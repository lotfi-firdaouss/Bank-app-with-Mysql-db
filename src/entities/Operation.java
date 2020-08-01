package entities;

import java.sql.Date;

public class Operation {
	
	private int id_operation;
	private int id_ac;
	private Date date_operation;
	private int montant_operation;
	private char type_operation; //either withdraw 'W' or deposit 'D'
	
	//default constructor
	public Operation() {
		
	}
	
	//constructors
	public Operation(int id_ac,int montant,char type) {
		this.id_ac=id_ac;
		long millis=System.currentTimeMillis();  
		this.date_operation=new java.sql.Date(millis); //to get the date now 
		this.montant_operation=montant;
		this.type_operation=type;
	}
	
	public Operation(int id_operation,int id_ac,int montant,char type) {
		this.id_operation=id_operation;
		this.id_ac=id_ac;
		long millis=System.currentTimeMillis();  
		this.date_operation=new java.sql.Date(millis); //to get the date now 
		this.montant_operation=montant;
		this.type_operation=type;
	}

	public String toString() {
		String infos="*****************************"
				+ "\n-Operation's id: "+this.id_operation
				+"\n-Associated account's id: "+this.id_ac
				+"\n-Operation's date: "+this.date_operation
				+"\n-Amount of money: "+this.montant_operation
				+"\n-Type of operation: "+this.type_operation;
		return infos;
	}
	
	public int getId_operation() {
		return id_operation;
	}

	public void setId_operation(int id_operation) {
		this.id_operation = id_operation;
	}

	public int getId_ac() {
		return id_ac;
	}

	public void setId_ac(int id_ac) {
		this.id_ac = id_ac;
	}

	public Date getDate_operation() {
		return date_operation;
	}

	public void setDate_operation(Date date_operation) {
		this.date_operation = date_operation;
	}

	public int getMontant_operation() {
		return montant_operation;
	}

	public void setMontant_operation(int montant_operation) {
		this.montant_operation = montant_operation;
	}

	public char getType_operation() {
		return type_operation;
	}

	public void setType_operation(char type_operation) {
		this.type_operation = type_operation;
	}

}
