package tester;

import java.util.ArrayList;

import database.DAO;
import webshop.Cart;
import webshop.Converter;
import webshop.WebshopProduct;

public class Test {
	
	public static void main(String[] args) {
		
		DAO database = new DAO();
		
		Converter conv = new Converter();
		
		ArrayList<WebshopProduct> web = conv.convert(database, "en_US");
		
		web.forEach(x -> System.out.println(x));
		
		Cart cart = new Cart(1, conv.convert(database, "en_US"));
		
		new WebshopProduct(0, 3, "White Coffee Cup™", 3.25, 6.5, "A great white cup!", "img/white.png");
		
		cart.add(1);
		cart.add(1);
		cart.add(1);
		
		
		cart.getProducts().forEach(x -> System.out.println(x));
		
//		System.out.println(cart.getItemTotal(new WebshopProduct(0,"White Coffee Cup™", 3.25, 6.5, "A great white cup!", "img/white.png"), "no_NO"));
//		System.out.println(cart.quantity(new WebshopProduct(0,"White Coffee Cup™", 3.25, 6.5, "A great white cup!", "img/white.png")));
		System.out.println(cart.grandTotal());
	}

}
