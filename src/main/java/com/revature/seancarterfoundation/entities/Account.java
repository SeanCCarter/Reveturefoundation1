package com.revature.seancarterfoundation.entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Account {

    //Creates a new unique ID for the account
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer account_id;

    //Setting up the user's cart
    // @OneToMany(mappedBy = "account")
    // Set<Cart> cart;

    //Requires a username and password for the account. Neither can be empty
    private String username;
    private String password;


    //Constructors
    public Account(){

    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(Integer account_id, String username, String password) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
    }


    //Getters and setters
    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
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

    // Set thing
    // public Set<Cart> getCart() {return cart;}
    // public void setCart(Set<Cart> cart) {this.cart = cart;}
}
