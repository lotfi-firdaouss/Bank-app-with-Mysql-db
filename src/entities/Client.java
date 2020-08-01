package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Client extends Personne implements Serializable {

	private String adresse;

	// default constructor
	public Client() {
		super();
	}

	// constructor
	public Client(String nom, String prenom, String adresse) {
		super(nom, prenom);
		this.adresse = adresse;
	}
	
	public Client(int id_client,String nom, String prenom, String adresse) {
		super(nom, prenom);
		this.setId_personne(id_client);
		this.adresse = adresse;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void afficher() {
		System.out.println("Informations : \n*Nom : " + this.getNom() + "\n*Prenom : " + this.getPrenom()
				+ "\n*Adresse : " + this.adresse + "\n" + "*Id : " + this.getId_personne()
				+ "--------------------------------------------------");
	}

	@Override
	public String toString() {
		return "Informations : \n*Nom : " + this.getNom() + "\n*Prenom : " + this.getPrenom() + "\n*Adresse : "
				+ this.adresse + "\n" + "*Id : " + this.getId_personne()
				+ "\n--------------------------------------------------";
	}

}
