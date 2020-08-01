package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

@SuppressWarnings({ "unused", "serial" })
public class Employe extends Personne implements Serializable {

	// private Date date=new Date(0,0,0); this method is deprecated so we use
	// instead:
	private Date date_embauche;
	// il faut aussi ajouter le champs d'agence danslaquelle l'object employé
	// travaille
	private int id_agence;

	// default constructor
	public Employe() {
		super();
	}

	// constructor
	public Employe(String nom, String prenom, int id_agence,Date date_embauche) {
		super(nom, prenom);
		this.id_agence=id_agence;
		this.date_embauche=date_embauche;
	}
	
	public Employe(int id_employe, int id_agence,String nom, String prenom,Date date_embauche) {
		super(nom, prenom);
		this.id_agence=id_agence;
		this.date_embauche=date_embauche;
		this.setId_personne(id_employe);

	}

	public Date getDate_embauche() {
		return date_embauche;
	}

	public void setDate_embauche(Date date_embauche) {
		this.date_embauche = date_embauche;
	}

	public int getId_agence() {
		return id_agence;
	}

	public void setId_agence(int id_agence) {
		this.id_agence = id_agence;
	}
	
	public void afficher() {
		System.out.println("Informations : \n*Id : " + this.getId_personne()+"\n*Nom : " + this.getNom() + 
				"\n*Prenom : " + this.getPrenom()+ "\nDate d'embauche : "+this.getDate_embauche()+
				"\n*Agence id: " + this.getId_agence());
	}

	@Override
	public String toString() {
		return "Informations : \n*Id : " + this.getId_personne()+"\n*Nom : " + this.getNom() + 
				"\n*Prenom : " + this.getPrenom()+ "\nDate d'embauche : "+this.getDate_embauche()+
				"\n*Agence id: " + this.getId_agence();
	}
}
