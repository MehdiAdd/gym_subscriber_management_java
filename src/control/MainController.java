package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Abonne;
import Model.Traitement;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML
	private Button adminBotton, connectButton;
	@FXML
	private Button idBtn;
	@FXML
	private PasswordField passwordField;
	@FXML
	private AnchorPane scene1;
	@FXML
	private AnchorPane adminPane;
	@FXML
	private AnchorPane se;
	@FXML
	private TextField idField;
	@FXML
	Label nomLabel, prenomLabel, dateInscriptionLabel, dernierPayementLabel, etatPayeLabel, notFound;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("static-access")
	@FXML
	private void onEnter() {
		Traitement t = new Traitement();
		int id = -1;
		System.out.println("ok");
		try {
			id = Integer.parseInt(idField.getText());

		
		
		Abonne abonne = t.getAbonne(id);
		
		if (abonne != null) {
			notFound.setVisible(false);
			nomLabel.setText(abonne.getNom());
			prenomLabel.setText(abonne.getPrenom());
			if (abonne.getDate().size() > 0) {
				dateInscriptionLabel.setText(abonne.getDate().get(abonne.getDate().size() - 1).toString());
				dernierPayementLabel.setText(abonne.getDate().get(0).toString());
				if (t.isPaye(abonne) == 1)
					etatPayeLabel.setText("Payé");
				else
					etatPayeLabel.setText("Non Payé");
			} else {
				dateInscriptionLabel.setText("------");
				dernierPayementLabel.setText("------");
			}
		} else {
			notFound.setVisible(true);
			nomLabel.setText("------");
			prenomLabel.setText("------");
			dateInscriptionLabel.setText("------");
			dernierPayementLabel.setText("------");
			etatPayeLabel.setText("");
		}} catch (Exception e) {
		  	
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Alert");
					alert.setHeaderText(null);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
		}
	}

	@FXML
	private void bottonAdminClick(ActionEvent e) throws IOException {
		@SuppressWarnings("unused")
		Parent root = FXMLLoader.load(getClass().getResource("/xml/Main.fxml"));
		@SuppressWarnings("unused")
		Scene scene = scene1.getScene();
		idBtn.setOpacity(0);
		idBtn.setVisible(true);

		// adminPane.setLayoutX(scene1.getWidth());
		adminPane.setVisible(true);

		/*
		 * Timeline time=new Timeline(); KeyValue kv=new
		 * KeyValue(searchPane.translateXProperty(),390,Interpolator.EASE_IN); KeyFrame
		 * kf=new KeyFrame(Duration.seconds(1),kv); time.getKeyFrames().add(kf);
		 * time.play();
		 */
		TranslateTransition translateTransition = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition.setNode(adminPane);

		// Setting the value of the transition along the x axis.
		translateTransition.setFromX(scene1.getWidth() / 2);
		translateTransition.setToX(0);

		// Setting the cycle count for the transition
		translateTransition.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition.setAutoReverse(false);

		// Playing the animation
		translateTransition.play();

		TranslateTransition translateTransition2 = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition2.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition2.setNode(se);

		// Setting the value of the transition along the x axis.

		translateTransition2.setFromX(0);
		translateTransition2.setToX(-600);

		// Setting the cycle count for the transition
		translateTransition2.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition2.setAutoReverse(false);

		// Playing the animation
		translateTransition2.play();
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1000));
		fade.setNode(adminBotton);
		fade.setFromValue(0.9);
		fade.setToValue(0);
		fade.play();
		fade.setOnFinished((event) -> adminBotton.setVisible(false));
		idBtn.setVisible(true);
		FadeTransition fade2 = new FadeTransition();
		fade2.setDuration(Duration.millis(1000));
		fade2.setNode(idBtn);
		fade2.setFromValue(0);
		fade2.setToValue(0.9);
		fade2.play();

		System.out.print("ok");
	}

	@FXML
	private void idBottonClick(ActionEvent e) throws IOException {

		@SuppressWarnings("unused")
		Scene scene = scene1.getScene();
		adminBotton.setOpacity(0);
		adminBotton.setVisible(true);

		// adminPane.setLayoutX(scene1.getWidth());
		adminPane.setVisible(true);

		TranslateTransition translateTransition = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition.setNode(adminPane);

		// Setting the value of the transition along the x axis.
		translateTransition.setFromX(0);
		translateTransition.setToX(scene1.getWidth() / 2);

		// Setting the cycle count for the transition
		translateTransition.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition.setAutoReverse(false);

		// Playing the animation
		translateTransition.play();

		TranslateTransition translateTransition2 = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition2.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition2.setNode(se);

		// Setting the value of the transition along the x axis.

		translateTransition2.setFromX(-600);
		translateTransition2.setToX(0);

		// Setting the cycle count for the transition
		translateTransition2.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition2.setAutoReverse(false);

		// Playing the animation

		translateTransition2.play();
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1000));
		fade.setNode(adminBotton);
		fade.setFromValue(0);
		fade.setToValue(0.9);
		fade.play();
		// fade.setOnFinished((event)->idBtn.setVisible(false));
		FadeTransition fade2 = new FadeTransition();
		fade2.setDuration(Duration.millis(1000));
		fade2.setNode(idBtn);
		fade2.setFromValue(0.9);
		fade2.setToValue(0);
		fade2.play();
		fade2.setOnFinished((event) -> idBtn.setVisible(false));
		System.out.print("ok");
	}

	@FXML
	public void connectClick() {

		Traitement t = new Traitement();
		try {
		if (t.connect(passwordField.getText()) == 1) {
			Stage stg1 = new Stage();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/xml/table.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stg1.getIcons().add(new Image(getClass().getResource("/images/icon.png").toString()))	;
			Scene scene = new Scene(root);
			stg1.setScene(scene);
			stg1.setResizable(true);
			stg1.centerOnScreen();
			stg1.alwaysOnTopProperty();
			stg1.initStyle(StageStyle.DECORATED);
			stg1.show();
			passwordField.clear();
			
		}else {
			passwordField.clear();
		}}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Alert");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	public void exitClick() {
		System.exit(0);
	}

}
