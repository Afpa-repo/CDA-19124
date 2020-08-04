package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends BaseDAO {
    /**
     * <b>Insert_product</b> est une méthode qui ajoute un produit dans la base de donnée
     * @param product le produit à ajouter
     */
    public void Insert_product(Product product) {
        int id=1;   //initialide l'id du produit à ajouter
        /*Récupère le dernier id*/

        try{
            Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT MAX(id) as id FROM product");
            if(result.next()){  //s'il y a un résultat
                id = result.getInt("id")+1; //change la valeur de l'id
            }
            con.close();
        }catch(Exception e){
            System.out.println("Erreur récupération du dernier id");
            System.out.println(e);
        }

        /*ajoute le produit à la base de donnée*/
        try {
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO product (libelle,description,color,picture,price,stock,created_at,id,product_category_id) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?)");

            //ajoute les valeurs à la requête
            stm.setString(1, product.getLibelle());
            stm.setString(2, product.getDescription());
            stm.setString(3, product.getColor());
            stm.setString(4, product.getPicture());
            stm.setDouble(5, product.getPrice());
            stm.setInt(6,product.getStock());
            stm.setDate(7,(java.sql.Date) product.getCreated_at());
            stm.setInt(8,id);
            stm.setInt(9,product.getProduct_category().getId());
            stm.execute();  //lance la requête
            stm.close();
            con.close();

            product.setId(id);  //ajoute l'id au produit
        }
        catch (Exception e) {
            System.out.println("Erreur ajout produit");
            System.out.println(e.getMessage());
        }
    }

    /**
     * <b>Update_product</b> est une méthode qui met à jour un produit dans la base de donnée
     * @param product le produit à modifier
     */
    public void Update_product(Product product) {
        try {
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("UPDATE product SET libelle = ?, description = ?,color = ?, picture = ?, price =?, stock = ?, product_category_id = ? WHERE id = ?");

            stm.setString(1, product.getLibelle());
            stm.setString(2, product.getDescription());
            stm.setString(3, product.getColor());
            stm.setString(4, product.getPicture());
            stm.setDouble(5, product.getPrice());
            stm.setInt(6,product.getStock());
            stm.setInt(9,product.getProduct_category().getId());
            stm.setInt(8,product.getId());
            stm.execute();
            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur modification Produit");
            System.out.println(e);
        }

    }

    public Product_Category Get_product_category(int id){
        Product_Category product_category = new Product_Category();
        try {
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM product_category WHERE id=?");
            stm.setInt(1,id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                product_category.setName(result.getString("name"));
                product_category.setPicture(result.getString("picture"));

            }else{
                return null;
            }
            stm.close();
            result.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Erreur dans la lecture du produit");
            System.out.println(e.getMessage());
        }
        return product_category;
    }

    /**
     * <b>Delete_product</b> est une méthode qui supprime un produit de la base de donnée
     * @param product  le produit à supprimer
     */
    public void Delete_product(Product product) {
        try{
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("DELETE FROM product WHERE id=?");
            stm.setInt(1,product.getId());
            stm.execute();

            stm.close();
            con.close();

        }catch (Exception e){
            System.out.println("Erreur lors de la suppression du produit");
            System.out.println(e);
        }
    }

    /**
     * <b>Find_product</b> est une méthode qui récupère un produit de la base de donnée à partir de son id
     * @param id l'identifiant du produit
     * @return le produit
     */
    public Product Find_product(int id)     {
        Product product=new Product();
        try {
            Connection con = this.getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM product WHERE id=?");
            stm.setInt(1,id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                product.setId(result.getInt("id"));
                product.setLibelle(result.getString("libelle"));
                product.setDescription(result.getString("description"));
                product.setColor(result.getString("color"));
                product.setPicture(result.getString("picture"));
                product.setPrice(result.getDouble("price"));
                product.setStock(result.getInt("stock"));
                product.setCreated_at(result.getDate("created_at"));
                /*récupère la catégorie du produit*/
                product.setProduct_category(this.Get_product_category(result.getInt("product_category_id")));
            }else{
                return null;
            }
            stm.close();
            result.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Erreur dans la lecture du produit");
            System.out.println(e.getMessage());
        }
        return product;
    }

    /**
     * <b>List_product</b> est une méthode qui retourne la liste de touts les produits dans la base de donnée
     * @return  la liste des produits
     */
    public List<Product> List_Product(){
        List<Product> products = new ArrayList<Product>();

        try {
            Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM product");
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setLibelle(result.getString("libelle"));
                product.setDescription(result.getString("description"));
                product.setColor(result.getString("color"));
                product.setPicture(result.getString("picture"));
                product.setPrice(result.getDouble("price"));
                product.setStock(result.getInt("stock"));
                product.setCreated_at(result.getDate("created_at"));
                /*récupère la catégorie du produit*/
                product.setProduct_category(this.Get_product_category(result.getInt("product_category_id")));
                products.add(product);
            }
            stm.close();
            result.close();
            con.close();
        } catch ( SQLException e) {//IOException | si le get instance est utlisé
            System.out.println("Erreur dans la lecture des produits");
            System.out.println(e.getMessage());
        }
        return products;
    }

    /**
     * <b>List_product_category</b> est une méthode qui retourne la liste de touts les produits dans la base de donnée
     * @return  la liste des produits
     */
    public List<Product_Category> List_Product_category(){
        List<Product_Category> product_categories = new ArrayList<Product_Category>();

        try {
            Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM product_category");
            while (result.next()) {
                Product_Category product_category = new Product_Category();
                product_category.setId(result.getInt("id"));
                product_category.setName(result.getString("name"));
                product_category.setPicture(result.getString("picture"));
                product_categories.add(product_category);
            }
            stm.close();
            result.close();
            con.close();
        } catch ( SQLException e) {//IOException |
            System.out.println("Erreur dans la lecture des produits");
            System.out.println(e.getMessage());
        }
        return product_categories;
    }
}
