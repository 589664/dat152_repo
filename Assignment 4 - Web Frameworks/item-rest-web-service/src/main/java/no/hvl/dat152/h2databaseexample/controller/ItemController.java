package no.hvl.dat152.h2databaseexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.hvl.dat152.h2databaseexample.model.Item;
import no.hvl.dat152.h2databaseexample.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	ItemService service;
	
	/*
	 * GET /items: Return a list of all the items
	 * POST /items: Add a new Item to the list
	 * GET /items/{id}: Return the items given by "id" parameter
	 * DELETE /items/{id}: Delete an item from the list
	 * PUT /items/{id}: Update an item given by the "id" parameter and get updated information from the RequestBody. 
	 */

	@GetMapping("/items")
	private List<Item> getAllItems() {
		return service.getAllItems();
	}
	
	@PostMapping("/items")
	private int saveItem(@RequestBody Item item) {
		service.saveOrUpdate(item);
		return item.getId();
	}

	@GetMapping("/items/{id}")
	private Item getItem(@PathVariable("id") int id) {
		return service.getItemById(id);
	}

	@DeleteMapping("/items/{id}")
	private void deleteItem(@PathVariable("id") int id) {
		service.delete(id);
	}

	@PutMapping("/items/{id}")
	private void updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		service.update(id, item);
	}
	
}
