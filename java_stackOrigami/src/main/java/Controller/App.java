package Controller;

import javafx.application.Application;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    public static User user_app;

    public static Product product_app;

    public static Stage stage;

    public App(){
    }

    @Override
    public void start(Stage stage) throws IOException {
        user_app =null;
        product_app=null;

        scene = new Scene(loadFXML("home"),642,400);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource( "/View/"+fxml + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
