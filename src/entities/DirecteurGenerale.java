package entities;

@SuppressWarnings("serial")
public class DirecteurGenerale extends Personne{

	private static int i=0;
	@SuppressWarnings("unused")
	private int directid;
	@SuppressWarnings("unused")
	private double revenu;
	//on doit aussi ajouter un champs de banque:
	@SuppressWarnings("unused")
	private Banque banque;
	
	public DirecteurGenerale() {
		
	}

	public DirecteurGenerale(double revenu,Banque bq) {
		i++;
		this.directid=i;
		this.revenu=revenu;
		this.banque=bq;
	}
	
}
