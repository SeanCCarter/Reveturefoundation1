package com.revature.seancarterfoundation.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//Custom Imports
import com.revature.seancarterfoundation.entities.Item;
import com.revature.seancarterfoundation.repository.ItemRepository;

@Service
public class ItemService {

    ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    /**
     * @return all items
     */
    public List<Item> getAllItems() {
        //return new ArrayList<Message>();
        return itemRepository.findAll();
    }

    /**
     * Gets items based on the seller
     * @param seller The seller
     * @return the items sold by the seller
     */
    public List<Item> getItemsBySeller(String seller){
        return itemRepository.findItemsBySeller(seller);
    }

    /**
     * Gets items based on the seller
     * @param seller The seller
     * @return the items sold by the seller
     */
    public List<Item> getItemsByCategory(String category){
        return itemRepository.findItemsByCategory(category);
    }

}
