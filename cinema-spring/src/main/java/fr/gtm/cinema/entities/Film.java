package fr.gtm.cinema.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
@Table(name = "films")
@Access(AccessType.FIELD)
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_film")
	private long id;
	private String titre;
	private String realisateur;
	@Column(name = "date_sortie")
	private LocalDate dateSortie;
	@Min(1)
	private int duree; // durée en minutes;
	@DecimalMin("1.0")
	private double prixHT;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "film_acteur", joinColumns = @JoinColumn(name = "fk_film"), inverseJoinColumns = @JoinColumn(name = "fk_acteur"))
	@MapKeyColumn(name = "role")
	private Map<String, Acteur> roles = new HashMap<String, Acteur>();

	public Film() {

	}

	public Film(String titre, String realisateur, LocalDate dateSortie, @Min(1) int duree,
			@DecimalMin("1.0") double prixHT) {
		this.titre = titre;
		this.realisateur = realisateur;
		this.dateSortie = dateSortie;
		this.duree = duree;
		this.prixHT = prixHT;
	}
	
	public void addRole(String role, Acteur acteur) {
		roles.put(role, acteur);
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

	public Map<String, Acteur> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Acteur> roles) {
		this.roles = roles;
	}

	public void add(String role, Acteur acteur) {
		roles.put(role, acteur);
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
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Film other = (Film) obj;
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
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

}
