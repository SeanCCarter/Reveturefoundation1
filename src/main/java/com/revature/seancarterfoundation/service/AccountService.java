package com.revature.seancarterfoundation.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//Custom Imports
import com.revature.seancarterfoundation.entities.Account;
import com.revature.seancarterfoundation.repository.AccountRepository;
import com.revature.seancarterfoundation.exceptions.DuplicateAccountException;

@Service
public class AccountService {

    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /**
    * Checks whether a potential account works before saving it
    * @param newAccount A new User
    * @return the user created with new IDs
    */
    public Account createNewAccount(Account newAccount) throws DuplicateAccountException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(newAccount.getUsername());
        if(optionalAccount.isPresent()){
            throw new DuplicateAccountException("User already exists");
        }
        else{
            return accountRepository.save(newAccount);
        }
    }

    /**
    * Checks whether a login attempt has a matching username and password
    * @param account A message object to persist
    * @return the account, if found
    */
    public Optional<Account> checkAccountLogin(Account account) {
        return accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());    
    }
    
}
