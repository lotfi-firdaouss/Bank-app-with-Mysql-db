package app.views;

import java.io.IOException;
import java.sql.SQLException;

import app.Main;
import entities.Compte;
import entities.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.AccountService;

public class InterfaceClientController {
	Main main;

	ObservableList<Integer> AccountsID = FXCollections.observableArrayList();
	
	@FXML TextField Amount_money;
	@FXML ChoiceBox<Integer> AccountId;
	
	@FXML Label labelResponse;
	
	@FXML Button withdraw;
	@FXML Button deposit;
	
	
	public void initialize() throws ClassNotFoundException, SQLException{
		AccountService myservice=new AccountService();
		AccountsID.addAll(myservice.getComptesIds("Courant"));
		AccountsID.addAll(myservice.getComptesIds("Renumere"));
		AccountId.getItems().addAll(AccountsID);
		
	}
	
	@FXML
	public void withdrawButtonHandler() throws ClassNotFoundException, SQLException {
		if(AccountId.getValue()==null || Amount_money.getText().equals("")) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		}else {
			int account_id=(int) AccountId.getValue();
			int amount=Integer.parseInt(Amount_money.getText());
			
			AccountService myservice=new AccountService();
			Compte selectedCompte=myservice.GetAppropriateAccount(account_id);
			
			if(amount>selectedCompte.getMontant()) {
				labelResponse.setTextFill(Color.CORAL);
				labelResponse.setText("Operation failed , the amount specified exceedes the sum of the account !");
			}
			else if(amount<=0){
				labelResponse.setTextFill(Color.CORAL);
				labelResponse.setText("Operation failed , the amount specified should be positive !");
			}
			else {
				//we should be doing two thing withdrawing the amount specified from the selected
				//account and creating the operation
				//which mean updating the column montant_compte of the table comptes and inserting an operation
				//in the operations table
				int newMontantCompte=selectedCompte.getMontant()-amount;
				selectedCompte.setMontant(newMontantCompte);
				
				AccountService myservice1=new AccountService();
				myservice1.UpdateAccountSum(selectedCompte);
				
				Operation newOp=new Operation(selectedCompte.getId_compte(),amount,'W');
				myservice1.AddOperation(newOp);
				
				// comment of success to the user
				labelResponse.setTextFill(Color.GREENYELLOW);
				labelResponse.setText("Operation succeeded ! ");
			}

		}
	}
	
	@FXML
	public void depositButtonHandler() throws ClassNotFoundException, SQLException {
		if(AccountId.getValue()==null || Amount_money.getText().equals("")) {
			labelResponse.setTextFill(Color.CORAL);
			labelResponse.setText("All fields are requirred !");
		}else {
			int account_id=(int) AccountId.getValue();
			int amount=Integer.parseInt(Amount_money.getText());
			
			AccountService myservice=new AccountService();
			Compte selectedCompte=myservice.GetAppropriateAccount(account_id);
			
			if(amount<=0){
				labelResponse.setTextFill(Color.CORAL);
				labelResponse.setText("Operation failed , the amount specified should be positive !");
			}
			else {
				int newMontantCompte=selectedCompte.getMontant()+amount;
				selectedCompte.setMontant(newMontantCompte);
				
				AccountService myservice1=new AccountService();
				myservice1.UpdateAccountSum(selectedCompte);
				
				Operation newOp=new Operation(selectedCompte.getId_compte(),amount,'D');
				myservice1.AddOperation(newOp);
				
				// comment of success to the user
				labelResponse.setTextFill(Color.GREENYELLOW);
				labelResponse.setText("Operation succeeded ! ");
			}

		}
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
	
	@FXML
	private void goHome() throws IOException {
		Main.showMainItems();
	}

}
