package app.views;

import java.sql.Date;
import java.sql.SQLException;

import entities.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.AgencyService;
import services.EmployeeService;

public class ModifyEmployeeController {
	
	//configure the table
	@FXML private TableView<Employe> tableView;
	@FXML private TableColumn<Employe , Integer> id_employeColumn;
	@FXML private TableColumn<Employe , Integer> id_agenceColumn;
	@FXML private TableColumn<Employe , String> nomColumn;
	@FXML private TableColumn<Employe , String> prenomColumn;
	@FXML private TableColumn<Employe , Date> date_embaucheColumn;
	
	@FXML
	private Button cancelButton;
	
	public void initialize() throws ClassNotFoundException, SQLException {
		//set up the columns in the table
		id_employeColumn.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id_personne")); //here we tell it the 
		//fields that we'll be working with from the object
		id_agenceColumn.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id_agence"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
		prenomColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
		date_embaucheColumn.setCellValueFactory(new PropertyValueFactory<Employe, Date>("date_embauche"));
		
		//load data from database
		EmployeeService myservice=new EmployeeService();
		tableView.setItems(myservice.getEmployees());
		
		//update the table to allow for the lastname column to be editable
		tableView.setEditable(true); //we set the table to be editable , then the columns
		nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//we should give the user the possibility to choose from a choice box
		AgencyService myservice2=new AgencyService();
		ObservableList<Integer> agenciesIds=FXCollections.observableArrayList();
		agenciesIds.addAll(myservice2.getAgenciesIds());
		id_agenceColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(agenciesIds));
	
	}

	@FXML
	public void changeIdAgencyCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		Employe Employeselected = tableView.getSelectionModel().getSelectedItem();
		Employeselected.setId_agence((int) edittedCell.getNewValue());
		
		//then we should change the actual data in the database
		EmployeeService myservice=new EmployeeService();
		myservice.UpdateEmployee(Employeselected);	
	}
	
	@FXML
	public void changeLastNameCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		Employe Employeselected = tableView.getSelectionModel().getSelectedItem();
		//System.out.println(Employeselected);
		Employeselected.setNom(edittedCell.getNewValue().toString());
		//System.out.println(Employeselected);
		
		//then we should change the actual data in the database
		EmployeeService myservice=new EmployeeService();
		myservice.UpdateEmployee(Employeselected);	
	}
	
	@FXML
	public void changeFirstNameCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		Employe Employeselected = tableView.getSelectionModel().getSelectedItem();
		//System.out.println(Employeselected);
		Employeselected.setPrenom(edittedCell.getNewValue().toString());
		//System.out.println(Employeselected);
		
		//then we should change the actual data in the database
		EmployeeService myservice=new EmployeeService();
		myservice.UpdateEmployee(Employeselected);	
	}
	
	
	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
