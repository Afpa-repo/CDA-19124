package Model;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {
    protected static BaseDAO baseDAO;
    protected BoneCP connectionPool;

    //Constructeur
    public BaseDAO() {
        Alert alert_err = new Alert(Alert.AlertType.ERROR); //crée l'alert pour afficher les erreurs
        try {
            // load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Une erreur a la connection a la base de donnée (driver jdbc) a été repérer veuillez le reglé svp (En excel)");
            e.printStackTrace();
        }
        try {
            // setup the connection pool using BoneCP Configuration
            BoneCPConfig config = new BoneCPConfig();
            // jdbc url specific to your database
            config.setJdbcUrl("jdbc:mysql://localhost/fil_rouge?serverTimezone=UTC");
            config.setUsername("root");
            config.setPassword("");
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            // setup the connection pool
            connectionPool = new BoneCP(config);
        } catch (Exception e) {
            System.out.println("Erreur connexion BD");
            alert_err.setContentText("Une erreur a la connection a la base de donnée a été repérer veuillez le reglé svp (En excel)");
            e.printStackTrace();
            alert_err.showAndWait();
            //connectionPool.getDbIsDown();
            System.exit(0);
        }
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
}
