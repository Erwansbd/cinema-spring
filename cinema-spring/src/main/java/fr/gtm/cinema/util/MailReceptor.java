package fr.gtm.cinema.util;

import java.io.Serializable;

public class MailReceptor implements Serializable {
	private String email;
	private String prenom;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
