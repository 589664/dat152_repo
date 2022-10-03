package shop;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose language:");
		System.out.println("1 for German \n2 for Norwegian\nor any other for default");
		
		Locale locale = new Locale("en", "US"); //default
		
		String lang = keyboard.nextLine();
		
		if (lang.equals("1")) {
			locale = new Locale("de", "DE");
		}
		
		else if (lang.equals("2")) {
			locale = new Locale("no", "NO");
		}
		
		
		Cart cart = new Cart(1, new ArrayList<Product>());
		ResourceBundle apptexts = ResourceBundle.getBundle("apptexts", locale);
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product(apptexts.getString("cookies"), 20.20));		
		products.add(new Product(apptexts.getString("chocolate"), 40.20));	
		products.add(new Product(apptexts.getString("amongus"), 100.20));	
		
	    System.out.println(apptexts.getString("wellcome"));
	    System.out.println(apptexts.getString("add"));
	    
	    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
	    
	    int cnt = 1;
	    for (Product product : products) {
			System.out.println(cnt + ": Product: " + product.getName() + "\tPrice: " + formatter.format(product.getPrice()));
			cnt++;
		}
	    
	    System.out.println(apptexts.getString("purchase"));
	    
		
		String prod = "";
	    
	    do {
	    	prod = keyboard.nextLine();
	    	if(prod.compareTo("1") == 0) {
		    	cart.getProducts().add(new Product(apptexts.getString("cookies"), 20.20));
		    }
	    	else if(prod.compareTo("2") == 0) {
		    	cart.getProducts().add(new Product(apptexts.getString("chocolate"), 40.20));
		    }
	    	else if(prod.compareTo("3") == 0) {
		    	cart.getProducts().add(new Product(apptexts.getString("amongus"), 100.20));
		    }
		} while (!prod.equals("0"));
	    
	    Double sum = 0.0;
	    System.out.println(apptexts.getString("cart"));
	    for (Product product1 : cart.getProducts()) {
			System.out.println(product1.getName());
			sum += product1.getPrice();
		}
	    
	    System.out.println(apptexts.getString("sum")+ sum);
	    
		keyboard.close();
	}

}
