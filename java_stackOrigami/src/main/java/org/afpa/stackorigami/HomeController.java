package org.afpa.stackorigami;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
    public Tab tab_product;
    @FXML
    public TableView<Product> table_product;
    @FXML
    public TableColumn<Product, String> libelle;
    @FXML
    public TableColumn<Product, String> color;
    @FXML
    public TableColumn<Product, Double> price;
    @FXML
    public TableColumn<Product, Integer> stock;
    @FXML
    public TableColumn<Product, Integer> category;
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

    public UserDAO userDAO = new UserDAO();

    public List<User> list_user = new ArrayList<User>();

    public ObservableList<User> obs_list_user = FXCollections.observableArrayList();

    public ProductDAO productDAO = new ProductDAO();

    public List<Product> list_product = new ArrayList<Product>();

    public ObservableList<Product> obs_list_product = FXCollections.observableArrayList();

    public String Tab;  //le tableau qui est utilisé

    public HomeController() throws IOException{
    }

    /**
     * <b>maj_lst</b> est une méthode qui met à jour l'affichage des listes
     */
    public void maj_lst(){

        //if(Tab.equals("utilisateur")){ //si on est sur la table user
            first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));   // Jonction du tableau avec les données
            surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));

            table_user.setItems(obs_list_user);    // On indique au TableView quelle modèle observer pour trouver les données
        /*}else{
            libelle.setCellFactory(new PropertyValueFactory("libelle"));
            color.setCellFactory(new PropertyValueFactory("color"));
            price.setCellFactory(new PropertyValueFactory("price"));
            stock.setCellFactory(new PropertyValueFactory("stock"));
            category.setCellFactory(new PropertyValueFactory("id"));
            table_product.setItems(obs_list_product);
        }*/

    }

    /**
     * <b>initialize</b> est la méthode qui initialise la vue et les variables
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* au changement de tab
        tab_x.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if(t1==tab_user){   //si on est sur la table user
                            Tab = "utilisateur";   //on change la variable
                        }else{  //si on est sur la table produit
                            Tab = "produit";
                        }
                        maj_lst();
                    }
                }
        );*/
        //Tab = "utilisateur";   //on initialise la variable du tableau actuel
        table_user.setEditable(false); //rend la liste des clients non éditable
        //table_product.setEditable(false);
        list_user = userDAO.List_user();   //récupère la liste des clients
        //list_product = productDAO.List_Product();
        obs_list_user.addAll(list_user);    //met la liste des clients dans obs_liste
        //obs_list_product.addAll(list_product);
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
     * <b>is_selected</b> Méthode qui vérifie si un utilisateur ou un produit est selectionné
     * @return  le numéro dans la liste
     */
    public int is_selected(){
        int i;  //index de l'élément selectionné
        Alert alert = new Alert(Alert.AlertType.ERROR); //crée l'alerte
        //if(Tab.equals("utilisateur")){
            i = table_user.getSelectionModel().getSelectedIndex(); //récupère l'index de l'utilisateur selectionné
        /*}else{
            i = table_product.getSelectionModel().getSelectedIndex(); //récupère l'index du produit selectionné
        }*/

        if(i==-1) { //s'il n'y a pas de selection
            /* On affiche une alerte */
            alert.setContentText("Veuillez selectionner un "+Tab);   //set le message à afficher
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
        //if(Tab.equals("utilisateur")) {
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
        /*}else{

        }*/
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
