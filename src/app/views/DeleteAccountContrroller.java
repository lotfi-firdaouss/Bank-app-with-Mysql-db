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
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AccountService;

public class DeleteAccountContrroller {

	ObservableList<Integer> AccountsID = FXCollections.observableArrayList();
	ObservableList<Integer> mySelectedAccountsIDs = FXCollections.observableArrayList();
	String TypeAccount;

	ObservableList<String> items = FXCollections.observableArrayList();

	// fields
	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private RadioButton RadioCourant;

	@FXML
	private RadioButton RadioRenumere;

	@FXML
	private CheckComboBox<Integer> AccountsIds;

	@FXML
	private Button cancelButton;

	@FXML
	private Label labelResponse;

	// methods
	public void initialize() throws IOException, ClassNotFoundException, SQLException {

	}
	
	public void set() throws ClassNotFoundException, SQLException {
		if (RadioCourant.isSelected()) {
			TypeAccount = "Courant";
		}else {
			TypeAccount="Renumere";
		}
		
		AccountsIds.getItems().clear();
		
		AccountService myservice=new AccountService();
		AccountsIds.getItems().addAll(myservice.getComptesIds(TypeAccount));

		items.clear();
		items.addAll(myservice.getComptesInfo(TypeAccount));
		list.setItems(items);
	}

	public ObservableList<Integer> getSelectedAccounts() {
		ObservableList<Integer> mySelectedAccountsIDs = AccountsIds.getCheckModel().getCheckedItems();
		return mySelectedAccountsIDs;
	}

	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void DeleteAccount() throws IOException, ClassNotFoundException, SQLException {
		// get ids of Accounts to be deleted
		mySelectedAccountsIDs = getSelectedAccounts();
		if (mySelectedAccountsIDs.isEmpty() || TypeAccount.isEmpty()) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			AccountService myservice = new AccountService();
			for (int id : mySelectedAccountsIDs) {
				myservice.DeleteAccount(id);
			}

			// comment of success to the user
			labelResponse.setTextFill(Color.YELLOWGREEN);
			labelResponse.setText("Account deleted successfully ! ");

			// reseting fields
			AccountsIds.getCheckModel().clearChecks();
			// reset();
		}
	}

}
