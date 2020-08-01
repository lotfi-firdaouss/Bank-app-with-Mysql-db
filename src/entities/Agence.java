package entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Agence implements Serializable{

	private static int i=0;
	private String adresse;
	private String nom;
	private int numero;
	//on doit aussi ajouter un champs de banque
	private Banque bq;
	//on doit aussi ajouter la liste d'employés qui travaillent dans une seule agence
	private ArrayList<Employe> employes;
	
	//default constructor
	public Agence() {
		i++;
		this.numero=i;		
	}
	
	//constructor
	public Agence(String adresse,String nom,Banque bq) {
		i++;
		this.numero=i;
		this.adresse=adresse;
		this.nom=nom;
		this.bq=bq;		
	}	
	
	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		Agence.i = i;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Banque getBq() {
		return bq;
	}

	public void setBq(Banque bq) {
		this.bq = bq;
	}

	public ArrayList<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(ArrayList<Employe> employes) {
		this.employes = employes;
	}

	public void setElements(String adresse,String nom,Banque bq) {
		this.adresse=adresse;
		this.nom=nom;
		this.bq=bq;		
	}
	
	void afficher() {
		
	}
	
	public void finalize() {
		
	}
	
}
