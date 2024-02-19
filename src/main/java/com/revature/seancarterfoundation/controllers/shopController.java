package com.revature.seancarterfoundation.controllers;

//Spring Imports
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//Project Imports
import com.revature.seancarterfoundation.entities.Account;
import com.revature.seancarterfoundation.entities.Item;
import com.revature.seancarterfoundation.entities.Cart;
import com.revature.seancarterfoundation.exceptions.DuplicateAccountException;
import com.revature.seancarterfoundation.exceptions.ResourceNotFoundException;
import com.revature.seancarterfoundation.service.AccountService;
import com.revature.seancarterfoundation.service.ItemService;
import com.revature.seancarterfoundation.service.CartService;

//Java Imports
import java.util.Optional;
import java.util.logging.Logger;
import java.util.List;
import java.util.Map;



@RestController
public class shopController {

    @Autowired
    private ApplicationContext applicationContext;
    private AccountService accountService;
    private ItemService itemService;
    private CartService cartService;
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    public void init(){
        accountService = applicationContext.getBean(AccountService.class);
        itemService = applicationContext.getBean(ItemService.class);
        cartService = applicationContext.getBean(CartService.class);
    }

    //Lets users visit localhost endpoint without needing to know html location
    @GetMapping("/")
    public RedirectView getHomePage(){
        return new RedirectView("home.html");
    }

    //Account Management
    @PostMapping(value = "/register")
    public Account createNewAccount(@RequestBody Account newAccount) throws DuplicateAccountException{
        return accountService.createNewAccount(newAccount);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Account> checkAccountLogin(@RequestBody Account account) {
        Optional<Account> optionalAccount= accountService.checkAccountLogin(account);
        if(optionalAccount.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalAccount.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //Retrieving Sodas
    @GetMapping(value = "/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "/items/categories/{category}")
    public List<Item> getItemByCategory(@PathVariable String category){
        return itemService.getItemsByCategory(category);
    }

    @GetMapping(value = "/items/sellers/{seller}")
    public List<Item> getItemBySeller(@PathVariable String seller){
        return itemService.getItemsBySeller(seller);
    }


    //Managing Carts
    //Returns a List alternating between items and the quantity of those items
    @GetMapping(value = "/cart/accounts/{account_id}")
    public List<Object> getCartByAccount(@PathVariable int account_id) throws ResourceNotFoundException{
        return cartService.getItemsByAccount(account_id);
        
    }

    //Create a new entry in the cart
    //If the entry already exists, incriment by one
    @PostMapping(value = "/cart/entries/{account_id}/{item_id}")
    public Cart createCartEntry(@PathVariable int account_id, @PathVariable int item_id) throws ResourceNotFoundException{
        return cartService.createCartEntry(account_id, item_id);
    }

    //Updates the quantity of an item in a cart. If it's less than 1, the record is deleted
    @PatchMapping(value = "/cart/entries/{account_id}/{item_id}")
    public int updateMessage(@RequestBody Map<String, String> request, @PathVariable int account_id, @PathVariable int item_id) throws ResourceNotFoundException{
        return cartService.updateCartEntry(Integer.parseInt(request.get("newQuantity")), account_id, item_id);
    }

    @DeleteMapping(value = "/cart/accounts/{account_id}")
    public int emptyCartByAccount(@PathVariable int account_id) throws ResourceNotFoundException{
        return cartService.emptyCartByAccountId(account_id);
    }

    @DeleteMapping(value = "/cart/entries/{account_id}/{item_id}")
    public int deleteCartEntry(@PathVariable int account_id, @PathVariable int item_id) throws ResourceNotFoundException{
        return cartService.deleteCartEntry(account_id, item_id);
    }

    
    // Handling specific error types
    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleInvalidAccount(DuplicateAccountException error) {
        return error.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleNoDatabaseEntry(ResourceNotFoundException error) {
        return error.getMessage();
    }
}
