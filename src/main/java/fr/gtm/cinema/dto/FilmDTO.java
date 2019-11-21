package fr.gtm.cinema.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;

public class FilmDTO implements Serializable {
	private long id;
	private String titre;
	private String realisateur;
	private LocalDate dateSortie;
	private int duree;
	private double prixHT;

	public FilmDTO() {
	}

	public FilmDTO(Film film) {
		id = film.getId();
		titre = film.getTitre();
		realisateur = film.getRealisateur();
		dateSortie = film.getDateSortie();
		duree = film.getDuree();
		prixHT = film.getPrixHT();
	}

	public Film toFilm() {
		Film f = new Film(id, titre, realisateur, dateSortie, prixHT, duree);
		return f;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSortie == null) ? 0 : dateSortie.hashCode());
		result = prime * result + duree;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(prixHT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((realisateur == null) ? 0 : realisateur.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		FilmDTO other = (FilmDTO) obj;
		if (dateSortie == null) {
			if (other.dateSortie != null)
				return false;
		} else if (!dateSortie.equals(other.dateSortie))
			return false;
		if (duree != other.duree)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(prixHT) != Double.doubleToLongBits(other.prixHT))
			return false;
		if (realisateur == null) {
			if (other.realisateur != null)
				return false;
		} else if (!realisateur.equals(other.realisateur))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

}
