package com.wei.wigellsushi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID")
    private int userID;

    @Column(name = "userName")
    @NotNull
    @Size(max = 20)
    private String userName;

    @Column(name = "userFullName")
    @NotNull
    @Size(max = 50)
    private String userFullName;
    @Column(name = "address")
    @NotNull
    @Size(max = 100)
    private String address;

    public User() {
    }

    public User(int userID, String userName, String userFullName, String adress) {
        this.userID = userID;
        this.userName = userName;
        this.userFullName = userFullName;
        this.address = adress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
