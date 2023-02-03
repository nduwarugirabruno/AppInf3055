package entity.metier;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Patient extends User implements Serializable{

	private Long idPatient;
	private String Description;
	private String Commentaire;
	private Long id_Users;
	public Patient() {
		super();
	}

	public Patient(String nom, String localite, String profession, long tel, int age, String login, String password, String description, String commentaire) {
		super(nom, localite, profession, tel, age, login, password);
		Description = description;
		Commentaire = commentaire;
	}

	public Long getId_Users() {
		return id_Users;
	}

	public void setId_Users(Long id_Users) {
		this.id_Users = id_Users;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCommentaire() {
		return Commentaire;
	}

	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", Description=" + Description + ", password=" + password
				+ ", login=" + login + ", Commentaire=" + Commentaire + ", id_Users=" + getId_Users() +"]";
	}
	
}
