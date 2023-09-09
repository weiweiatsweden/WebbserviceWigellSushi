package com.wei.wigellsushi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name ="admin" )
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="adminID")
    private int adminID;
    @Size(max = 20)
    @NotNull(message = "Admin username cannot be null")
    @Column(name = "adminUserName")
    private String adminUserName;

    @Size(max = 100)
    @NotNull(message = "Admin full name cannot be null")
    @Column(name = "adminFullName")
    private String adminFullName;

    public Admin() {
    }

    public Admin(int adminID, String adminUserName, String adminFullName) {
        this.adminID = adminID;
        this.adminUserName = adminUserName;
        this.adminFullName = adminFullName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminFullName() {
        return adminFullName;
    }

    public void setAdminFullName(String adminFullName) {
        this.adminFullName = adminFullName;
    }
}
