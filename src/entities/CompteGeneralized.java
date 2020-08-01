package entities;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class CompteGeneralized extends CompteRenumere {
	private ArrayList<Integer> OwnerClientsIds;
	private char type_compte;

	// constructor
	public CompteGeneralized(int id_compte, int id_employe, int id_agence, char type_compte, float taux_interet,
			ArrayList<Integer> OwnerClientsIds, int montant_compte) {
		super(id_compte, id_employe, id_agence, taux_interet, montant_compte);
		this.type_compte = type_compte; // either 'R' or 'C', standing for renumere and courant respectively
		this.OwnerClientsIds = OwnerClientsIds;
	}

	public ArrayList<Integer> getOwnerClientsIds() {
		return OwnerClientsIds;
	}

	public void setOwnerClientsIds(ArrayList<Integer> ownerClientsIds) {
		OwnerClientsIds = ownerClientsIds;
	}

	public char getType_compte() {
		return type_compte;
	}

	public void setType_compte(char type_compte) {
		this.type_compte = type_compte;
	}

	public void afficher() {
		System.out.println("**********informations**********" + "\n-Account id: " + this.getId_compte()
				+ "\n-Associated employee's id: " + this.getId_employe() + "\n-Associated agency's id: "
				+ this.getId_agence() + "\n-Type of Account: " + this.type_compte + "\n-Interest Rate: "
				+ this.getTauxInteret() + "\n-Sum of Account: " + this.getMontant()
				+ "\n*******************************");
	}
}
