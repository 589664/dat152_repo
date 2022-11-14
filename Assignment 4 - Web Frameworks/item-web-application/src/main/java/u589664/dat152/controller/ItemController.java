package u589664.dat152.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import u589664.dat152.model.Item;

@Controller
public class ItemController {
    
    @Autowired
    private RestTemplate restTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewShoppingDefault() {
		return "index";
    }
	
	@RequestMapping(value = "/viewitems", method = RequestMethod.GET)
    public String viewShoppingList(Model model) {
		
		final List<Item> items = Arrays.asList(restTemplate.getForEntity("http://localhost:8299/items", Item[].class).getBody());
		
		model.addAttribute("items", items);
		
		return "shoppinglist";
        
    }
	
	@RequestMapping(value = "/viewitem/{id}", method = RequestMethod.GET)
    protected String viewItem(@PathVariable String id, Model model) {
		
		String url = "http://localhost:8299/items/" + id;
		
        final Item item = restTemplate.getForEntity(url, Item.class).getBody();
        model.addAttribute("item", item);

        return "viewitem";
    }
	
	@GetMapping("/deleteitem")
	public String deleteItem(@RequestParam String id) {
		String url = "http://localhost:8299/items/" + id;
        
        restTemplate.delete(url, id);
		return "redirect:/viewitems";
	}
	
	@RequestMapping(value = "/createitem", method = RequestMethod.GET)
    protected String createItem(Model model) {
        return "createitem";
    }
	
	@RequestMapping(value = "/createitem", method = RequestMethod.POST)
    protected String createItem(@RequestParam String name, 
    		                    @RequestParam Double price, @RequestParam String description, 
    		                    Model model) {
		
		String url = "http://localhost:8299/items";
		
		final Item newItem = new Item(name, price, description);
		
		restTemplate.postForObject(url, newItem, Item.class);
        
        return "redirect:viewitems";
    }
	
	@RequestMapping(value = "/updateitem/{id}", method = RequestMethod.GET)
    protected String updateItem(@PathVariable String id, Model model) {
		String url = "http://localhost:8299/items/" + id;
		
        final Item item = restTemplate.getForEntity(url, Item.class).getBody();
        model.addAttribute("item", item);
		
        return "updateitem";
    }
	
	@PostMapping("/updateitem")
    protected String updateItem(@RequestParam String id, @RequestParam String name, 
    		                    @RequestParam Double price, @RequestParam String description, 
    		                    Model model) {
		
		String url = "http://localhost:8299/items/" + id;
		
		Item newItem = new Item(name, price, description);
		
		restTemplate.put(url, newItem);
        
        return "redirect:viewitems";
    }
	
}
