package entities;

@SuppressWarnings("serial")
public class CompteRenumere extends Compte {

	private float tauxInteret;

	// defaultconstructor
	public CompteRenumere() {

	}

	public CompteRenumere( int id_employe,int id_agence,float tauxInteret,int montant_compte) {
		super(id_employe,id_agence,montant_compte);
		this.tauxInteret=tauxInteret;
		
	}
	
	public CompteRenumere(int id_compte,int id_employe,int id_agence,float tauxInteret,int montant_compte) {
		super(id_employe,id_agence,montant_compte);
		this.setId_compte(id_compte);
		this.tauxInteret=tauxInteret;
	}

	public float getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}


	void verserInteret() {

	}

}
