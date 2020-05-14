package org.afpa.stackorigami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class UpdateProductController implements Initializable {

    @FXML
    public TextField val_libelle;
    @FXML
    public TextField val_color;
    @FXML
    public Spinner val_stock;
    @FXML
    public TextField val_img;
    @FXML
    public TextArea val_description;
    @FXML
    public ComboBox<Product_Category> liste_category;

    public List<Product_Category> lst_category = new ArrayList<Product_Category>();   //liste des commerciaux
    public ObservableList<Product_Category> obs_liste_category = FXCollections.observableArrayList();



    /**
     * méthode qui affiche la liste des commerciaux correspondant au type de l'utilisateur
     */
    public void show_category(){
        ProductDAO productDAO = new ProductDAO();
        lst_category = productDAO.List_Product_category();
        obs_liste_category.addAll(lst_category);;
        liste_category.setItems(obs_liste_category);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        val_stock.setEditable(true);
        effacer(null);  //vide les input
        show_category();
    }


    /**
     * <b>annuler</b> méthode qui retourne à la page home
     * {@link org.afpa.stackorigami.HomeController}
     * @param actionEvent
     */
    @FXML
    public void annuler(ActionEvent actionEvent) throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void effacer(ActionEvent actionEvent) {
        /*vide les input*/
        // nb_stock = App.product_app.getStock().toString;
        val_libelle.setText(App.product_app.getLibelle());
        val_color.setText(App.product_app.getColor());
        val_description.setText(App.product_app.getDescription());
        val_img.setText(App.product_app.getPicture());
        show_category();
    }

    /**
     * <b>verif_form</b> est une méthode qui vérifie que les valeurs du formulaire sont valides
     * @return true si le formulaire est valide
     */
    public boolean verif_form(){
        boolean valid = true; //booléen qui vaut false si le formulaire est invalide
        String reg_name = "[A-Za-zÀ-ú -]+";  //expression régulière pour le nom
        String reg_adr = "[^<>]*";  //expression régulière pour l'stocke
        String reg_phone = "[+]?[0-9]+";
        String reg_img = "[0-9]{14}";
        String message_err = "";    //message d'erreur à afficher si le formulaire est invalide
        Alert alert_err = new Alert(Alert.AlertType.ERROR); //crée l'alert pour afficher les erreurs

        /*Vérification des valeurs du formulaire*/

        /*pour le nom*/
        if(val_libelle.getText().equals("")){ //si le nom est vide
            valid = false;
            val_libelle.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom est vide";
        }else if(val_libelle.getText().length()>255) {    //si le nom est trop long
            valid = false;
            val_libelle.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom est trop long";
        }else if(!val_libelle.getText().matches(reg_name)){    //si le nom respecte l'expression régulière
            valid = false;
            val_libelle.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom comporte des caractères non autorisés";
        }else{  //si il n'y a pas d'erreur
            val_libelle.setStyle("");    //l'input est normal
        }

        /*pour le prénom*/
        if(val_color.getText().equals("")){ //si le prénom est vide
            valid = false;
            val_color.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom est vide";
        }else if(val_color.getText().length()>255) {    //si le prénom est trop long
            valid = false;
            val_color.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom est trop long";
        }else if(!val_color.getText().matches(reg_name)){    //si le prénom respecte l'expression régulière
            valid = false;
            val_color.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom comporte des caractères non autorisés";
        }else{
            val_color.setStyle("");    //l'input est normal
        }


        /*pour l'stocke*/
        if(val_stock.getPromptText().length()>255) {    //si l'stocke est trop longue
            valid = false;
            val_stock.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- L'stocke est trop longue";
        }else if(!val_stock.getPromptText().matches(reg_adr)){    //si l'stocke respecte l'expression régulière
            valid = false;
            val_stock.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- L'stocke comporte des caractères non autorisés";
        }else{
            val_stock.setStyle("");    //l'input est normal
        }

        /*pourval_description*/
        if(val_description.getText().equals("")){ //sival_description est vide
            valid = false;
            val_description.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n-val_description est vide";
        }else if(val_description.getText().length()>50) {    //sival_description est trop long
            valid = false;
            val_description.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n-val_description est trop long";
        }else if(!val_description.getText().matches(reg_name)){    //sival_description respecte l'expression régulière
            valid = false;
            val_description.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n-val_description comporte des caractères non autorisés";
        }else{
            val_description.setStyle("");    //l'input est normal
        }

        /*pour le img*/
        if(!val_img.getText().matches(reg_img) && !val_img.getText().equals("")){    //si l'stocke respecte l'expression régulière
            valid = false;
            val_img.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le img n'est pas valide";
        }else{
            val_img.setStyle("");    //l'input est normal
        }

        /*pour le category*/
        int num_category = liste_category.getSelectionModel().getSelectedIndex();
        //int val_category = obs_liste_category.get(liste_category.getSelectionModel().getSelectedIndex()).getId();
        //System.out.println(val_category);
        if(num_category==-1){
            valid = false;
            message_err += "\n- Aucun category n'est selectionné";
        }

        if(valid) {  //si le formulairfe est valide
            return true;    //on retourne vrai
        }else{  //si le formulaire est invalide
            alert_err.setContentText(message_err);  //ajoute le message d'erreur à l'alert
            alert_err.show();   //affiche l'alert
            return false;   //retourne faux
        }

    }

    @FXML
    public void ajouter(ActionEvent actionEvent) {
        boolean form_valid = verif_form();  //ajoute le formulaire
        if(form_valid){ //si le formulaire est valide
            Product product = new Product(); //crée l'utilisateur
            /*récupère les valeurs du formulaire*/
            /*récupère le category selectionné*/
            int val_category = obs_liste_category.get(liste_category.getSelectionModel().getSelectedIndex()).getId();
            int nb_stock = Integer.getInteger(val_stock.getPromptText());
            System.out.println(nb_stock);
            product.setLibelle(val_libelle.getText()); //récupère le nom
            product.setColor(val_color.getText());
            product.setDescription(val_description.getText());
            product.setStock(nb_stock);
            product.setProduct_category(liste_category.getSelectionModel().getSelectedItem());


            //ajoute l'utilisateur
            ProductDAO productDAO = new ProductDAO();
            productDAO.Insert_product(product);
            effacer(null);  //vide les input
            //alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //crée l'alerte
            alert.setContentText("Le Produit a bien été ajouté");   //set le message à afficher
            alert.show();   //affiche l'alert
        }
    }

}
