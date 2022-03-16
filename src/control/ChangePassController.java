package control;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Traitement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ChangePassController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ancienTextField.textProperty().bindBidirectional(ancienPassField.textProperty());
		nouveauTextField.textProperty().bindBidirectional(nouveauPassField.textProperty());
		confirmeTextField.textProperty().bindBidirectional(confirmePassField.textProperty());
	}
	    @FXML
	    private Button btnAjouter;

	    @FXML
	    private PasswordField ancienPassField;

	    @FXML
	    private PasswordField nouveauPassField;

	    @FXML
	    private PasswordField confirmePassField;

	    @FXML
	    private CheckBox afficherCheck;

	    @FXML
	    private Label labelError;

	    @FXML
	    private Label labelSucces;

	    @FXML
	    private TextField ancienTextField;

	    @FXML
	    private TextField confirmeTextField;

	    @FXML
	    private TextField nouveauTextField;
	    
	    @FXML
	    void onAfficherCheck(ActionEvent event) {
           if(afficherCheck.isSelected()) {
        	   ancienTextField.setVisible(true);
        	   confirmeTextField.setVisible(true);
        	   nouveauTextField.setVisible(true);
        	   ancienPassField.setVisible(false);
        	   confirmePassField.setVisible(false);
        	   nouveauPassField.setVisible(false); 
           }  else {
        	   ancienTextField.setVisible(false);
        	   confirmeTextField.setVisible(false);
        	   nouveauTextField.setVisible(false);
        	   ancienPassField.setVisible(true);
        	   confirmePassField.setVisible(true);
        	   nouveauPassField.setVisible(true); 
           } 
           }

	    @FXML
	    void onBtnAppliquer(ActionEvent event) {
	    	try {
	    	if(confirmePassField.getText().equals(nouveauPassField.getText())) {
	    		Traitement t=new Traitement();
	    	int n=	t.changePass(ancienPassField.getText(),nouveauPassField.getText());
	    		if(n==1) 
	    		{
	    			labelSucces.setVisible(true);
	    			labelError.setVisible(false);
	    		}else {
	    			labelSucces.setVisible(false);
	    			labelError.setVisible(true);
	    		}
	    	}else {
    			labelSucces.setVisible(false);
    			labelError.setVisible(true);
    		}}catch(Exception e) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Alert");
    			alert.setHeaderText(null);
    			alert.setContentText(e.getMessage());
    			alert.showAndWait();
    		}

	    }

}
