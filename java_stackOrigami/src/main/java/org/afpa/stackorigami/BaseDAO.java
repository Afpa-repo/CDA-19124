package org.afpa.stackorigami;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {
    protected static BaseDAO baseDAO;
    protected BoneCP connectionPool;

    public BaseDAO() {
        try {
            // load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return;
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
            e.printStackTrace();
            return;
        }

    }

    public static BaseDAO getInstance() throws IOException, SQLException {
        if (baseDAO == null) {
            baseDAO = new BaseDAO();
        }
        return baseDAO;
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
}