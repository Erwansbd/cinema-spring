package fr.gtm.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.gtm.cinema.entities.Film;

@Repository
public interface CinemaRepository extends JpaRepository<Film, Long> {
	
	@Query("SELECT f FROM Film f JOIN FETCH f.roles WHERE f.id = ?1")
	Film getFilmsAndActeursById(long id);

}
