package org.afpa.stackorigami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    public void actualise(ActionEvent actionEvent) {
        maj_lst();  //met à jour la liste
    }

    public void details(ActionEvent actionEvent) {
    }

    public void ajoute(ActionEvent actionEvent) {
    }

    public void supprime(ActionEvent actionEvent) {
    }

    public void quitte(ActionEvent actionEvent) {
    }

}
