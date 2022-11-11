package u589664.dat152.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import u589664.dat152.model.Item;
import u589664.dat152.repository.ItemRepository;
import u589664.dat152.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	ItemService service;
	@Autowired
	ItemRepository itemRepository;
	/*
	 * GET /items: Return a list of all the items
	 * POST /items: Add a new Item to the list
	 * GET /items/{id}: Return the items given by "id" parameter
	 * DELETE /items/{id}: Delete an item from the list
	 * PUT /items/{id}: Update an item given by the "id" parameter and get updated information from the RequestBody. 
	 */

	@GetMapping("/items")
	private ResponseEntity<List<Item>> getAllItems() {
		
		List<Item> items = service.getAllItems();
		
		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PostMapping("/items")
	private ResponseEntity<Item> saveItem(@RequestBody Item item) {
		try {
			service.saveOrUpdate(item);
			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/items/{id}")
	private ResponseEntity<Item> getItem(@PathVariable("id") int id) {
		
		Optional<Item> item1 = service.getItemById(id);
		
		if (!item1.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Item());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(item1.get());
	}

	@DeleteMapping("/items/{id}")
	private ResponseEntity<?> deleteItem(@PathVariable("id") int id) {
		
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//without service all in one
	@PutMapping("/items/{id}")
	private ResponseEntity<Item> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
	
		Optional<Item> itemRepo = itemRepository.findById(id);
		
		if (itemRepo.isPresent()) {
			Item thisItem = itemRepo.get();
			thisItem.setDescription(item.getDescription());
			thisItem.setName(item.getName());
			thisItem.setPrice(item.getPrice());
			return new ResponseEntity<>(itemRepository.save(thisItem), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
