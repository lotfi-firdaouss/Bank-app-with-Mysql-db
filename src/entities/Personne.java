package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Personne implements Serializable{
	private String nom;
	private String prenom;
	private int id_personne;
	
	//default constructor
	public Personne() {
	}
	
	//constructor
	public Personne(String nom,String prenom) {
		this.setNom(nom);
		this.setPrenom(prenom);
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

	public int getId_personne() {
		return id_personne;
	}

	public void setId_personne(int id_personne) {
		this.id_personne = id_personne;
	}

}
