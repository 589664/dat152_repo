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
  
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		itemRepository.findAll().forEach(item -> items.add(item)) ;
		return items;
	}

	public Item getItemById(int id) {
		return itemRepository.findById(id).get();
	}

	public Item saveOrUpdate(Item item) {
		itemRepository.save(item);
		return item;
	}
 
	public void delete(int id) {
		itemRepository.deleteById(id);
	}
}
