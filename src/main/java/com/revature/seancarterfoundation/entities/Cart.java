package com.revature.seancarterfoundation.entities;
import java.io.Serializable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @EmbeddedId
    CartKey cart_id;

    @ManyToOne
    @MapsId("account_id")
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    Item item;

    int quantity;

    //Constructors
    public Cart(){

    }
    
    public Cart(Account account, Item item, int quantity){
        this.cart_id = new CartKey(account.getAccount_id(), item.getItem_id());
        this.account = account;
        this.item = item;
        this.quantity = quantity;
    }

    public Cart(CartKey cart_id, Account account, Item item, int quantity) {
        this.cart_id = cart_id;
        this.account = account;
        this.item = item;
        this.quantity = quantity;
    }

    public CartKey getCart_id() {return cart_id;}
    public void setCart_id(CartKey cart_id) {this.cart_id = cart_id;}

    public Account getAccount() {return account;}
    public void setAccount(Account account) {this.account = account;}

    public Item getItem() {return item;}
    public void setItem(Item item) {this.item = item;}

    public int getQuantity() {return quantity;}
    public void setQantity(int quantity) {this.quantity = quantity;}

    //Defining the composite key
    @Embeddable
    public static class CartKey implements Serializable {
        //private static final long serialVersionUID = 1L;

        @Column(name = "account_id")
        private int account_id;

        @Column(name = "item_id")
        private int item_id;

        // standard constructors, getters, and setters
        public CartKey(){
        }
        
        public CartKey(int account_id, int item_id){
            this.account_id = account_id;
            this.item_id = item_id;
        }

        //Getters and setters
        public Integer getAccount_id() {return account_id;}
        public void setAccount_id(Integer account_id) {this.account_id = account_id;}

        public Integer getItem_id() {return item_id;}
        public void setItem_id(Integer item_id) {this.item_id = item_id;}

        // // hashcode and equals implementation, required for Serializable
        // // using the apache builder library
        // public boolean equals(CartKey key){
        //     return (key.account_id == this.account_id) && (key.item_id == this.item_id);
        // }

        // public int hashCode(){
        //     return new HashCodeBuilder(17,37).append(account_id).append(item_id).toHashCode();
        // }
    }
}

