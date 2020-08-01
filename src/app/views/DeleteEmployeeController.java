package app.views;

import java.io.IOException;
import java.sql.SQLException;
import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.EmployeeService;

public class DeleteEmployeeController {

	ObservableList<Integer> EmployeesID = FXCollections.observableArrayList();
	ObservableList<Integer> mySelectedEmployeesIDs = FXCollections.observableArrayList();
	ObservableList<String> items = FXCollections.observableArrayList();

	// fields
	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private CheckComboBox<Integer> EmployeesIds;

	@FXML
	private Button cancelButton;

	@FXML
	private Label labelResponse;

	// methods
	public void initialize() throws IOException, ClassNotFoundException, SQLException {
		// getting Employee list from Employees.json file
		EmployeeService myservice=new EmployeeService();
		EmployeesID.addAll(myservice.getEmployeesIds());
		// finally initialyzing our checkcombobox list with the list of ids
		EmployeesIds.getItems().addAll(EmployeesID);

		// populate our list view
		items.addAll(myservice.getEmployeesinfo());
		list.setItems(items);
	}

	public void reset() throws IOException, ClassNotFoundException, SQLException {
		EmployeeService myservice=new EmployeeService();
		EmployeesID.addAll(myservice.getEmployeesIds());
		EmployeesIds.getItems().addAll(EmployeesID);

		// populate our list view
		items.clear();
		items.addAll(myservice.getEmployeesinfo());
		list.setItems(items);
	}

	public ObservableList<Integer> getSelectedEmployees() {
		ObservableList<Integer> mySelectedEmployeesIDs = EmployeesIds.getCheckModel().getCheckedItems();
		return mySelectedEmployeesIDs;
	}

	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void DeleteEmployee() throws IOException, ClassNotFoundException, SQLException {
		// get ids of Employees to be deleted
		mySelectedEmployeesIDs = getSelectedEmployees();
		if (mySelectedEmployeesIDs.isEmpty()) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			EmployeeService myservice = new EmployeeService();
			for (int id : mySelectedEmployeesIDs) {
				myservice.DeleteEmployee(id);
			}

			// comment of success to the user
			labelResponse.setTextFill(Color.YELLOWGREEN);
			labelResponse.setText("Employee deleted successfully ! ");

			// reseting fields
			EmployeesIds.getCheckModel().clearChecks();
			reset();
		}
	}
}
