package com.revature.seancarterfoundation.entities;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;

@Entity
public class Item {
    //Creates a new unique ID for the item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;

    //Allows item to be added to a cart
    // @OneToMany(mappedBy = "item")
    // Set<Cart> cart;

    //Items require a bunch of info
    private String name; //name of item
    private String category; //for sorting
    private String seller; //
    private String imageLocation;
    private String description;
    private float price;

     //Constructors
     public Item(){

     }

     @Autowired
     public Item(String name, String category, String seller, String imageLocation, String description, float price){
     }

     @Autowired
     public Item(int item_id, String name, String category, String seller, String imageLocation, String description, float price){
    }

    //Getters and setters
    public Integer getItem_id() {return item_id;}
    public void setItem_id(Integer item_id) {this.item_id = item_id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getSeller() {return seller;}
    public void setSeller(String seller) {this.seller = seller;}

    public String getImageLocation() {return imageLocation;}
    public void setImageLocation(String imageLocation) {this.imageLocation = imageLocation;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public float getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}

    // Set thing
    // public Set<Cart> getCart() {return cart;}
    // public void setCart(Set<Cart> cart) {this.cart = cart;}
}
