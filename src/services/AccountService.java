package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import entities.Compte;
import entities.CompteCourant;
import entities.CompteGeneralized;
import entities.CompteRenumere;
import entities.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountService {

   public void addAccount(CompteCourant compte,ObservableList<Integer> mySelectedClientsIDs) throws ClassNotFoundException, SQLException {
	  // create a mysql database connection
	  String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	  String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	  Class.forName(myDriver);
	  Connection conn = DriverManager.getConnection(myUrl, "root", "");
	  
	  //trying to insert an account //here we have to insert in two tables "comptes" table and "posséder" table
	  
	  // the mysql insert statement
	  String query1 = " insert into comptes (id_employe,id_agence,type_compte,montant_compte)"
	        + " values (?,?,?,?)";
	  
	  
	  // create the mysql insert preparedstatement
	  PreparedStatement preparedStmt1 = (PreparedStatement) conn.prepareStatement(query1);
	  preparedStmt1.setInt(1, compte.getId_employe());
	  preparedStmt1.setInt (2, compte.getId_agence());
	  preparedStmt1.setString(3, "C");
	  preparedStmt1.setInt(4, compte.getMontant());
	  
	  // execute the preparedstatement
	  preparedStmt1.execute();	
	
	  ResultSet rs=(ResultSet) preparedStmt1.getGeneratedKeys();
	  int id=0;
	  if(rs.next()){
			id=rs.getInt(1);
		}
	  for(int id_client: mySelectedClientsIDs) {
	    String query2="insert into posséder (id_compte,id_client) values(?,?)";
	    PreparedStatement preparedStmt2 = (PreparedStatement) conn.prepareStatement(query2);
	    preparedStmt2.setInt(1, id);
	    preparedStmt2.setInt(2, id_client);
	    preparedStmt2.execute();			  
	  }
	      
	}

	public void addAccount(CompteRenumere compte,ObservableList<Integer> mySelectedClientsIDs) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
		  String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
		  String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(myUrl, "root", "");
		  
		  //trying to insert an account //here we have to insert in two tables "comptes" table and "posséder" table
		  
		  // the mysql insert statement
		  String query1 = " insert into comptes (id_employe,id_agence,type_compte,taux_interet,montant_compte)"
		        + " values (?,?,?,?,?)";
		  
		  
		  // create the mysql insert preparedstatement
		  PreparedStatement preparedStmt1 = (PreparedStatement) conn.prepareStatement(query1);
		  preparedStmt1.setInt(1, compte.getId_employe());
		  preparedStmt1.setInt (2, compte.getId_agence());
		  preparedStmt1.setString(3, "R");
		  preparedStmt1.setFloat(4, (float) compte.getTauxInteret());
		  preparedStmt1.setInt(5, compte.getMontant());
		  
		  // execute the preparedstatement
		  preparedStmt1.execute();	
		
		  ResultSet rs=(ResultSet) preparedStmt1.getGeneratedKeys();
		  int id=0;
		  if(rs.next()){
				id=rs.getInt(1);
			}
		  for(int id_client: mySelectedClientsIDs) {
		    String query2="insert into posséder (id_compte,id_client) values(?,?)";
		    PreparedStatement preparedStmt2 = (PreparedStatement) conn.prepareStatement(query2);
		    preparedStmt2.setInt(1, id);
		    preparedStmt2.setInt(2, id_client);
		    preparedStmt2.execute();			  
		  }
	}
	

	public void DeleteAccount(int AccountId) throws ClassNotFoundException, SQLException{
		//we should delete from two tables the "comptes" table and the relation table "posséder"
		
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //we'll start with the "comptes" table
	      String query = "delete from comptes where id_compte=?";
	      PreparedStatement preparedSt=(PreparedStatement) conn.prepareStatement(query);
	      preparedSt.setInt (1, AccountId);
	      preparedSt.execute();
	      
	      //then the "posséder" table
	      String query2 = "delete from posséder where id_compte=?";
	      PreparedStatement preparedSt2=(PreparedStatement) conn.prepareStatement(query2);
	      preparedSt2.setInt (1, AccountId);
	      preparedSt2.execute();
	      
	}
	
	public ArrayList<Integer> getComptesIds(String account_type) throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	    //1//We should select basic informations of compte from "comptes" table
	    //2//We should select client owners of this compte from "posséder" table
		  ArrayList<Integer> ClientsIds = new ArrayList<Integer>();
	      String query1 = " select id_compte from comptes where type_compte=?";
	      
	      //create the java statement
	      PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(query1);
	      
	      if(account_type.equals("Courant")) {
	    	  st1.setString(1, "C");
	      }
	      else {
	    	  st1.setString(1, "R");
	      }
	      
	      // execute the query, and get a java resultset
	      ResultSet rs1=(ResultSet) st1.executeQuery();
	      
	      // iterate through the java resultset
	      while (rs1.next())
	      {
	    	int id_compte=rs1.getInt("id_compte");
	    	ClientsIds.add(id_compte);
	      }
	      st1.close();
	      
	      return ClientsIds;
	}
	
	public ArrayList<String> getComptesInfo(String account_type) throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	    //1//We should select basic informations of compte from "comptes" table
	    //2//We should select client owners of this compte from "posséder" table
		  ArrayList<String> ClientsInfo = new ArrayList<String>();
	      String query1 = " select * from comptes where type_compte=?";
	      
	      //create the java statement
	      PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(query1);
	      if(account_type.equals("Courant")) {
	    	  st1.setString(1, "C");
	      }
	      else {
	    	  st1.setString(1, "R");
	      }
	      
	      // execute the query, and get a java resultset
	      ResultSet rs1=(ResultSet) st1.executeQuery();
	      
	      // iterate through the java resultset
	      while (rs1.next())
	      {
	    	int id_compte=rs1.getInt("id_compte");
	    	int id_employee=rs1.getInt("id_employe");
	    	int id_agence=rs1.getInt("id_agence");
	    	String type_compte=rs1.getString("type_compte");
	        Float taux_interet = rs1.getFloat("taux_interet");
	        int montant_compte=rs1.getInt("montant_compte");
	        
	        ArrayList<Integer> ownerClients=new ArrayList<Integer>();
	        String query2="select * from posséder where id_compte=?";
	        PreparedStatement st2= (PreparedStatement) conn.prepareStatement(query2);
	        st2.setInt(1, id_compte);
	        ResultSet rs2=(ResultSet) st2.executeQuery();
	        
	        while(rs2.next()) {
	        	int id_client=rs2.getInt("id_client");
	        	ownerClients.add(id_client);
		        }
		        
	        String client="**********informations**********"+
	        "\n-Account id: "+id_compte+
	        "\n-Associated employee's id: "+id_employee+
	        "\n-Associated agency's id: "+id_agence+
	        "\n-Type of Account: "+type_compte;
	        
	        if(type_compte.equals('R')) {
	        	client+="\n-Interest Rate: "+taux_interet;
	        }
	        
	        client+="\n-Sum of Account: "+montant_compte+
	        		"\n*******************************";
	        		
	        
	        ClientsInfo.add(client);
	        st2.close();
	        
	      }
	      st1.close();
	      
	      return ClientsInfo;
	}
	
	public Compte GetAppropriateAccount(int compteID) throws ClassNotFoundException, SQLException {
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	    ///We should select informations of compte from "comptes" table
	      String query1 = " select * from comptes where id_compte=?";
	      
	      //create the java statement
	      PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(query1);
	      st1.setInt(1, compteID);
	      
	      // execute the query, and get a java resultset
	      ResultSet rs1=(ResultSet) st1.executeQuery();
	      
	      Compte compte = new Compte();
	      // iterate through the java resultset
	      while (rs1.next())
	      {
	    	int id_compte=rs1.getInt("id_compte");
	    	
	    	if(id_compte==compteID) {
		    	int id_employe=rs1.getInt("id_employe");
		    	int id_agence=rs1.getInt("id_agence");
		        int montant_compte=rs1.getInt("montant_compte");
		        compte=new Compte(id_compte,id_employe,id_agence,montant_compte);	    		
	    	}
	      }
	      st1.close();
	      
	      return compte;
	}

	
	public ObservableList<CompteGeneralized> getAccountsGeneralized() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	    //1//We should select basic informations of compte from "comptes" table
	    //2//We should select Compte owners of this compte from "posséder" table
		  ObservableList<CompteGeneralized> ComptesGeneralized = FXCollections.observableArrayList();
	      String query1 = " select * from comptes ";
	      
	      //create the java statement
	      Statement st1 = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs1=(ResultSet) st1.executeQuery(query1);
	      
	      // iterate through the java resultset
	      while (rs1.next())
	      {
	    	int id_compte=rs1.getInt("id_compte");
	    	int id_employe=rs1.getInt("id_employe");
	    	int id_agence=rs1.getInt("id_agence");
	    	char type_compte=rs1.getString("type_compte").charAt(0);
	        Float taux_interet = rs1.getFloat("taux_interet");
	        int montant_compte=rs1.getInt("montant_compte");
	        
	        ArrayList<Integer> ownerClients=new ArrayList<Integer>();
	        String query2="select * from posséder where id_compte=?";
	        PreparedStatement st2= (PreparedStatement) conn.prepareStatement(query2);
	        st2.setInt(1, id_compte);
	        ResultSet rs2=(ResultSet) st2.executeQuery();
	        
	        while(rs2.next()) {
	        	int id_client=rs2.getInt("id_client");
	        	ownerClients.add(id_client);
		        }
		        
	        CompteGeneralized compte=new CompteGeneralized(id_compte,id_employe,id_agence,type_compte,taux_interet,ownerClients,montant_compte);
	        ComptesGeneralized.add(compte);
	        
	        st2.close();
	        
	      }
	      st1.close();
	      
	      return ComptesGeneralized;
	}
	
	public void UpdateAccount(CompteGeneralized compte) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //1
	      String query = "update comptes set id_employe=?,id_agence=?,type_compte=?,taux_interet=?,montant_compte=? where id_compte=?";
	      
	      //create the java statement
	      PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
	      st.setInt(1, compte.getId_employe());
	      st.setInt(2, compte.getId_agence());
	      st.setString(3,Character.toString(compte.getType_compte()));
	      st.setFloat(4, compte.getTauxInteret());
	      st.setInt(5, compte.getMontant());
	      st.setInt(6, compte.getId_compte());
	      //execute the query	
	      st.execute();
	      
	      //2
	      //to change the posséder table we should firstly delete all elements with specified id_compte
	      //and then create new ones with the new selected clients
	      String query2 = "delete from posséder where id_compte=?";
	      PreparedStatement preparedSt2=(PreparedStatement) conn.prepareStatement(query2);
	      preparedSt2.setInt (1, compte.getId_compte());
	      preparedSt2.execute();
	      
	      for(int id_client: compte.getOwnerClientsIds()) {
			    String query3="insert into posséder (id_compte,id_client) values(?,?)";
			    PreparedStatement preparedStmt2 = (PreparedStatement) conn.prepareStatement(query3);
			    preparedStmt2.setInt(1, compte.getId_compte());
			    preparedStmt2.setInt(2, id_client);
			    preparedStmt2.execute();			  
			  }      
	      
	}
	
	public void UpdateClients(ArrayList<Integer> client,int id) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //2
	      //to change the posséder table we should firstly delete all elements with specified id_compte
	      //and then create new ones with the new selected clients
	      String query2 = "delete from posséder where id_compte=?";
	      PreparedStatement preparedSt2=(PreparedStatement) conn.prepareStatement(query2);
	      preparedSt2.setInt (1, id);
	      preparedSt2.execute();
	      
	      for(int id_client: client) {
			    String query3="insert into posséder (id_compte,id_client) values(?,?)";
			    PreparedStatement preparedStmt2 = (PreparedStatement) conn.prepareStatement(query3);
			    preparedStmt2.setInt(1, id);
			    preparedStmt2.setInt(2, id_client);
			    preparedStmt2.execute();			  
			  }      
	      
	}
	
	public void UpdateAccountSum(Compte compte) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");

	      String query = "update comptes set montant_compte=? where id_compte=?";
	      
	      //create the java statement
	      PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
	      st.setInt(1, compte.getMontant());
	      st.setInt(2, compte.getId_compte());
	      //execute the query	
	      st.execute();
	}
	
	public void AddOperation(Operation operation) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      String query="insert into operations(id_compte,date_operation,montant_operation,type_operation)"
	      		+ "values(?,?,?,?)";
	      PreparedStatement st=(PreparedStatement) conn.prepareStatement(query);
	      st.setInt(1, operation.getId_ac());
	      st.setDate(2, operation.getDate_operation());
	      st.setInt(3, operation.getMontant_operation());
	      st.setString(4, Character.toString(operation.getType_operation()));
	      st.execute();
	}
	

}
