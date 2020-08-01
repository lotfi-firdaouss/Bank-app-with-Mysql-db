package app.views;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;

import entities.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AgencyService;
import services.EmployeeService;

public class AddNewEmployeeController {
	ObservableList<Integer> agenciesID = FXCollections.observableArrayList();

	// fields
	@FXML
	private TextField EmployeeLastName;

	@FXML
	private TextField EmployeeFirstName;

	@FXML
	private ChoiceBox<Integer> EmployeeAgency;

	@FXML
	private DatePicker EmployeeHiringDate;

	// buttons
	@FXML
	public Button cancelButton;

	@FXML
	public Label labelResponse;

	// initialize method
	@FXML
	public void initialize() throws IOException, ClassNotFoundException, SQLException {
		agenciesID.clear();
		System.out.println(agenciesID);
		//We need to get agencies id's from the agency table
		AgencyService myservice=new AgencyService();
		agenciesID.addAll(myservice.getAgenciesIds());
		System.out.println(agenciesID);
		EmployeeAgency.setValue(1);
		EmployeeAgency.setItems(agenciesID);
		
	}

	// handlers (of buttons)
	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void AddEmployee() throws IOException, ClassNotFoundException, SQLException {
		if (EmployeeLastName.getText().isEmpty() || EmployeeFirstName.getText().isEmpty()
				|| EmployeeAgency.getValue() == null || EmployeeHiringDate.getValue() == null) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			String lastName = EmployeeLastName.getText();
			String firstName = EmployeeFirstName.getText();

			// we try to get an agency among those already definned
			int agencyid=(int) EmployeeAgency.getValue();

			// we get the locatedate from the datepicker in the fxml file and then convert it into "sql.date" date type object
			LocalDate localDate = EmployeeHiringDate.getValue(); //the value that we get from the datepicker
			//is actually a localDate
			Date Hiringdate = Date.valueOf(localDate); 
			//Date Hiringdate=EmployeeHiringDate.getValue();

			// now we finally create an employee object to save in our employee.json file
			Employe myEmployee = new Employe(lastName,firstName,agencyid,Hiringdate);
			EmployeeService myService = new EmployeeService();
			myService.addEmployee(myEmployee);

			// comment to the user
			labelResponse.setTextFill(Color.GREENYELLOW	);
			labelResponse.setText("Employee Added successfully ! ");

			// reseting the fields
			EmployeeFirstName.setText("");
			EmployeeLastName.setText("");
			EmployeeAgency.setValue(1);
			EmployeeHiringDate.setValue(null);

		}
	}

}
