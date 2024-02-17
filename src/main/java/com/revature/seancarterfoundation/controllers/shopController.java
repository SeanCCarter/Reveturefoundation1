package com.revature.seancarterfoundation.controllers;

//Spring Imports
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@RestController
public class shopController {
    
    @GetMapping("/")
    public RedirectView getHomePage(){
        return new RedirectView("helloWorld.html");
    }
}
