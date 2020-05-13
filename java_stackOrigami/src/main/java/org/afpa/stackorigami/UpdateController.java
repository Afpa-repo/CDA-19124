package org.afpa.stackorigami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.afpa.stackorigami.App;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    public TextField val_surname;
    @FXML
    public TextField val_first_name;
    @FXML
    public TextField val_adress;
    @FXML
    public TextField val_mail;
    @FXML
    public TextField val_phone;
    @FXML
    public CheckBox val_type;
    @FXML
    public Label label_siret;
    @FXML
    public TextField val_siret;
    @FXML
    public ComboBox<User> list_commercial;

    public List<User> lst_commercial = new ArrayList<User>();   //liste des commerciaux
    public ObservableList<User> obs_list_commercial = FXCollections.observableArrayList();

    /**
     * méthode qui affiche la liste des commerciaux correspondant au type de l'utilisateur
     */
    public void show_commercial(){
        /*** selectionner celui correspondant***/

        UserDAO userDAO = new UserDAO();
        if(val_type.isSelected()){  //si c'est un particulier
            lst_commercial = userDAO.List_role_user(2); //récupère la liste des commerciaux pour particulier
        }else{
            lst_commercial = userDAO.List_role_user(3);
        }
        obs_list_commercial.addAll(lst_commercial);
        list_commercial.setItems(obs_list_commercial);
        System.out.println(lst_commercial);
        System.out.println(obs_list_commercial);
        System.out.println(list_commercial);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effacer(null);  //vide les input
        //show_commercial();
    }

    @FXML
    public void change_type(ActionEvent actionEvent) {
        if(val_type.isSelected()){  //si c'est un particulier
            label_siret.setVisible(false);  //cache le label du siret
            val_siret.setVisible(false);
        }else{
            label_siret.setVisible(true);   //affiche le label du siret
            val_siret.setVisible(true);
        }
        list_commercial.getItems().clear();
        show_commercial();
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
        /*rempli les input avec les valeurs de l'utilisateur*/
        val_surname.setText(App.user_app.getSurname());
        val_first_name.setText(App.user_app.getFirst_name());
        val_adress.setText(App.user_app.getAdress());
        val_mail.setText(App.user_app.getMail());
        val_phone.setText(App.user_app.getPhone());
        val_siret.clear();
        if(App.user_app.getType()==1) {
            val_type.setSelected(true); //coche le type
        }else{
            val_type.setSelected(false); //décoche le type
        }
        change_type(null);  //remet la liste des commerciaux à zéro
    }

    /**
     * <b>verif_form</b> est une méthode qui vérifie que les valeurs du formulaire sont valides
     * @return true si le formulaire est valide
     */
    public boolean verif_form(){
        boolean valid = true; //booléen qui vaut false si le formulaire est invalide
        String reg_name = "[A-Za-zÀ-ú -]+";  //expression régulière pour le nom
        String reg_adr = "[^<>]*";  //expression régulière pour l'adresse
        String reg_phone = "[+]?[0-9]+";
        String reg_mail = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        String reg_siret = "[0-9]{14}";
        String message_err = "";    //message d'erreur à afficher si le formulaire est invalide
        Alert alert_err = new Alert(Alert.AlertType.ERROR); //crée l'alert pour afficher les erreurs

        /*Vérification des valeurs du formulaire*/

        /*pour le nom*/
        if(val_surname.getText().equals("")){ //si le nom est vide
            valid = false;
            val_surname.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom est vide";
        }else if(val_surname.getText().length()>255) {    //si le nom est trop long
            valid = false;
            val_surname.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom est trop long";
        }else if(!val_surname.getText().matches(reg_name)){    //si le nom respecte l'expression régulière
            valid = false;
            val_surname.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le nom comporte des caractères non autorisés";
        }else{  //si il n'y a pas d'erreur
            val_surname.setStyle("");    //l'input est normal
        }

        /*pour le prénom*/
        if(val_first_name.getText().equals("")){ //si le prénom est vide
            valid = false;
            val_first_name.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom est vide";
        }else if(val_first_name.getText().length()>255) {    //si le prénom est trop long
            valid = false;
            val_first_name.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom est trop long";
        }else if(!val_first_name.getText().matches(reg_name)){    //si le prénom respecte l'expression régulière
            valid = false;
            val_first_name.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le prénom comporte des caractères non autorisés";
        }else{
            val_first_name.setStyle("");    //l'input est normal
        }

        /*pour l'adresse*/
        if(val_adress.getText().length()>255) {    //si l'adresse est trop longue
            valid = false;
            val_adress.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- L'adresse est trop longue";
        }else if(!val_adress.getText().matches(reg_adr)){    //si l'adresse respecte l'expression régulière
            valid = false;
            val_adress.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- L'adresse comporte des caractères non autorisés";
        }else{
            val_adress.setStyle("");    //l'input est normal
        }

        /*pour le mail*/
        if(val_mail.getText().equals("")){ //si le mail est vide
            valid = false;
            val_mail.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le mail est vide";
        }else if(val_mail.getText().length()>50) {    //si le mail est trop long
            valid = false;
            val_mail.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le mail est trop long";
        }else if(!val_mail.getText().matches(reg_mail)){    //si le mail respecte l'expression régulière
            valid = false;
            val_mail.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le mail comporte des caractères non autorisés";
        }else{
            val_mail.setStyle("");    //l'input est normal
        }

        /*pour le numéro*/
        if(val_phone.getText().equals("")){ //si le numéro est vide
            valid = false;
            val_phone.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le numéro est vide";
        }else if(val_phone.getText().length()>15) {    //si le numéro est trop long
            valid = false;
            val_phone.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le numéro est trop long";
        }else if(!val_phone.getText().matches(reg_phone)){    //si le numéro respecte l'expression régulière
            valid = false;
            val_phone.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le numéro comporte des caractères non autorisés";
        }else{
            val_phone.setStyle("");    //l'input est normal
        }

        /*pour le siret*/
        if(!val_siret.getText().matches(reg_siret) && !val_siret.getText().equals("")){    //si l'adresse respecte l'expression régulière
            valid = false;
            val_siret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ");    //colore l'input
            message_err+="\n- Le siret n'est pas valide";
        }else{
            val_siret.setStyle("");    //l'input est normal
        }

        /*pour le commercial*/
        int num_commercial = list_commercial.getSelectionModel().getSelectedIndex();
        //int val_commercial = obs_list_commercial.get(list_commercial.getSelectionModel().getSelectedIndex()).getId();
        //System.out.println(val_commercial);
        if(num_commercial==-1){
            valid = false;
            message_err += "\n- Aucun commercial n'est selectionné";
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
    public void valider(ActionEvent actionEvent) {
        boolean form_valid = verif_form();  //vérifie le formulaire
        if(form_valid){ //si le formulaire est valide
            /*récupère les valeurs du formulaire*/
            /*récupère le commercial selectionné*/
            int val_commercial = obs_list_commercial.get(list_commercial.getSelectionModel().getSelectedIndex()).getId();
            App.user_app.setSurname(val_surname.getText()); //récupère le nom
            App.user_app.setFirst_name(val_first_name.getText());
            App.user_app.setMail(val_mail.getText());
            App.user_app.setCommercial(val_commercial);
            App.user_app.setPhone(val_phone.getText());
            App.user_app.setRole(0);    //c'est un client
            if(val_type.isSelected()){   //si c'est un particulier
                App.user_app.setType(1);
                App.user_app.setCoefficient(1);
                App.user_app.setSiret(null);
            }else{
                App.user_app.setType(0);
                App.user_app.setCoefficient(2);
                App.user_app.setSiret(val_siret.getText());
            }
            //modifie l'utilisateur
            UserDAO userDAO = new UserDAO();
            userDAO.Update_user(App.user_app);
            effacer(null);  //vide les input
            //alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //crée l'alerte
            alert.setContentText("Le client a bien été modifié");   //set le message à afficher
            alert.show();   //affiche l'alert
        }
    }
}
