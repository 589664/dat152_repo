package no.hvl.dat152.h2databaseexample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.h2databaseexample.model.Item;
import no.hvl.dat152.h2databaseexample.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;
	
	/*
	 * GET /items: Return a list of all the items
	 * POST /items: Add a new Item to the list
	 * GET /items/{id}: Return the item given by "id" parameter
	 * DELETE /items/{id}: Delete an item from the list
	 * PUT /items/{id}: Update an item given by the "id" parameter and get updated information from the RequestBody. 
	 */
  
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		itemRepository.findAll().forEach(item -> items.add(item)) ;
		return items;
	}

	public Item saveOrUpdate(Item item) {
		itemRepository.save(item);
		return item;
	}
	
	public Item getItemById(int id) {
		return itemRepository.findById(id).get();
	}

	public void delete(int id) {
		itemRepository.deleteById(id);
	}
	
	public void update(int id, Item item) {
		Item item1 = itemRepository.findById(id).get();
		item1.setDescription(item.getDescription());
		item1.setName(item.getName());
		item1.setPrice(item.getPrice());
		itemRepository.save(item1);
	}
}
