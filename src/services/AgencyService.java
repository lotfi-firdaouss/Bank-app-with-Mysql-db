package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class AgencyService {

    public ArrayList<Integer> getAgenciesIds() throws ClassNotFoundException, SQLException{
		// create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver"; //The driver class for the mysql database is com.mysql.jdbc.Driver
	      String myUrl = "jdbc:mysql://localhost/comptebancairedb"; //where comptebancairedb is the database we'll be working on
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      //trying to get elements from employes table
	      String query = " select * from agences";
	      
	      //create the java statement
	      Statement st = (Statement) conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = (ResultSet) st.executeQuery(query);
	      
	      ArrayList<Integer> AgenciesIDs=new ArrayList<Integer>();
	   // iterate through the java resultset
	      while (rs.next())
	      {
	    	int id_agence=rs.getInt("id_agence");
	    	AgenciesIDs.add(id_agence);
	      }
	      st.close();
	      
	      return AgenciesIDs;
    }

}
