package fr.gtm.cinema;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.gtm.cinema.dao.CinemaRepository;
import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CinemaSpringApplicationTests {
	@Autowired
	CinemaRepository repo;

	@Test
	void getAll() {
		List<Film> films = repo.findAll();
		assertNotNull(films);
		assertTrue(films.size() > 0);
		for (Film f : films) {
			System.out.println(f.getTitre());
		}
	}

	@Test
	void getActeurs() {
		Film film = repo.getActeursByFilms(2);
		assertTrue(film.getActeurs().size() > 1);
		List<Acteur> acteurs = film.getActeurs();
		for (Acteur a : acteurs) {
			System.out.println(a.getCivilite() + " " + a.getNom() + " " + a.getPrenom());
		}
	}

}
