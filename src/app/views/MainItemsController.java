package app.views;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainItemsController {

	@FXML
	private void ClientAddBtn() throws IOException {
		Main.showStageAddNewClient();
	}

	@FXML
	private void DeleteClientBtn() throws IOException {
		Main.showStageDeleteClient();
	}

	@FXML
	private void EmployeeAddBtn() throws IOException {
		Main.showStageAddNewEmployee();
	}

	@FXML
	private void DeleteEmployeeBtn() throws IOException {
		Main.showStageDeleteEmployee();
	}

	@FXML
	private void AccountAddBtn() throws IOException {
		Main.showStageAddNewAccount();
	}

	@FXML
	private void DeleteAccountBtn() throws IOException {
		Main.showStageDeleteAccount();
	}

}
