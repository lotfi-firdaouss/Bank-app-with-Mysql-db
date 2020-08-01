package app.views;

import java.sql.SQLException;

import entities.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.ClientService;

public class ModifyClientController {

	//configure the table
	@FXML private TableView<Client> tableView;
	@FXML private TableColumn<Client , Integer> id_client;
	@FXML private TableColumn<Client , String> nom;
	@FXML private TableColumn<Client , String > prenom;
	@FXML private TableColumn<Client , String> adress;
	
	@FXML
	private Button cancelButton;
	
	public void initialize() throws ClassNotFoundException, SQLException {
		//set up the columns in the table
		id_client.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_personne"));
		nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		adress.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
		
		//load dummy data
		ClientService myservice=new ClientService();
		tableView.setItems(myservice.getClients()); //this method should return an observable list of clients
		//saved in the database
		
		//update the table to allow for the lastname column to be editable
		tableView.setEditable(true); //we set the table to be editable , then the columns
		nom.setCellFactory(TextFieldTableCell.forTableColumn());
		prenom.setCellFactory(TextFieldTableCell.forTableColumn());
		adress.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	@FXML
	public void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	/*this method will allow the user to double click on a cell and update the first
	 * name of the person
	 */
	
	@FXML
	public void changeLastNameCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		//first we need to change the table view that we see in the gui
		Client clientselected = tableView.getSelectionModel().getSelectedItem();
		//System.out.println(clientselected);
		clientselected.setNom(edittedCell.getNewValue().toString());
		//System.out.println(clientselected);
		
		//then we should change the actual data in the database
		ClientService myservice=new ClientService();
		myservice.UpdateClient(clientselected);	
	}
	
	@FXML
	public void changeFirstNameCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		Client clientselected = tableView.getSelectionModel().getSelectedItem();
		clientselected.setPrenom(edittedCell.getNewValue().toString());
		
		ClientService myservice=new ClientService();
		myservice.UpdateClient(clientselected);	
	}
	
	@FXML
	public void changeAdressCellEvent(CellEditEvent<?, ?> edittedCell) throws ClassNotFoundException, SQLException {
		Client clientselected = tableView.getSelectionModel().getSelectedItem();
		clientselected.setAdresse(edittedCell.getNewValue().toString());
		
		ClientService myservice=new ClientService();
		myservice.UpdateClient(clientselected);
	}
	

	

}
