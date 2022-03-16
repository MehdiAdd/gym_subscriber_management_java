package control;

import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import Model.ConnexionDB;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

	double xOffset = 0;
	double yOffset = 0;
    Stage stg;
    private Statement st=null;
	@Override
	public void start(Stage stg) throws Exception {
		try {
			ConnexionDB.getFirstconnection();
			 st = (Statement) (ConnexionDB.cnx).createStatement();
			ResultSet res=st.executeQuery("select schema_name from information_schema.schemata where schema_name='menouali'");
			if(!res.next()) {
			int nombre =st.executeUpdate("create database if not exists menouali");
			System.out.println(nombre);
			ConnexionDB.cnx.close();
			
				ConnexionDB.getconnection();
				 st = (Statement) (ConnexionDB.cnx).createStatement();
				 st.executeUpdate("create table abonne(id int primary key AUTO_INCREMENT,nom varchar(30),prenom varchar(30),tele varchar(15),paiement double)");
				 st.executeUpdate("create table date(idDate int primary key AUTO_INCREMENT,date Date,idAbonne int, FOREIGN key (idAbonne) references abonne(id))");
				 st.executeUpdate("create table password(idPass int primary key AUTO_INCREMENT,password varchar(30))");
				 st.executeUpdate("insert into password(password) values(\"menouali\")");
				 ConnexionDB.cnx.close();
			}
		Parent root=FXMLLoader.load(getClass().getResource("/xml/Main.fxml"));
		Scene scene=new Scene(root);
		stg.setScene(scene);
		stg.getIcons().add(new Image(getClass().getResource("/images/icon.png").toString()))	;	
		stg.setResizable(false);
		stg.centerOnScreen();
		stg.initStyle(StageStyle.UNDECORATED);
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		// set mouse drag
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stg.setX(event.getScreenX() - xOffset);
				stg.setY(event.getScreenY() - yOffset);
			}
		});
		stg.show();
		
		
	}catch(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
