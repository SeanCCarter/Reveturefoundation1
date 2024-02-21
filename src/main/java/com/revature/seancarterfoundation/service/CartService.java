package com.revature.seancarterfoundation.service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;


//Custom Imports
import com.revature.seancarterfoundation.entities.Cart;
import com.revature.seancarterfoundation.entities.Item;
import com.revature.seancarterfoundation.entities.Cart.CartKey;
import com.revature.seancarterfoundation.exceptions.ResourceNotFoundException;
import com.revature.seancarterfoundation.entities.Account;
import com.revature.seancarterfoundation.repository.AccountRepository;
import com.revature.seancarterfoundation.repository.CartRepository;
import com.revature.seancarterfoundation.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    CartRepository cartRepository;
    AccountRepository accountRepository;
    ItemRepository itemRepository;
    @Autowired
    public CartService(CartRepository cartRepository, AccountRepository accountRepository, ItemRepository itemRepository){
        this.cartRepository = cartRepository;
        this.accountRepository = accountRepository;
        this.itemRepository = itemRepository;
    }

    //Find all cart entries for user
    public List<Object> getItemsByAccount(int account_id) throws ResourceNotFoundException{
        Optional<Account> optionalAccount= accountRepository.findById(account_id);
        if(optionalAccount.isEmpty()){
            throw new ResourceNotFoundException("There is no account with that account ID");
        }
        else{
            //Todo: handle missing cart items? Not sure it's needed, but might be useful
            List<Cart> cartList = cartRepository.findByAccount(optionalAccount.get());
            //need a JSON object to return that doesn't include username and password
            List<Object> workAround = new ArrayList<Object>();  
            for (Cart entry : cartList){
                workAround.add(entry.getItem());
                workAround.add(entry.getQuantity());
            }
            return workAround;
        }
    }

    //Creates a new cart entry with quantity 1
    //Adds one to cart if item already exists
    @Transactional
    public Cart createCartEntry(int account_id, int item_id) throws ResourceNotFoundException{
        Optional<Account> optionalAccount= accountRepository.findById(account_id);
        Optional<Item> optionalItem= itemRepository.findById(item_id);
        if(optionalAccount.isEmpty()){
            throw new ResourceNotFoundException("There is no account with that account ID");
        }
        else if(optionalItem.isEmpty()){
            throw new ResourceNotFoundException("That item does not exist");
        }
        else{
            Optional<Cart> optionalCart = cartRepository.findByAccountAndItem(optionalAccount.get(), optionalItem.get());
            if (optionalCart.isPresent()){
                Cart existingCart = optionalCart.get();
                this.updateCartEntry(existingCart.getQuantity() + 1, account_id, item_id);
                existingCart.setQantity(existingCart.getQuantity() + 1);
                return existingCart;
            }
            else{
                Cart newEntry = new Cart(optionalAccount.get(), optionalItem.get(), 1);
                return cartRepository.save(newEntry);
            }
        }
    }

    //Updates the quantity in a cart
    @Transactional
    public int updateCartEntry(int newQuantity, int account_id, int item_id) throws ResourceNotFoundException{
        Optional<Account> optionalAccount= accountRepository.findById(account_id);
        Optional<Item> optionalItem= itemRepository.findById(item_id);
        if(optionalAccount.isEmpty()){
            throw new ResourceNotFoundException("There is no account with that account ID");
        }
        else if(optionalItem.isEmpty()){
            throw new ResourceNotFoundException("That item does not exist");
        }
        else if(newQuantity < 1){
            return this.deleteCartEntry(account_id, item_id);
        }
        else{
            return cartRepository.updateCart(optionalAccount.get(), optionalItem.get(), newQuantity);
        }
    }

    //Deletes every cart entry associated with an account
    //Find and delete all cart entries for a user
    public int emptyCartByAccountId(int account_id) throws ResourceNotFoundException{
        Optional<Account> optionalAccount= accountRepository.findById(account_id);
        if(optionalAccount.isEmpty()){
            throw new ResourceNotFoundException("There is no account with that account ID");
        }
        else{
            Account accountToClear = optionalAccount.get();
            return cartRepository.deleteByAccount(accountToClear);
        }
    }

    //Deletes the record of an item being in a cart
    public int deleteCartEntry(int account_id, int item_id) throws ResourceNotFoundException{
        Optional<Account> optionalAccount= accountRepository.findById(account_id);
        Optional<Item> optionalItem= itemRepository.findById(item_id);
        if(optionalAccount.isEmpty()){
            throw new ResourceNotFoundException("There is no account with that account ID");
        }
        else if(optionalItem.isEmpty()){
            throw new ResourceNotFoundException("That item does not exist");
        }
        else{
            Account accountToClear = optionalAccount.get();
            Item itemToClear = optionalItem.get();
            return cartRepository.deleteByAccountAndItem(accountToClear, itemToClear);
        }
    }



}
