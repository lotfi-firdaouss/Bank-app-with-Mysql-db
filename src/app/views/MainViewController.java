package app.views;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainViewController {
	
	
	@FXML
	private void goHome() throws IOException {
		Main.showMainItems();
	}
	

}
