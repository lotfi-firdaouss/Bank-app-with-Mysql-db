package app.views;

import java.io.IOException;
import java.sql.SQLException;
import org.controlsfx.control.CheckComboBox;

import entities.CompteCourant;
import entities.CompteRenumere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AccountService;
import services.ClientService;
import services.EmployeeService;

public class AddNewAccountController {

	// make list to initialize the combo/choice boxes
	ObservableList<Integer> ClientsID = FXCollections.observableArrayList();
	ObservableList<Integer> EmployeesID = FXCollections.observableArrayList();
//	ObservableList<Integer> AgenciesID = FXCollections.observableArrayList();
	ObservableList<String> TypesOfAccounts = FXCollections.observableArrayList("Renumere", "Courant");

	ObservableList<Integer> mySelectedClientsIDs = FXCollections.observableArrayList();

	ObservableList<String> items = FXCollections.observableArrayList();
	// fields
	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private CheckComboBox<Integer> AccountClients;

	@FXML
	private ChoiceBox<Integer> AccountEmployee;

//	@FXML
//	private ChoiceBox AccountAgency;

	@FXML
	private ChoiceBox<String> AccountType;

	@FXML
	private TextField AccountInterestRate;
	
	@FXML
	private TextField InitialSum;

	// buttons
	@FXML
	private Button cancelButton;

	// labelresponse
	@FXML
	private Label labelResponse;

	@FXML
	private Button showClientsInfo;

	// initialize method
	public void initialize() throws IOException, ClassNotFoundException, SQLException {

		AccountEmployee.setValue(1);
		AccountType.setValue("Renumere");
		AccountType.setItems(TypesOfAccounts);

		//we need to fill ClientsID list by the ids of the clients retreived from database
		ClientService myservice=new ClientService();
		ClientsID.addAll(myservice.getClientsIds());
		AccountClients.getItems().addAll(ClientsID);
		

		// now we'll under go the same procedure for the AccountEmployee choice box
		EmployeeService myservice2=new EmployeeService();
		EmployeesID.addAll(myservice2.getEmployeesIds());
		AccountEmployee.setItems(EmployeesID);

	}

	// handlers (of buttons)
	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void handleAccountType_InterestRate() {
		if (AccountType.getValue() == "Courant") {
			AccountInterestRate.setDisable(true);// greyed out
		} else {
			AccountInterestRate.setDisable(false);// disable graying out
		}
	}

	public void showClientsInfo() throws IOException, ClassNotFoundException, SQLException {
		
		ClientService myservice=new ClientService();
		items.clear();
		items.setAll(myservice.getClientsInfo());
		list.setItems(items);
		
	}

	public ObservableList<Integer> getSelectedClients() {
		ObservableList<Integer> mySelectedClientsIDs = AccountClients.getCheckModel().getCheckedItems();
		return mySelectedClientsIDs;
	}

	public void GetSelectedClients() {
		System.out.println(AccountClients.getCheckModel().getCheckedItems().listIterator());
	}

	@FXML
	public void AddAccount() throws IOException, ClassNotFoundException, SQLException {
		// GetSelectedClients();
		mySelectedClientsIDs = getSelectedClients();

		// we should firstly check that all fields are full otherwise nothing will run
		// and the user will get an warning
		if (mySelectedClientsIDs.isEmpty() || AccountEmployee.getValue() == null || AccountType.getValue() == null
				|| (AccountType.getValue() == "Renumere" && AccountInterestRate.getText().isEmpty())) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			//we should find the responsible employee's id
			int resp_empl_id=(int)AccountEmployee.getValue();
			
			//and we should find the corrensponding agency
			EmployeeService myservice2=new EmployeeService();
			int resp_agence_id=myservice2.getCorrespondantAgencyId(resp_empl_id);
			
			//get the initial sum of the account
			int myInitialSum=Integer.parseInt(InitialSum.getText());
			
			//declare the interestrate variable
			float myInterestRate=0;


			AccountService myService = new AccountService();
			if (AccountType.getValue() == "Courant") {
				CompteCourant myAccount = new CompteCourant(resp_empl_id,resp_agence_id,myInitialSum);
				myService.addAccount(myAccount,mySelectedClientsIDs);
			} else {
				myInterestRate = Float.parseFloat(AccountInterestRate.getText());
				CompteRenumere myAccount = new CompteRenumere(resp_empl_id, resp_agence_id, myInterestRate,myInitialSum);
				myService.addAccount(myAccount,mySelectedClientsIDs);
			}

			// comment of success to the user
			labelResponse.setTextFill(Color.GREENYELLOW);
			labelResponse.setText("Account Added successfully ! ");

			// reseting the values of fields
			AccountEmployee.setValue(1);
			AccountType.setValue("Renumere");
			AccountInterestRate.setText("");
			InitialSum.setText("");
			AccountClients.getCheckModel().clearChecks();

		}

	}

}
