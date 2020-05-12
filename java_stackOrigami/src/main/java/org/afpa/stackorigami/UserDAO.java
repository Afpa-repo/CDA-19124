package org.afpa.stackorigami;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO{
    /*
    private static UserDAO userDAO;
    private BoneCP connectionPool;

    public UserDAO(){
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

    public static UserDAO getInstance() throws IOException, SQLException {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
*/
    /**
     * <b>Insert_user</b> est une méthode qui ajoute un client dans la base de donnée
     * @param user l'utilisateur à ajouter
     */
    public void Insert_user(User user) {
        int id=1;   //initialide l'id du client à ajouter
        /*Récupère le dernier id*/

        try{
            Connection con = baseDAO.getInstance().getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT MAX(id) as id FROM users");
            if(result.next()){  //s'il y a un résultat
                id = result.getInt("id")+1; //change la valeur de l'id
            }
            con.close();
        }catch(Exception e){
            System.out.println("Erreur récupération du dernier id");
            System.out.println(e);
        }

        /*ajoute le client à la base de donnée*/
        try {
            Connection con = baseDAO.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO users (mail,surname,first_name,phone_number,address_fact,type,siret,commercial_id,id,password,coefficient) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //ajoute les valeurs à la requête
            stm.setString(1, user.getMail());
            stm.setString(2, user.getSurname());
            stm.setString(3, user.getFirst_name());
            stm.setString(4, user.getPhone());
            stm.setString(5, user.getAdress());
            stm.setInt(6,user.getType());
            stm.setString(7,user.getSiret());
            stm.setInt(8,user.getCommercial());
            stm.setInt(9,id);
            stm.setString(10,user.getPassword());
            stm.setDouble(11,user.getCoefficient());
            stm.execute();  //lance la requête
            stm.close();
            con.close();

            user.setId(id);  //ajoute l'id au client
        }
        catch (Exception e) {
            System.out.println("Erreur ajout client");
            System.out.println(e.getMessage());
        }

    }

    /**
     * <b>Update_user</b> est une méthode qui met à jour un client dans la base de donnée
     * @param user l'utilisateur à modifier
     */
    public void Update_user(User user) {
        try {
            Connection con = baseDAO.getInstance().getConnection();

            PreparedStatement stm = con.prepareStatement("UPDATE client SET mail = ?, surname = ?,first_name = ?, phone_number = ?, address_fact = ?, type = ?,siret = ?,commercial_id = ? WHERE id=?");

            stm.setString(1, user.getMail());
            stm.setString(2, user.getSurname());
            stm.setString(3, user.getFirst_name());
            stm.setString(4, user.getPhone());
            stm.setString(5, user.getAdress());
            stm.setInt(6,user.getType());
            stm.setString(7,user.getSiret());
            stm.setInt(8,user.getCommercial());
            stm.setInt(9,user.getId());

            stm.execute();

            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur modification Client");
            System.out.println(e);
        }

    }

    /**
     * <b>Delete_user</b> est une méthode qui supprime un client de la base de donnée
     * @param user  l'utilisateur à supprimer
     */
    public void Delete_user(User user) {
        try{
            Connection con = baseDAO.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("DELETE FROM users WHERE id=?");
            stm.setInt(1,user.getId());
            stm.execute();

            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur lors de la suppression du client");
            System.out.println(e);
        }
    }

    /**
     * <b>Find_user</b> est une méthode qui récupère un client de la base de donnée à partir de son id
     * @param id l'identifiant du client
     * @return le client
     */
    public User Find_user(int id)     {
        User user=new User();
        try {
            Connection con = baseDAO.getInstance().getConnection();

            PreparedStatement stm = con.prepareStatement("SELECT * FROM users WHERE id=?");
            stm.setInt(1,id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setSurname(result.getString("surname"));
                user.setFirst_name(result.getString("first_name"));
                user.setAdress(result.getString("Address_fact"));
                user.setMail(result.getString("mail"));
                user.setPhone(result.getString("phone_number"));
                user.setType(result.getInt("type"));
                user.setCommercial(result.getInt("commercial_id"));
                user.setSiret(result.getString("siret"));
                user.setCoefficient(result.getFloat("coefficient"));
                user.setRole(result.getInt("role"));
            }else{
                return null;
            }
            stm.close();
            result.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error while reading 'client'");
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * <b>List_user</b> est une méthode qui retourne la liste de touts les utilisateurs dans la base de donnée
     * @return  la liste des utilisateurs
     */
    public List<User> List_user(){
        List<User> resultat = new ArrayList<User>();

        try {
            Connection con = baseDAO.getInstance().getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setSurname(result.getString("surname"));
                user.setFirst_name(result.getString("first_name"));
                user.setAdress(result.getString("Address_fact"));
                user.setMail(result.getString("mail"));
                user.setPhone(result.getString("phone_number"));
                user.setType(result.getInt("type"));
                user.setCommercial(result.getInt("commercial_id"));
                user.setSiret(result.getString("siret"));
                user.setCoefficient(result.getFloat("coefficient"));
                user.setRole(result.getInt("role"));
                resultat.add(user);
            }
            stm.close();
            result.close();
            con.close();
        } catch (IOException | SQLException e) {
            System.out.println("Erreur dans la lecture de 'users'");
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    /**
     * <b>List_role_user</b> est une méthode qui retourne la liste des utilisateurs du role demandé
     * @param role le numéro du type
     * @return  la liste des utilisateurs
     */
    public List<User> List_role_user(int role){
        List<User> resultat = new ArrayList<User>();

        try {
            Connection con = baseDAO.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM users WHERE role = ?");
            stm.setInt(1,role);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setSurname(result.getString("surname"));
                user.setFirst_name(result.getString("first_name"));
                user.setAdress(result.getString("Address_fact"));
                user.setMail(result.getString("mail"));
                user.setPhone(result.getString("phone_number"));
                user.setType(result.getInt("type"));
                user.setCommercial(result.getInt("commercial_id"));
                user.setSiret(result.getString("siret"));
                user.setCoefficient(result.getFloat("coefficient"));
                user.setRole(result.getInt("role"));
                System.out.println(user.getRole());
                resultat.add(user);
            }
            stm.close();
            result.close();
            con.close();
        } catch (IOException | SQLException e) {
            System.out.println("Erreur dans la lecture de 'users'");
            System.out.println(e.getMessage());
        }
        return resultat;
    }

}
