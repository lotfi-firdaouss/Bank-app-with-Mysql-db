package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientService {

	public void addClient(Client client) throws ClassNotFoundException, SQLException {
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      // the mysql insert statement
	      String query = " insert into clients (nom, prenom,adresse)"
	        + " values (?, ?, ?)";
	      
	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
	      preparedStmt.setString (1, client.getNom());
	      preparedStmt.setString (2, client.getPrenom());
	      preparedStmt.setString(3, client.getAdresse());
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
	}
	
	public ObservableList<Client> getClients() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      ObservableList<Client> Clients=FXCollections.observableArrayList();
	      String query = " select * from clients";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_client=rs.getInt("id_client");
	        String nom = rs.getString("nom");
	        String prenom = rs.getString("prenom");
	        String adresse = rs.getString("adresse");
	        
	        Client client=new Client(id_client,nom,prenom,adresse);
	    	Clients.add(client);
	    	
	      }
	      st.close();
	      
	      return Clients;
	}
	
	public static ArrayList<Client> getClient() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      ArrayList<Client> Clients=new ArrayList<Client>();
	      String query = " select * from clients";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_client=rs.getInt("id_client");
	        String nom = rs.getString("nom");
	        String prenom = rs.getString("prenom");
	        String adresse = rs.getString("adresse");
	        
	        Client client=new Client(id_client,nom,prenom,adresse);
	    	Clients.add(client);
	    	
	      }
	      st.close();
	      
	      return Clients;
	}
	
	public ArrayList<Integer> getClientsIds() throws ClassNotFoundException, SQLException {
	// create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      
	  ArrayList<Integer> ClientsIDs = new ArrayList<Integer>();
      String query = " select * from clients";
      
      //create the java statement
      Statement st = (Statement) conn.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = (ResultSet) st.executeQuery(query);
      
      // iterate through the java resultset
      while (rs.next())
      {
    	int id_client=rs.getInt("id_client");
    	ClientsIDs.add(id_client);
      }
      st.close();
      
      return ClientsIDs;
	}
	
	public ArrayList<String> getClientsInfo() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
		  ArrayList<String> ClientsInfo = new ArrayList<String>();
	      String query = " select * from clients";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_client=rs.getInt("id_client");
	        String nom = rs.getString("nom");
	        String prenom = rs.getString("prenom");
	        String adresse = rs.getString("adresse");
	        
	        String client="**********informations**********"+
	        "\n-Client's id: "+id_client+"\n-Last name: "+nom+
	        "\n-First name: "+prenom+"\n-Adress: "+adresse+
	        "\n*******************************";
	        
	        ClientsInfo.add(client);
	    	
	      }
	      st.close();
	      
	      return ClientsInfo;
	}

	public void DeleteClient(int ClientID) throws ClassNotFoundException, SQLException {
	  // create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      
      String query = "delete from clients where id_client=?";
      
      PreparedStatement preparedSt=(PreparedStatement) conn.prepareStatement(query);
      preparedSt.setInt (1, ClientID);
      
      preparedSt.execute(); 
	}
	
	public void UpdateClient(Client client) throws ClassNotFoundException, SQLException {
	  // create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      
      String query = "update clients set nom=?,prenom=?,adresse=? where id_client=?";
      
      //create the java statement
      PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
      st.setString(1, client.getNom());
      st.setString(2, client.getPrenom());
      st.setString(3,client.getAdresse());
      st.setInt(4, client.getId_personne());
      
      //execute the query	
      st.execute();
      
	}

}
