package control;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Abonne;
import Model.Traitement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ModifyPersonController implements Initializable {
	
	Abonne a;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		deleteDate.setGraphic(new ImageView(getClass().getResource("/images/deleteDate.png").toString()));
		a = tableController.abonne;
		idField.setText("" + a.getId());
		nomField.setText(a.getNom());
		prenomField.setText(a.getPrenom());
		teleField.setText(a.getTele());
		dernierPaiementField.setText(a.getDate().get(0).toString());
		ObservableList<Dates> data = FXCollections.observableArrayList();

		for (int i = 0; i < a.getDate().size(); i++) {
			data.add(new Dates(a.getIndexDate().get(i), a.getDate().get(i)));

		}
		iDDate.setCellValueFactory(new PropertyValueFactory<Dates, Integer>("id"));
		dates.setCellValueFactory(new PropertyValueFactory<Dates, Date>("date"));
		dateTable.setItems(data);
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
		}
	}

	@FXML
	Button btnAjouter,deleteDate;
	@FXML
	Button btnFermer;
	@FXML
	TableView<Dates> dateTable;
	@FXML
	TableColumn<Dates, Integer> iDDate;
	@FXML
	TableColumn<Dates, Date> dates;
	@FXML
	TextField idField, nomField, prenomField, teleField, dernierPaiementField;

	@FXML
	public void actionFermer() throws IOException {
		@SuppressWarnings("unused")
		Parent root = FXMLLoader.load(getClass().getResource("/xml/AddPerson.fxml"));

	}

	@FXML
	public void actionModifier() {

		try {
			a.setNom(nomField.getText());
			a.setPrenom(prenomField.getText());
			a.setTele(teleField.getText());
			@SuppressWarnings("unused")
			java.util.Date date1;

			java.util.Date dateM;

			dateM = new SimpleDateFormat("yyyy-MM-dd").parse(dernierPaiementField.getText());
			a.getDate().set(0, new java.sql.Date(dateM.getTime()));
			Traitement t=new Traitement();
			t.modifierAbonne(a);
			ObservableList<Dates> data = FXCollections.observableArrayList();
			for (int i = 0; i < a.getDate().size(); i++) {
				data.add(new Dates(a.getIndexDate().get(i), a.getDate().get(i)));

			}
			iDDate.setCellValueFactory(new PropertyValueFactory<Dates, Integer>("id"));
			dates.setCellValueFactory(new PropertyValueFactory<Dates, Date>("date"));
			dateTable.setItems(data);
		} catch (ParseException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("alert");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}
	@FXML
	public void onDeleteDate() {
		try {
		 Dates date= dateTable.getSelectionModel().getSelectedItem();
	     if(date!=null) {
	    
	     Traitement t=new Traitement();
        
        	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Étes-vous sùr de supprimer cette date ?");
		
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			int size=a.getDate().size();
			if(size>1) {
			t.supprimerDate(a,date.getId());
			a = t.getAbonne(a.getId());
			idField.setText("" + a.getId());
			nomField.setText(a.getNom());
			prenomField.setText(a.getPrenom());
			teleField.setText(a.getTele());
			dernierPaiementField.setText(a.getDate().get(0).toString());
			ObservableList<Dates> data = FXCollections.observableArrayList();

			for (int i = 0; i < a.getDate().size(); i++) {
				data.add(new Dates(a.getIndexDate().get(i), a.getDate().get(i)));

			}
			iDDate.setCellValueFactory(new PropertyValueFactory<Dates, Integer>("id"));
			dates.setCellValueFactory(new PropertyValueFactory<Dates, Date>("date"));
			dateTable.setItems(data);
			}else {
				Alert alertDate = new Alert(AlertType.ERROR);
				alertDate.setTitle("alert");
				alertDate.setHeaderText(null);
				alertDate.setContentText("Vous ne pouvez pas supprimer la dernière date");
				alertDate.show();
			}
			
			
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}else {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information ");
    	alert.setHeaderText(null);
    	alert.setContentText("Vous devez selectionnez une date");

    	alert.showAndWait();
    }
       
	} catch(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}
	}
}
