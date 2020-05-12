package org.afpa.stackorigami;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        User user = new User();
        user.setSurname("Nom");
        user.setFirst_name("prenom");
        user.setAdress("Addresse");
        user.setType(1);
        user.setMail("mail@ok.fr");
        user.setCommercial(1);
        user.setPassword("mdp");
        user.setPhone("026516416");
        user.setCoefficient(1);
        user.setSiret("");
        UserDAO userDAO = new UserDAO();
        userDAO.Insert(user);
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}