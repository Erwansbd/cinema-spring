package fr.gtm.cinema.rest;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.cinema.dao.ActeurRepository;
import fr.gtm.cinema.dao.CinemaRepository;
import fr.gtm.cinema.dto.ActeurDTO;
import fr.gtm.cinema.dto.FilmDTO;
import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.util.MailReceptor;

@RestController
public class CinemaRestController {

	@Autowired
	ActeurRepository acteurRepository;
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/acteur/{id}")
	public ActeurDTO findActeurById(@PathVariable long id) {
		Optional<Acteur> acteur = acteurRepository.findById(id);
		if (!acteur.isPresent()) {
			throw new RuntimeException("L'id de l'acteur numéro " + id
					+ " est introuvable. Il n'existe sûrement pas dans votre base de données");
		}
		return new ActeurDTO(acteur.get());
	}

	@GetMapping("/role/{id}")
	public Map<String, Acteur> findRoleById(@PathVariable long id) {
		Film film = cinemaRepository.getFilmsAndActeursById(id);
		return film.getRoles();
	}

	@PostMapping("/role/new")
	public String createRole(@RequestBody ActeurDTO acteurDTO, FilmDTO filmDTO, String role) {
		acteurRepository.save(acteurDTO.toActeur());
		Film film = filmDTO.toFilm();
		film.addRole(role, acteurDTO.toActeur());
		cinemaRepository.save(film);
		return "Le rôle : " + role + " pour " + acteurDTO.getPrenom() + " " + acteurDTO.getNom() + " a bien été crée";

	}

	@PostMapping("/acteur/new")
	public String createActeur(@RequestBody ActeurDTO acteurDTO) {
		acteurRepository.save(acteurDTO.toActeur());
		return "Votre contact : " + acteurDTO.getCivilite() + " " + acteurDTO.getNom() + " " + acteurDTO.getPrenom()
				+ " a bien été sauvegardé en base de données.";
	}

	@PostMapping("/film/new")
	public String createFilm(@RequestBody FilmDTO filmDTO) {
		cinemaRepository.save(filmDTO.toFilm());
		return "Votre film : " + filmDTO.getTitre() + " " + filmDTO.getRealisateur() + " " + filmDTO.getDuree()
				+ " a bien été sauvegardé en base de données.";
	}

	@PostMapping("/mail/send")
	@Async
	public void sendMail(@RequestBody MailReceptor mailReceptor) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mailReceptor.getEmail());
		mailMessage.setFrom("Emmanuel.Macron@elysee.gouv.fr");
		mailMessage.setSubject("Information");
		mailMessage.setText("Bonjour " + mailReceptor.getPrenom());
		mailSender.send(mailMessage);
	}

}
