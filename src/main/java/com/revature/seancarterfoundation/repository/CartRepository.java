package com.revature.seancarterfoundation.repository;
import com.revature.seancarterfoundation.entities.Cart;
import com.revature.seancarterfoundation.entities.Item;

import jakarta.transaction.Transactional;

import com.revature.seancarterfoundation.entities.Account;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer>{

    @Transactional
    int deleteByAccount(Account account);
    @Transactional
    int deleteByAccountAndItem(Account account, Item item);

    @Modifying
    @Query("UPDATE Cart e SET e.quantity = :newQuantity WHERE e.account = :account AND e.item = :item")
    int updateCart(@Param(value = "account") Account account,@Param(value = "item") Item item, @Param(value = "newQuantity") int newQuantity);

    List<Cart> findByAccount(Account account);
    Optional<Cart> findByAccountAndItem(Account account, Item item);

    
}
