package org.afpa.stackorigami;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    /*Les boutons de droite*/
    @FXML
    public Button btn_detail;
    @FXML
    public Button btn_add;
    @FXML
    public Button btn_update;
    @FXML
    public Button btn_delete;
    /*les tableaux*/
    @FXML
    public TabPane tab_x;
    @FXML
    public Tab tab_user;
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
    /*la fiche détail de l'utilisateur*/
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
    @FXML
    public PieChart graph_type; //le graphique

    public UserDAO userDAO = new UserDAO();

    public List<User> list_user = new ArrayList<User>();

    public ObservableList<User> obs_list_user = FXCollections.observableArrayList();

    //public ObservableList<PieChart.Data> obs_graph = FXCollections.observableArrayList();

    public HomeController() throws IOException{
    }

    /**
     * <b>maj_lst</b> est une méthode qui met à jour l'affichage des listes
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
        maj_lst();  //affiche la liste dans le tableau

        /*rajoue les valeurs au graphique*/
        int total_part = userDAO.sum_commande_part();   //récupère le CA pour les particuliers
        int total_ent = userDAO.sum_commande_ent();

        ObservableList<PieChart.Data> obs_graph = FXCollections.observableArrayList(
                new PieChart.Data("particulier ("+total_part+"€)",total_part),    //ajoute les valeurs
                new PieChart.Data("entreprise ("+total_ent+"€)", total_ent)
                );
        graph_type.setData(obs_graph);  //associe l'observableListe au graphique
        graph_type.setTitle("CA par type de client (total : "+(total_ent+total_part)+"€)"); //met un titre


    }


    /**
     * <b>actualise</b> est la méthode qui actualise le tableau
     */
    @FXML
    public void actualise(ActionEvent actionEvent) {
        maj_lst();  //met à jour la liste
    }

    /**
     * <b>is_selected</b> Méthode qui vérifie si un utilisateur ou un produit est selectionné
     * @return  le numéro dans la liste
     */
    public int is_selected(){
        int i;  //index de l'élément selectionné
        Alert alert = new Alert(Alert.AlertType.ERROR); //crée l'alerte
            i = table_user.getSelectionModel().getSelectedIndex(); //récupère l'index de l'utilisateur selectionné

        if(i==-1) { //s'il n'y a pas de selection
            /* On affiche une alerte */
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
        int i = is_selected();
            if (i != -1) { //s'il y en a un
                User user = new User(); //cree l'utilisateur
                user = obs_list_user.get(i);    //récupère les valeurs de l'utilisateur
                /*met les valeurs de l'utilisateur sur la fiche*/
                val_surname.setText(user.getSurname());
                val_first_name.setText(user.getFirst_name());
                val_mail.setText(user.getMail());
                val_phone.setText(user.getPhone());
                val_adress.setText(user.getAdress());
                if (user.getType() == 1) {
                    val_type.setText("Particulier");
                    label_siret.setVisible(false);   //cache le label siret
                    val_siret.setVisible(false); //cache la valeur du siret
                } else {
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
        int i = is_selected();
        if(i!=-1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  //crée une alert de confirmation
            alert.setContentText("Voulez vous vraiment supprimer l'utilisateur ?");   //set le texte de l'alerte
            Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
            if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
                User user = new User();
                user = obs_list_user.get(i);    //récupère les valeurs de l'utilisateur
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

    @FXML
    public void modif(ActionEvent actionEvent) throws IOException {
        int i = is_selected();
        if(i!=-1){
            App.user_app=obs_list_user.get(i);    //récupère les valeurs de l'utilisateur
            App.setRoot("update");
        }
    }

    public void product(ActionEvent actionEvent) throws IOException {
        App.setRoot("home_product");
    }
}
