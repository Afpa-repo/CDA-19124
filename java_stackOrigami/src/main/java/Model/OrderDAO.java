package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO {
    /**
     * <b>Insert_order</b> est une méthode qui ajoute une commande dans la base de donnée
     * @param order la commande à ajouter
     */
    public void Insert_user(Order order) {
        int id=1;   //initialide l'id du client à ajouter
        /*Récupère le dernier id*/
        try{
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT MAX(id) as id FROM orders");
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
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO orders (users_id_id,date,status,total,id) VALUES (?,?,?,?,?)");

            //ajoute les valeurs à la requête
            stm.setInt(1, order.getUser().getId());
            stm.setDate(2, (Date) order.getDate());
            stm.setString(3, order.getStatus());
            stm.setDouble(4, order.getTotal());
            stm.setInt(5,id);
            stm.execute();  //lance la requête
            stm.close();
            con.close();

            order.setId(id);  //ajoute l'id à la commande
        }
        catch (Exception e) {
            System.out.println("Erreur ajout commande");
            System.out.println(e.getMessage());
        }

    }

    /**
     * <b>Update_order</b> est une méthode qui met à jour un client dans la base de donnée
     * @param order l'utilisateur à modifier
     */
    public void Update_order(Order order) {
        try {
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("UPDATE client SET users_id_id = ?, date = ?, status = ?,total = ? WHERE id = ?");
            stm.setInt(1, order.getUser().getId());
            stm.setDate(2, (Date) order.getDate());
            stm.setString(3, order.getStatus());
            stm.setDouble(4, order.getTotal());
            stm.setInt(5,order.getId());
            stm.execute();
            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur modification Commande");
            System.out.println(e);
        }

    }

    /**
     * <b>Delete_order</b> est une méthode qui supprime une commande de la base de donnée
     * @param order  la commande à supprimer
     */
    public void Delete_order(Order order) {
        try{
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("DELETE FROM orders WHERE id=?");
            stm.setInt(1,order.getId());
            stm.execute();

            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur lors de la suppression de la commande");
            System.out.println(e);
        }
    }

    /**
     * <b>List_user_order</b> est une méthode qui retourne la liste des commandes d'un utilisateur
     * @param user l'utilisateur
     * @return  la liste des commandes
     */
    public List<Order> List_user_order(User user){
        List<Order> resultat = new ArrayList<Order>();

        try {
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM orders WHERE users_id_id = ? ");
            stm.setInt(1,user.getId());
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setStatus(result.getString("status"));
                order.setTotal(result.getInt("total"));
                order.setDate(result.getDate("date"));
                resultat.add(order);
            }
            stm.close();
            result.close();
            con.close();
        } catch (SQLException e) { //IOException |
            System.out.println("Erreur dans la lecture de 'orders'");
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    /**
     * <b>List_order</b> est une méthode qui retourne la liste des commandes
     * @return  la liste des commandes
     */
    public List<Order> List_order(){
        List<Order> resultat = new ArrayList<Order>();

        try {
            //Connection con = baseDAO.getInstance().getConnection();
            Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM orders");
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setStatus(result.getString("status"));
                order.setTotal(result.getInt("total"));
                order.setDate(result.getDate("date"));
                /*** Verifier c'est c'est pas mieux un id ***/
                UserDAO userDAO = new UserDAO();
                order.setUser(userDAO.Find_user(result.getInt("users_id_id")));

                resultat.add(order);
            }
            stm.close();
            result.close();
            con.close();
        } catch (SQLException e) {//IOException |
            System.out.println("Erreur dans la lecture de 'orders'");
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    /**
     * <b>Find_order</b> est une méthode qui récupère une commande de la base de donnée à partir de son id
     * @param id l'identifiant de la commande
     * @return le client
     */
    public Order Find_order(int id)     {
        Order order=new Order();
        try {
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM orders WHERE id=?");
            stm.setInt(1,id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                order.setId(result.getInt("id"));
                order.setStatus(result.getString("status"));
                order.setTotal(result.getInt("total"));
                order.setDate(result.getDate("date"));
            }else{
                return null;
            }

            result.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error while reading 'orders'");
            System.out.println(e.getMessage());
        }
        return order;
    }
}
