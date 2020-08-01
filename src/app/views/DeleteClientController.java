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
import services.ClientService;

public class DeleteClientController {
	// create an obervable list to initialize the field
	ObservableList<Integer> ClientsID = FXCollections.observableArrayList();

	// create a list to retreive elements enterred by user
	ObservableList<Integer> mySelectedClientsIDs = FXCollections.observableArrayList();

	// creating our list view with informations on clients
	// ObservableList<String> items = FXCollections.observableArrayList("Single",
	// "Double", "Suite", "Family App");
	ObservableList<String> items = FXCollections.observableArrayList();

	// fields
	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private CheckComboBox<Integer> ClientsIds;

	@FXML
	private Button cancelButton;

	@FXML
	private Label labelResponse;

	// methods
	@FXML
	public void initialize() throws IOException, ClassNotFoundException, SQLException {
		// getting client list from database
		ClientService myservice1 = new ClientService();
		ClientsID.addAll(myservice1.getClientsIds());

		// initialyzing our checkcombobox list with the list of ids
		ClientsIds.getItems().addAll(ClientsID);

		// populate our list view
		items.addAll(myservice1.getClientsInfo());
		list.setItems(items);
	}

	public void reset() throws IOException, ClassNotFoundException, SQLException {

		ClientService myservice1 = new ClientService();
		ClientsID.addAll(myservice1.getClientsIds());
		ClientsIds.getItems().addAll(ClientsID);

		// populate our list view
		items.clear();
		items.addAll(myservice1.getClientsInfo());
		list.setItems(items);
	}

	public ObservableList<Integer> getSelectedClients() {
		ObservableList<Integer> mySelectedClientsIDs = ClientsIds.getCheckModel().getCheckedItems();
		return mySelectedClientsIDs;
	}

	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void DeleteClient() throws IOException, ClassNotFoundException, SQLException {
		// get ids of clients to be deleted
		mySelectedClientsIDs = getSelectedClients();
		if (mySelectedClientsIDs.isEmpty()) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		} else {
			ClientService myservice = new ClientService();
			for (int id : mySelectedClientsIDs) {
				myservice.DeleteClient(id);
			}

			// comment of success to the user
			labelResponse.setTextFill(Color.YELLOWGREEN);
			labelResponse.setText("Client deleted successfully ! ");

			// reseting fields
			ClientsIds.getCheckModel().clearChecks();
			reset();
		}
	}
}
