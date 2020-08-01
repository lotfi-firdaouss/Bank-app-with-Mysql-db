package app.views;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainController {
	
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
	
	@FXML
	private void goHome() throws IOException {
		Main.showMainItems();
	}

}
