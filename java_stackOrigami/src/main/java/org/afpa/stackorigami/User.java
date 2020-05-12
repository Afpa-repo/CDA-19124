package org.afpa.stackorigami;

/**
 * <b>User</b> est la classe qui représente les utilisateurs de StackOrigami
 */
public class User {
    private int id;
    private String surname;
    private String first_name;
    private String mail;
    private String phone;
    private String Adress;    //voir si on fait un champ pour ville cp... + address ship dans commande
    private int type;   //vaut 1 si c'est une entreprise et 0 qi c'est un particulier
    private String password;
    private int role;   //vaut 1 si l'utilisateur est admin 2 et 3 si c'est un commercial
    private double coefficient;  //coefficient pour les taxes
    private double total_achat; //montant total de tout les achats
    private String siret; // numéro de siret de l'entreprise
    private int commercial;   //commercial s'occupant du client
    /**
     * Contructeur de la classe <b>User</b>
     */
    public User(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public int getCommercial() {
        return commercial;
    }

    public void setCommercial(int commercial) {
        this.commercial = commercial;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getFullname(){
        String fullname = surname + " "+first_name;
        return fullname;
    }

    public void setTotal_achat(double total_achat) {
        this.total_achat = total_achat;
    }

    public double getTotal_achat() {
        return total_achat;
    }

    //Nom afficher pour la liste des commerciaux.
    @Override
    public String toString() {
        return this.getFullname();
    }
}