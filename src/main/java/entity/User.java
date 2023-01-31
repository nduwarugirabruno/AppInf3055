package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	private Long id_Users;
	private String Nom;
	private String localite;
	private String Profession;
	private long Tel;
	private int age;
	
	
	public User() {
		super();
	}

	
	public User(String nom, String localite, String profession, long tel, int age) {
		super();
		this.Nom = nom;
		this.localite = localite;
		this.Profession = profession;
		Tel = tel;
		this.age = age;
	}


	public Long getId_Users() {
		return id_Users;
	}
	public void setId_Users(Long id_Users) {
		this.id_Users = id_Users;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	public String getProfession() {
		return Profession;
	}
	public void setProfession(String profession) {
		Profession = profession;
	}
	public long getTel() {
		return Tel;
	}
	public void setTel(long tel) {
		Tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id_Users=" + id_Users + ", Nom=" + Nom + ", localite=" + localite + ", Profession=" + Profession
				+ "]";
	}
}
