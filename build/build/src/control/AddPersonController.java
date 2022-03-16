package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Abonne;
import Model.Traitement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPersonController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	Button btnAjouter;
    @FXML
    TextField nomField,prenomField,teleField;
    @FXML
    Label ajouterLabel;
	@SuppressWarnings("unused")
	@FXML
	public void actionFermer() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/xml/AddPerson.fxml"));
		
	}
	@SuppressWarnings("unused")
	@FXML
	public void actionAjouter() {
		Abonne abonne=new Abonne();
		Traitement t=new Traitement();
		abonne.setNom(nomField.getText());
		abonne.setPrenom(prenomField.getText());
		abonne.setTele(teleField.getText());
		int n=t.inscription(abonne);
		ajouterLabel.setVisible(true);
		
	}
      
	@FXML
	public void onClickNom(){
		ajouterLabel.setVisible(false);
	}
}
