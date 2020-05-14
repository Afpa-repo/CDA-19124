package org.afpa.stackorigami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeProductController implements Initializable {
    @FXML
    public TableView<Product> table_product;
    @FXML
    public TableColumn<Product,String> libelle;
    @FXML
    public TableColumn<Product,String> color;
    @FXML
    public TableColumn<Product,Double> price;
    @FXML
    public TableColumn<Product,Integer> stock;
    /*
    @FXML
    public TableColumn<Product_Category,String> category;*/

    public ProductDAO productDAO = new ProductDAO();

    public List<Product> list_product = new ArrayList<Product>();

    public ObservableList<Product> obs_list_product = FXCollections.observableArrayList();

    /**
     * <b>maj_lst</b> est une méthode qui met à jour l'affichage des listes
     */
    public void maj_lst(){

            libelle.setCellValueFactory(new PropertyValueFactory("libelle"));
            color.setCellValueFactory(new PropertyValueFactory("color"));
            price.setCellValueFactory(new PropertyValueFactory("price"));
            stock.setCellValueFactory(new PropertyValueFactory("stock"));
            //category.setCellValueFactory(new PropertyValueFactory("category"));
            table_product.setItems(obs_list_product);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_product.setEditable(false);
        list_product = productDAO.List_Product();
        obs_list_product.addAll(list_product);
        maj_lst();

    }
    @FXML
    public void actualise(ActionEvent actionEvent) {
        maj_lst();
    }

    /**
     * <b>is_selected</b> Méthode qui vérifie si un utilisateur ou un produit est selectionné
     * @return  le numéro dans la liste
     */
    public int is_selected(){
        int i;  //index de l'élément selectionné
        Alert alert = new Alert(Alert.AlertType.ERROR); //crée l'alerte
        i = table_product.getSelectionModel().getSelectedIndex(); //récupère l'index de l'utilisateur selectionné
        if(i==-1) { //s'il n'y a pas de selection
            /* On affiche une alerte */
            alert.setContentText("Veuillez selectionner un produit");   //set le message à afficher
            alert.show();   //affiche l'erreur
        }
        return i;
    }


    @FXML
    public void details(ActionEvent actionEvent) {

    }

    /**
     * <b>add</b> est la méthode qui redirige vers le stage qui permet d'ajouter un produit
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void add(ActionEvent actionEvent) throws IOException{
        App.setRoot("add_product");
    }

    @FXML
    public void update(ActionEvent actionEvent) throws IOException {
        int i = is_selected();
        if(i!=-1) {
            //App.product_app = obs_list_product.get(i);    //récupère les valeurs de l'utilisateur
            App.setRoot("update_product");
        }
    }

    /**
     * <b>delete</b> est la méthode qui supprime le produit selectionné
     * @param actionEvent
     */
    @FXML
    public void delete(ActionEvent actionEvent) {
        int i = is_selected();
        if(i!=-1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  //crée une alert de confirmation
            alert.setContentText("Voulez vous vraiment supprimer le produit ?");   //set le texte de l'alerte
            Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
            if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
                Product product = new Product();
                product = obs_list_product.get(i);    //récupère les valeurs de l'utilisateur
                productDAO.Delete_product(product);    //supprime l'utilisateur de la base de donnée
                obs_list_product.remove(i);    //enlève l'utilisateur de la liste
                maj_lst();  //met à jour la liste des utilisateurs dans le tableau
            }
        }
    }

    /**
     * <b>exit</b> est la méthode qui supprime l'utilisateur selectionné de la base de donnée
     * @param actionEvent
     */
    @FXML
    public void exit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  //crée une alert de confirmation
        alert.setContentText("Voulez vous vraiment quitter l'application ?");   //set le texte de l'alerte
        Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
        if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
            Stage stage = (Stage) table_product.getScene().getWindow();    //récupère le stage actuel
            stage.close();  //le ferme
        }
    }

}
