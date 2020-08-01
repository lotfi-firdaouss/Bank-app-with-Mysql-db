package entities;

import java.util.ArrayList;

public class Banque {
	
	@SuppressWarnings("unused")
	private String adresse;
	@SuppressWarnings("unused")
	private double capital;
	@SuppressWarnings("unused")
	private String nom;
	//on doit aussi ajouter un champs de directeur générale:
	@SuppressWarnings("unused")
	private DirecteurGenerale DG;
	//Pour une banque plusieurs agences appartiennent
	private ArrayList<Agence> agences;
	
	//default constructor
	public Banque() {
		
	}
	
	//constructor
	public Banque(String adresse,double capital,String nom,DirecteurGenerale DG,ArrayList<Agence> agences) {
		this.adresse=adresse;
		this.capital=capital;
		this.nom=nom;
		this.agences=agences;
		this.DG=DG;
	}
	
	public void ajouterAgence(Agence ag) {
		this.agences.add(ag);
	}
	
	
	public void afficher() {
		
	}

}
