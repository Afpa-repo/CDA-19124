package Model;

import java.util.Date;

public class Order {
    private int id; //identifiant de la commande
    private User user;  //utilisateur qui a passé la commande
    private String status;  //status de la commande
    private Date date;  //date de la commande
    private double total;  //total de la commande

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
