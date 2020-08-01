package app.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.controlsfx.control.CheckComboBox;

import app.Main;
import entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AccountService;
import services.ClientService;

public class UpdateClientsController {

	ObservableList<Integer> AccountsID = FXCollections.observableArrayList();
	ObservableList<Integer> mySelectedAccountsIDs = FXCollections.observableArrayList();

	ObservableList<String> items = FXCollections.observableArrayList();

	// fields
	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private CheckComboBox<Integer> AccountsIds;

	@FXML
	private TextField compteId;

	@FXML
	private Button cancelButton;

	@FXML
	private Label labelResponse;

	// methods
	public void initialize() throws ClassNotFoundException, SQLException {
		ArrayList<Client> Accounts = ClientService.getClient();

		AccountsIds.getItems().clear();
		AccountsID = FXCollections.observableArrayList();
		for (Client cl : Accounts) {
			// System.out.println(cl.getClass());
			AccountsID.add(cl.getId_personne());
		}
		AccountsIds.getItems().addAll(AccountsID);

		items.clear();
		for (Client cl : Accounts) {
			items.add(cl.toString());
		}
		list.setItems(items);
	}

	public ObservableList<Integer> getSelectedAccounts() {
		ObservableList<Integer> mySelectedAccountsIDs = AccountsIds.getCheckModel().getCheckedItems();
		return mySelectedAccountsIDs;
	}

	@FXML
	public void handleCancelButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		Main.showStageModifyAccount(stage);
	}

	@FXML
	public void UpdateAccount() throws ClassNotFoundException, SQLException {
		// get ids of Accounts to be deleted
		mySelectedAccountsIDs = getSelectedAccounts();
		ArrayList<Integer> myClients = new ArrayList<Integer>();
		if (mySelectedAccountsIDs.isEmpty() || compteId.getText().isEmpty()) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			for (int id : mySelectedAccountsIDs) {

				myClients.add(id);
			}
			int id = Integer.parseInt(compteId.getText());
			AccountService myservice = new AccountService();
			myservice.UpdateClients(myClients, id);

			// comment of success to the user
			labelResponse.setTextFill(Color.YELLOWGREEN);
			labelResponse.setText("Account updated successfully ! ");

			// reseting fields
			AccountsIds.getCheckModel().clearChecks();
			// reset();
		}
	}

}
