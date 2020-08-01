package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Compte implements Serializable {
	private int id_compte;
	// il faut ajouter l'employé responsable de ce compte
	private int id_employe;
	// il faut ajouter l'agence à laquelle appartient ce compte
	private int id_agence;
	//il faut ajouter le montant originale du compte
	private int montant;

	// default constructor
	public Compte() {
	}

	// constructor
	public Compte( int id_employe,int id_agence,int montant_compte) {
		this.id_employe=id_employe;
		this.id_agence=id_agence;
		this.montant=montant_compte;
	}
	
	public Compte(int id_compte, int id_employe,int id_agence,int montant_compte) {
		this.id_compte=id_compte;
		this.id_employe=id_employe;
		this.id_agence=id_agence;
		this.montant=montant_compte;
	}
	
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getId_compte() {
		return id_compte;
	}

	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}

	public int getId_employe() {
		return id_employe;
	}

	public void setId_employe(int id_employe) {
		this.id_employe = id_employe;
	}

	public int getId_agence() {
		return id_agence;
	}

	public void setId_agence(int id_agence) {
		this.id_agence = id_agence;
	}

}
