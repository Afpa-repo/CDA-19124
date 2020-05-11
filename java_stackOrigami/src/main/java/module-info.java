module org.afpa.stackorigami {
    requires javafx.controls;
    requires javafx.fxml;
    requires bonecp;
    requires java.sql;

    opens org.afpa.stackorigami to javafx.fxml;
    exports org.afpa.stackorigami;
}