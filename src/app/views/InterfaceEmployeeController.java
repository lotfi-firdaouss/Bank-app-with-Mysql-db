package app.views;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class InterfaceEmployeeController {

	@FXML
	private void ClientAddBtn() throws IOException {
		Main.showStageAddNewClient();
	}
	
	@FXML
	private void AccountAddBtn() throws IOException {
		Main.showStageAddNewAccount();
	}

	@FXML
	private void DeleteClientBtn() throws IOException {
		Main.showStageDeleteClient();
	}

	@FXML
	private void DeleteAccountBtn() throws IOException {
		Main.showStageDeleteAccount();
	}
	
	@FXML
	private void ModifyClientBtn() throws IOException {
		Main.showStageModifyClient();
	}
	
	@FXML
	private void ModifyAccountBtn() throws IOException {
		Main.showStageModifyAccount();
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
