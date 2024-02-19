package com.revature.seancarterfoundation.repository;
import com.revature.seancarterfoundation.entities.Account;
import com.revature.seancarterfoundation.entities.Item;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ItemRepository extends JpaRepository<Item, Integer>{

    Optional<Item> findById(int item_id);
    Optional<Item> deleteById(int item_id);

    /**
     * @param category an category of item.
     * @return the items from that category
     */
    @Query("FROM Item WHERE category = :category")
    List<Item> findItemsByCategory(String category);

    /**
     * @param seller the seller of an item.
     * @return the items from that category
     */
    @Query("FROM Item WHERE seller = :seller")
    List<Item> findItemsBySeller(String seller);
    
    
}
