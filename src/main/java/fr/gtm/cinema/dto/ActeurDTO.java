package fr.gtm.cinema.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;

public class ActeurDTO {
	private long id;
	private String civilite;
	private String nom;
	private String prenom;
	private LocalDate dateNaissace;
	private LocalDate dateMort;

	public ActeurDTO() {
	}

	public ActeurDTO(Acteur acteur) {
		id = acteur.getId();
		civilite = acteur.getCivilite();
		nom = acteur.getNom();
		prenom = acteur.getPrenom();
		dateNaissace = acteur.getDateNaissace();
		dateMort = acteur.getDateMort();
	}

	public ActeurDTO(long id, String civilite, String nom, String prenom, LocalDate dateNaissace, LocalDate dateMort) {
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissace = dateNaissace;
		this.dateMort = dateMort;
	}

	public Acteur toActeur() {
		Acteur acteur = new Acteur(id, civilite, nom, prenom, dateNaissace, dateMort);
		return acteur;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissace() {
		return dateNaissace;
	}

	public void setDateNaissace(LocalDate dateNaissace) {
		this.dateNaissace = dateNaissace;
	}

	public LocalDate getDateMort() {
		return dateMort;
	}

	public void setDateMort(LocalDate dateMort) {
		this.dateMort = dateMort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((dateMort == null) ? 0 : dateMort.hashCode());
		result = prime * result + ((dateNaissace == null) ? 0 : dateNaissace.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActeurDTO other = (ActeurDTO) obj;
		if (civilite == null) {
			if (other.civilite != null)
				return false;
		} else if (!civilite.equals(other.civilite))
			return false;
		if (dateMort == null) {
			if (other.dateMort != null)
				return false;
		} else if (!dateMort.equals(other.dateMort))
			return false;
		if (dateNaissace == null) {
			if (other.dateNaissace != null)
				return false;
		} else if (!dateNaissace.equals(other.dateNaissace))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

}
