package entities;

@SuppressWarnings("serial")
public class CompteCourant extends Compte {

	// default constructor
	public CompteCourant() {

	}

	public CompteCourant(Compte p) {
		super();
	}

	public CompteCourant(int id_employe,int id_agence,int montant_compte) {
		super(id_employe,id_agence,montant_compte);
	}
	
	public CompteCourant(int id_compte,int id_employe,int id_agence,int montant_compte) {
		super(id_employe,id_agence,montant_compte);
		this.setId_compte(id_compte);
	}

//	@Override
//	public String toString() {
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!to chaaaaaaange
		//		String clientsIds = "";
//		for (Client cl : this.getClients()) {
//			clientsIds += cl.getPersonid() + " , ";
//		}
//
//		return "Informations:\n*Account Type : 'Compte Courant' \n*Account Id :  " + this.getCompteid()
//				+ "\n*Account Clients ids :  " + clientsIds + "\n*Account responsible Employee's id :  "
//				+ this.getEmploye().getPersonid() + "\n*Account responsible Agency id :  "
//				+ this.getAgence().getNumero();
//	}

}
