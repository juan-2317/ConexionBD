package co.edu.usbbog.prg2.jdbc.entity;

import java.util.Date;

public class Usuario {

    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private Date dateLastPassword;
    private String active;
    private String typeUser;

    public Usuario() {
    }

    public Usuario(int id, String userName, String password, String fullName,
            String emailAddress, String phoneNumber, Date dateLastPassword,
            String active, String typeUser) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateLastPassword = dateLastPassword;
        this.active = active;
        this.typeUser = typeUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateLastPassword() {
        return dateLastPassword;
    }

    public void setDateLastPassword(Date dateLastPassword) {
        this.dateLastPassword = dateLastPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

}
