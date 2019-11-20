package fr.gtm.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.cinema.entities.Acteur;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {

}
