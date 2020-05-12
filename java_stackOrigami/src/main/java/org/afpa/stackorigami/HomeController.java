package org.afpa.stackorigami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public TableView<User> table_user;
    @FXML
    public TableColumn<User,String> surname;
    @FXML
    public TableColumn<User,String> first_name;
    @FXML
    public TableColumn<User,String> phone;
    @FXML
    public TableColumn<User,String> type;
    @FXML
    public VBox fiche_user;
    @FXML
    public Label label_siret;
    @FXML
    public Label val_surname;
    @FXML
    public Label val_first_name;
    @FXML
    public Label val_phone;
    @FXML
    public Label val_mail;
    @FXML
    public Label val_adress;
    @FXML
    public Label val_type;
    @FXML
    public Label val_siret;

    public UserDAO userDAO = new UserDAO();

    public List<User> list_user = new ArrayList<User>();

    ObservableList<User> obs_list_user = FXCollections.observableArrayList();

    public HomeController() throws IOException{
    }

    /**
     * <b>maj_lst</b> est une méthode qui met à jour l'affichage de la liste des clients
     */
    public void maj_lst(){

        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));   // Jonction du tableau avec les données
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        table_user.setItems(obs_list_user);    // On indique au TableView quelle modèle observer pour trouver les données
    }

    /**
     * <b>initialize</b> est la méthode qui initialise la vue et les variables
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_user.setEditable(false); //rend la liste des clients non éditable
        list_user = userDAO.List_user();   //récupère la liste des clients
        obs_list_user.addAll(list_user);    //met la liste des clients dans obs_liste
        maj_lst();
    }

    /**
     * <b>actualise</b> est la méthode qui actualise le tableau
     */
    @FXML
    public void actualise(ActionEvent actionEvent) {
        maj_lst();  //met à jour la liste
    }

    /**
     * Méthode qui vérifie si un utilisateur est selectionné
     * @return  le numéro dans la liste
     */
    public int user_selected(){
        int i = table_user.getSelectionModel().getSelectedIndex(); //récupère l'index de l'utilisateur selectionné
        if(i==-1) { //s'il n'y a pas d'utilisateur
            /* On affiche une alerte */
            Alert alert = new Alert(Alert.AlertType.ERROR); //crée l'alerte
            alert.setContentText("Veuillez selectionner un utilisateur");   //set le message à afficher
            alert.show();   //affiche l'erreur
        }
        return i;
    }

    /**
     * <b>details</b> est la méthode qui affiche la fiche avec les détails de l'utilisateur selectionné
     * @param actionEvent
     */
    @FXML
    public void details(ActionEvent actionEvent) {
        int i = user_selected();
        if(i!=-1) { //s'il y en a un
            User user = new User(); //cree l'utilisateur
            user = obs_list_user.get(i);    //récupère les valeurs de l'utilisateur
            /*met les valeurs de l'utilisateur sur la fiche*/
            val_surname.setText(user.getSurname());
            val_first_name.setText(user.getFirst_name());
            val_mail.setText(user.getMail());
            val_phone.setText(user.getPhone());
            val_adress.setText(user.getAdress());
            if(user.getType()==1) {
                val_type.setText("Particulier");
                label_siret.setVisible(false);   //cache le label siret
                val_siret.setVisible(false); //cache la valeur du siret
            }else{
                val_type.setText("Entreprise");
                label_siret.setVisible(true);   //affiche le label siret
                val_siret.setVisible(true); //affiche la valeur du siret
                val_siret.setText(user.getSiret());
            }
            fiche_user.setVisible(true);    //Affiche la fiche utilisateur
        }
    }

    /**
     * <b>ajoute</b> est la méthode qui redirige vers le stage qui permet d'ajouter un utilisateur
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void ajoute(ActionEvent actionEvent) throws IOException {
        App.setRoot("add");
    }

    /**
     * <b>supprime</b> est la méthode qui supprime l'utilisateur selectionné
     * @param actionEvent
     */
    @FXML
    public void supprime(ActionEvent actionEvent) {
        int i = user_selected();
        if(i!=-1){
            User user = new User();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  //crée une alert de confirmation
            alert.setContentText("Voulez vous vraiment supprimer l'utilisateur ?");   //set le texte de l'alerte
            Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
            if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
                UserDAO userDAO = new UserDAO();
                userDAO.Delete_user(user);    //supprime l'utilisateur de la base de donnée
                obs_list_user.remove(i);    //enlève l'utilisateur de la liste
                maj_lst();  //met à jour la liste des utilisateurs dans le tableau
            }
        }
    }

    /**
     * <b>quitte</b> est la méthode qui supprime l'utilisateur selectionné de la base de donnée
     * @param actionEvent
     */
    @FXML
    public void quitte(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  //crée une alert de confirmation
        alert.setContentText("Voulez vous vraiment quitter l'application ?");   //set le texte de l'alerte
        Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
        if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
            Stage stage = (Stage) table_user.getScene().getWindow();    //récupère le stage actuel
            stage.close();  //le ferme
        }

    }

    /**
     * <b>ok</b> est la méthode qui ferme la fiche client
     * @param actionEvent
     */
    @FXML
    public void ok(ActionEvent actionEvent) {
        fiche_user.setVisible(false);   //cache la partie fiche utilisateur
    }
}
