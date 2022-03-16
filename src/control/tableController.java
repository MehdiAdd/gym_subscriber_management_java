package control;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Abonne;
import Model.Traitement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class tableController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addPerson.setGraphic(new ImageView(getClass().getResource("/images/add.png").toString()));
		modifyPerson.setGraphic(new ImageView(getClass().getResource("/images/edit1.png").toString()));
		deletePerson.setGraphic(new ImageView(getClass().getResource("/images/delete.png").toString()));
		payer.setGraphic(new ImageView(getClass().getResource("/images/payer.png").toString()));
		refresh.setGraphic(new ImageView(getClass().getResource("/images/refresh.png").toString()));
	
		deleteAll.setGraphic(new ImageView(getClass().getResource("/images/vider.png").toString()));
		ObservableList<Abonne> data = FXCollections.observableArrayList();
		
        Traitement t=new Traitement();
        
		for(Abonne a:t.getAbonnes()){
			data.add(a);
			
		}
		
		id.setCellValueFactory(new PropertyValueFactory<Abonne, Integer>("Id"));
		nom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("prenom"));
		tele.setCellValueFactory(new PropertyValueFactory<Abonne, String>("tele"));
		dernierPaiem.setCellValueFactory(new PropertyValueFactory<Abonne, Date>("dernierPaiem"));
		etat.setCellValueFactory(new PropertyValueFactory<Abonne, String>("etat"));	
		
		 FilteredList<Abonne> filteredData = new FilteredList<>(data, b -> true);
			
			// 2. Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(abonne -> {
					// If filter text is empty, display all persons.
									
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (abonne.getNom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					} else if (abonne.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}else if ((""+abonne.getId()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}else if ((""+abonne.getDernierPaiem()).toLowerCase().equals(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}
					
					
					     else  
					    	 return false; // Does not match.
				});
			});
			
			// 3. Wrap the FilteredList in a SortedList. 
			SortedList<Abonne> sortedData = new SortedList<>(filteredData);
			
			// 4. Bind the SortedList comparator to the TableView comparator.
			// 	  Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			// 5. Add sorted (and filtered) data to the table.
			table.setItems(sortedData);
		//	table.getProperties()
			 /*etat.setCellFactory(new Callback<TableColumn<Abonne, String>, TableCell<Abonne, String>>() {
	           public TableCell call(TableColumn param) {
	               return new TableCell<Abonne, String>() {

	                   @Override
	                   public void updateItem(String item, boolean empty) {
	                       super.updateItem(item, empty);
	                       if (!isEmpty()) {
	                           
	                    	   if(item.equals("Payé")) 
	                        	   this.setStyle("-fx-background-color:green;");
	                           else if(item.equals("Non Payé"))
	                        	   this.setStyle("-fx-background-color:red;");
	                           setText(item);
	                       }
	                   }
	               };
	           }
	       });*/
		
	}

    @FXML
    private Button addPerson,payer;

    @FXML
    private TextField searchField;
    @FXML
    private Button modifyPerson;

    @FXML
    private Button deletePerson;

    @FXML
    private Button refresh;

   

    @FXML
    private Button deleteAll;

    @FXML
    private TableColumn<Abonne, Integer> id;

    @FXML
    private TableColumn<Abonne, String> nom,etat;

    @FXML
    private TableColumn<Abonne, String> prenom;

    @FXML
    private TableColumn<Abonne, String> tele;

    @FXML
    private TableColumn<Abonne, Date> dernierPaiem;
    @FXML
    private TableView<Abonne> table;
    
    public static Abonne abonne=null;
    @FXML  
    public void onAddPerson() {
    	Stage stg1 = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/xml/AddPerson.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stg1.setScene(scene);
		stg1.setResizable(false);
		stg1.centerOnScreen();
		stg1.alwaysOnTopProperty();
		stg1.initStyle(StageStyle.UTILITY);
		stg1.show();
    }
    @FXML
    public void onModifyPerson() {
    	
    	Abonne abn= table.getSelectionModel().getSelectedItem();
	     if(abn!=null) {
	     Traitement t=new Traitement();
	     abonne=t.getAbonne(abn.getId());
    	Stage stg1 = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/xml/ModifyPerson.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stg1.setScene(scene);
		stg1.setResizable(false);
		stg1.centerOnScreen();
		stg1.alwaysOnTopProperty();
		stg1.initStyle(StageStyle.UTILITY);
		stg1.show();
	    }else {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information ");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Vous devez selectionnez un abonné");

	    	alert.showAndWait();
	    }
	     
    }
    
    @FXML
    public void onRefresh() {
ObservableList<Abonne> data = FXCollections.observableArrayList();
		
        Traitement t=new Traitement();
        
		for(Abonne a:t.getAbonnes()){
			data.add(a);
			
		}
		
		id.setCellValueFactory(new PropertyValueFactory<Abonne, Integer>("Id"));
		nom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("prenom"));
		tele.setCellValueFactory(new PropertyValueFactory<Abonne, String>("tele"));
		dernierPaiem.setCellValueFactory(new PropertyValueFactory<Abonne, Date>("dernierPaiem"));
	
		 FilteredList<Abonne> filteredData = new FilteredList<>(data, b -> true);
			
			// 2. Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(abonne -> {
					// If filter text is empty, display all persons.
									
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (abonne.getNom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					} else if (abonne.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}else if ((""+abonne.getId()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}else if ((""+abonne.getDernierPaiem()).toLowerCase().equals(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}else if ((""+abonne.getEtat()).toLowerCase().equals(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}
					
					
					
					     else  
					    	 return false; // Does not match.
				});
			});
			
			// 3. Wrap the FilteredList in a SortedList. 
			SortedList<Abonne> sortedData = new SortedList<>(filteredData);
			
			// 4. Bind the SortedList comparator to the TableView comparator.
			// 	  Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			// 5. Add sorted (and filtered) data to the table.
			table.setItems(sortedData);
    }
    @FXML
    public void onDeletePerson() {
    	 
     try {
		     Abonne abonne= table.getSelectionModel().getSelectedItem();
		     if(abonne!=null) {
		     System.out.println(abonne.toString());
		     Traitement t=new Traitement();
             if(abonne!=null) {
            	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Étes-vous sùr de supprimer cet abonné ?");
			
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				t.supprimerAbonne(abonne.getId());
				Alert confirm = new Alert(AlertType.INFORMATION);
				confirm.setTitle("Information ");
				confirm.setHeaderText(null);
				confirm.setContentText("L'abonné "+abonne.getNom()+" "+abonne.getPrenom()+" est supprimé");

				confirm.showAndWait();
				onRefresh();
				
				
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
		}}else {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information ");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Vous devez selectionnez un abonné");

	    	alert.showAndWait();
	    }}catch(Exception e) {
	    	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Alert");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
	    }
           
		
		}
    
    
    @FXML
    public void onPayer() {
    	Abonne abonne= table.getSelectionModel().getSelectedItem();
	    if(abonne!=null) {
    	System.out.println(abonne.toString());
	     Traitement t=new Traitement();
	     t.payer(abonne.getId());
	     onRefresh();
	    }else {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information ");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Vous devez selectionnez un abonné");

	    	alert.showAndWait();
	    }
    }
    
    @FXML
    public void onDeleteAll() {
    	Traitement t=new Traitement();
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Vider La base de données");
    	dialog.setHeaderText("Étes vous sùr de vider la base de données(La suppression de tous les abonnés)");
    	dialog.setContentText("Confirmez en saisissant votre mot de passe");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    if(t.connect(result.get())==1) {
    	    	t.truncate();
    	    	Alert confirm = new Alert(AlertType.INFORMATION);
				confirm.setTitle("Information ");
				confirm.setHeaderText(null);
				confirm.setContentText("tous les abonnées sont supprimés avec succès ");

				confirm.showAndWait();
				onRefresh();
    	    }
    	    else {
    	    	Alert confirm = new Alert(AlertType.INFORMATION);
				confirm.setTitle("Information ");
				confirm.setHeaderText("Suppression est annulée");
				confirm.setContentText("Mot de passe est erroné ");

				confirm.showAndWait();
    	    }
    	}
    	
    	
    }
    @FXML
    public void onChangerPass(){
    	Stage stg2 = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/xml/ChangePass.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stg2.setScene(scene);
		stg2.setResizable(false);
		stg2.centerOnScreen();
		stg2.alwaysOnTopProperty();
		stg2.initStyle(StageStyle.UTILITY);
		stg2.show();
    }
    @FXML
    public void onSearch() {
/*ObservableList<Abonne> data = FXCollections.observableArrayList();
		
        Traitement t=new Traitement();
        
		for(Abonne a:t.getAbonnes()){
			data.add(a);
			
		}
		
		id.setCellValueFactory(new PropertyValueFactory<Abonne, Integer>("Id"));
		nom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("prenom"));
		tele.setCellValueFactory(new PropertyValueFactory<Abonne, String>("tele"));
		dernierPaiem.setCellValueFactory(new PropertyValueFactory<Abonne, Date>("dernierPaiem"));
		
		 FilteredList<Abonne> filteredData = new FilteredList<>(data, b -> true);
			
			// 2. Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(abonne -> {
					// If filter text is empty, display all persons.
									
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (abonne.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
						return true; // Filter matches first name.
					} else if (abonne.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					}else if (abonne.getId()+"".toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					}
					
					
					     else  
					    	 return false; // Does not match.
				});
			});
			
			// 3. Wrap the FilteredList in a SortedList. 
			SortedList<Abonne> sortedData = new SortedList<>(filteredData);
			
			// 4. Bind the SortedList comparator to the TableView comparator.
			// 	  Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			// 5. Add sorted (and filtered) data to the table.
			table.setItems(sortedData);
	*/	
    }
}
