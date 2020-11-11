package com.example.productproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_pic")
    private String profilePic;

    public User() {

    }

    public User(String username, String password, String profilePic) {
        this.setUsername(username);
        this.setPassword(password);
        this.setProfilePic(profilePic);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    //    @CreationTimestamp
//    @Column(name = "create_date", nullable = false, updatable = false)
//    private Date createDate;
//
//    @UpdateTimestamp
//    @Column(name = "update_date")
//    private Date updateDate;
//
//    @Column(name = "role")
//    private String role;
}
