package app.views;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class InterfaceRHController {

	
	@FXML
	private void EmployeeAddBtn() throws IOException {
		Main.showStageAddNewEmployee();
	}

	@FXML
	private void DeleteEmployeeBtn() throws IOException {
		Main.showStageDeleteEmployee();
	}
	
	@FXML
	private void ModifyEmployeeBtn() throws IOException {
		Main.showStageModifyEmployee();
	}

	@FXML
	private void goHome() throws IOException {
		Main.showMainItems();
	}
	
	@FXML
	private void gointerfaceEmployee() throws IOException {
		Main.interfaceEmployee();
	}
	
	@FXML
	private void gointerfaceRH() throws IOException {
		Main.interfaceRH();
	}

	@FXML
	private void gointerfaceClient() throws IOException {
		Main.interfaceClient();
	}
}
