package fr.gtm.cinema.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.cinema.dao.ActeurRepository;
import fr.gtm.cinema.dto.ActeurDTO;
import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.util.MailReceptor;

@RestController
public class CinemaRestController {

	@Autowired
	ActeurRepository acteurRepository;
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

	@PostMapping("/acteur/new")
	public String createActeur(@RequestBody ActeurDTO acteurDTO) {
		acteurRepository.save(acteurDTO.toActeur());
		return "Votre contact : " + acteurDTO.getCivilite() + " " + acteurDTO.getNom() + " " + acteurDTO.getPrenom()
				+ " a bien été sauvegardé en base de données.";
	}

	@PostMapping("/mail/send")
	public String testMail(@RequestBody MailReceptor mailReceptor) {
		try {
			new Thread(()->sendMail(mailReceptor)).start();
		} catch (Exception e) {

		}
		return "Votre mail a bien été envoyé.";
	}

	private void sendMail(MailReceptor mailReceptor) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mailReceptor.getEmail());
		mailMessage.setFrom("Emmanuel.Macron@elysee.gouv.fr");
		mailMessage.setSubject("Information");
		mailMessage.setText("Bonjour "+"\u001B[36m"+mailReceptor.getPrenom());
		mailSender.send(mailMessage);
	}

}
