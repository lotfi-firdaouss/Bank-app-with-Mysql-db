package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import entities.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeService {

	public void addEmployee(Employe employe) throws ClassNotFoundException, SQLException {

		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      // the mysql insert statement
	      String query = " insert into employes (id_agence,nom, prenom,date_embauche)"
	        + " values (?,?, ?, ?)";
	      
	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
	      preparedStmt.setInt(1, employe.getId_agence());
	      preparedStmt.setString (2, employe.getNom());
	      preparedStmt.setString(3, employe.getPrenom());
	      preparedStmt.setDate(4,employe.getDate_embauche() );
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
		
	}
	
	public ObservableList<Employe> getEmployees() throws SQLException, ClassNotFoundException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      ObservableList<Employe> EmployeesInfo = FXCollections.observableArrayList();
	      String query = " select * from employes";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_employe=rs.getInt("id_employe");
	    	int id_agence=rs.getInt("id_agence");
	        String nom = rs.getString("nom");
	        String prenom = rs.getString("prenom");
	        Date date_embauche = rs.getDate("date_embauche");
	        
	        Employe employe=new Employe(id_employe,id_agence,nom,prenom,date_embauche);
	        EmployeesInfo.add(employe);
	    	
	      }
	      st.close();
	      
	      return EmployeesInfo;
	}
	
	public ArrayList<Integer> getEmployeesIds() throws ClassNotFoundException, SQLException {
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //trying to get elements from employes table
	      String query = " select * from employes";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      ArrayList<Integer> EmployeesIDs=new ArrayList<Integer>();
	   // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_employe=rs.getInt("id_employe");
	    	EmployeesIDs.add(id_employe);
	      }
	      st.close();
	      
	      return EmployeesIDs;
		}

	public ArrayList<String> getEmployeesinfo() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
		  ArrayList<String> EmployeesInfo = new ArrayList<String>();
	      String query = " select * from employes";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_employee=rs.getInt("id_employe");
	    	int id_agence=rs.getInt("id_agence");
	        String nom = rs.getString("nom");
	        String prenom = rs.getString("prenom");
	        Date date_embauche = rs.getDate("date_embauche");
	        
	        String Employee="**********informations**********"+
	        "\n-Employee's id: "+id_employee+"\n-Associated agency: "
	        +id_agence+"\n-Last name: "+nom+"\n-First name: "+prenom+
	        "\n-Hiring date: "+date_embauche+
	        "\n*******************************";
	        
	        EmployeesInfo.add(Employee);
	    	
	      }
	      st.close();
	      
	      return EmployeesInfo;
	}
	
	public int getCorrespondantAgencyId(int employeeID) throws ClassNotFoundException, SQLException {
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //trying to get elements from employes table
	      String query = " select * from employes";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	   // iterate through the java resultset
	      int id_agence=0;
	      while (rs.next())
	      {
	    	int id_employe=rs.getInt("id_employe");
	    	if(id_employe==employeeID) {
	    		id_agence=rs.getInt("id_agence");
	    	}
	      }
	      st.close();
	      
	      return id_agence;
	}
	
	public void DeleteEmployee(int employeeID) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      String query = "delete from employes where id_employe=?";
	      
	      PreparedStatement preparedSt=(PreparedStatement) conn.prepareStatement(query);
	      preparedSt.setInt (1, employeeID);
	      
	      preparedSt.execute();
	}
	
	public void UpdateEmployee(Employe employe) throws ClassNotFoundException, SQLException {
		  // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      String query = "update employes set id_agence=?,nom=?,prenom=?,date_embauche=? where id_employe=?";
	      
	      //create the java statement
	      PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
	      st.setInt(1, employe.getId_agence());
	      st.setString(2, employe.getNom());
	      st.setString(3, employe.getPrenom());
	      st.setDate(4,employe.getDate_embauche());
	      st.setInt(5, employe.getId_personne());
	      
	      //execute the query	
	      st.execute();
	}

}
