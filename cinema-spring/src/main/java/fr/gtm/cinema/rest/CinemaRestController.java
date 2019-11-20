package fr.gtm.cinema.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.cinema.dao.ActeurRepository;
import fr.gtm.cinema.dto.ActeurDTO;
import fr.gtm.cinema.entities.Acteur;

@RestController
public class CinemaRestController {

	@Autowired
	ActeurRepository acteurRepository;

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

}
