package app.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import app.Main;
import entities.CompteGeneralized;
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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.AccountService;
import services.AgencyService;
import services.EmployeeService;

public class ModifyAccountController {
	
	//configure the table
	@FXML private TableView<CompteGeneralized> tableView;
	@FXML private TableColumn<CompteGeneralized , Integer> id_compteColumn;
	@FXML private TableColumn<CompteGeneralized , Integer> id_employeColumn;
	@FXML private TableColumn<CompteGeneralized , Integer> id_agenceColumn;
	@FXML private TableColumn<CompteGeneralized , String> type_compteColumn;
	@FXML private TableColumn<CompteGeneralized , Float> taux_interetColumn;
	@FXML private TableColumn<CompteGeneralized , ArrayList<Integer>> Owner_clientsColumn;
	@FXML private TableColumn<CompteGeneralized , Integer> SumColumn;
	
	@FXML
	private Button cancelButton;
	
	public void initialize() throws ClassNotFoundException, SQLException {
		//set up the columns in the table
		id_compteColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized, Integer>("id_compte"));
		id_employeColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized, Integer>("id_employe"));
		id_agenceColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized, Integer>("id_agence"));
		type_compteColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized , String>("type_compte"));
		taux_interetColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized , Float>("tauxInteret"));
		Owner_clientsColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized , ArrayList<Integer>>("OwnerClientsIds"));
		SumColumn.setCellValueFactory(new PropertyValueFactory<CompteGeneralized, Integer>("montant"));
		
		//load data from database
		AccountService myservice=new AccountService();
		tableView.setItems(myservice.getAccountsGeneralized());
		
		//set cells of table to be editable
		tableView.setEditable(true);
		
		//we should give the user the possibility to choose from a choice box
		AgencyService myservice2=new AgencyService();
		ObservableList<Integer> agenciesIds=FXCollections.observableArrayList();
		agenciesIds.addAll(myservice2.getAgenciesIds());
		id_agenceColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(agenciesIds));
		
		EmployeeService myserive3=new EmployeeService();
		ObservableList<Integer> employeesIds=FXCollections.observableArrayList();
		employeesIds.addAll(myserive3.getEmployeesIds());
		id_employeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(employeesIds));
		
		ObservableList<String> AccountTypes=FXCollections.observableArrayList();
		AccountTypes.add("C");
		AccountTypes.add("R");
		type_compteColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(AccountTypes));
		
		taux_interetColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		SumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		
	}
	
	@FXML
	public void changeIdAgencyCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		CompteGeneralized Accountselected = tableView.getSelectionModel().getSelectedItem();
		Accountselected.setId_agence((int) edittedCell.getNewValue());
		
		//then we should change the actual data in the database
		AccountService myservice=new AccountService();
		myservice.UpdateAccount(Accountselected);	
		
	}
	
	@FXML
	public void changeIdEmployeeCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		CompteGeneralized Accountselected = tableView.getSelectionModel().getSelectedItem();
		Accountselected.setId_employe((int) edittedCell.getNewValue());
		
		//then we should change the actual data in the database
		AccountService myservice=new AccountService();
		myservice.UpdateAccount(Accountselected);	
	}
	
	@FXML
	public void changeTypeAccountCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		CompteGeneralized Accountselected = tableView.getSelectionModel().getSelectedItem();
		String str=(String)edittedCell.getNewValue();
		Accountselected.setType_compte(str.charAt(0));
		
		//then we should change the actual data in the database
		AccountService myservice=new AccountService();
		myservice.UpdateAccount(Accountselected);	
	}
	
	@FXML
	public void changeInterestRateCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {

		CompteGeneralized Accountselected = tableView.getSelectionModel().getSelectedItem();
		taux_interetColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));					
		float InterestRate=(float)edittedCell.getNewValue();
		if(Accountselected.getType_compte()=='C') {
			InterestRate=0;
		}
		else if(InterestRate>1){
			InterestRate=1;
		}
		else if(InterestRate<0){
			InterestRate=0;
		}
		Accountselected.setTauxInteret(InterestRate);
		
		//then we should change the actual data in the database
		AccountService myservice=new AccountService();
		myservice.UpdateAccount(Accountselected);	
	}
	
	@FXML
	public void changeSumCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		CompteGeneralized Accountselected = tableView.getSelectionModel().getSelectedItem();
		int Sum=(int)edittedCell.getNewValue();
		Accountselected.setMontant(Sum);
		
		//then we should change the actual data in the database
		AccountService myservice=new AccountService();
		myservice.UpdateAccount(Accountselected);	
		
	}
	
	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void ClientUpdateBtn() throws IOException {
		
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		Main.showStageModifyclients(stage);
	}
	

}
