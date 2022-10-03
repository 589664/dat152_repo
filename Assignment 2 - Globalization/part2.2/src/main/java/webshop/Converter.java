package webshop;

import java.util.ArrayList;

import database.DAO;
import database.Product;

public class Converter {
	
	public Converter() {
	}
	
	public ArrayList<WebshopProduct> convert(DAO db, String locale) {
		
		ArrayList<WebshopProduct> webpro = new ArrayList<WebshopProduct>();
		
		ArrayList<Product> pro = db.getProducts();
		
		if (locale.equals("en_US")) {
			pro.forEach(product ->
			webpro.add(new WebshopProduct(
				product.getpNo(),
				0,
				product.getpName(),
				product.getPriceInEUR() * 0.5,
				product.getPriceInEUR(),
				db.find(product.getpNo(), locale).getText(),
				product.getImageFile()
				))
			);
		}
		else if (locale.equals("no_NO")) {
			pro.forEach(product ->
			webpro.add(new WebshopProduct(
				product.getpNo(),
				0,
				product.getpName(),
				product.getPriceInEUR() * 10,
				product.getPriceInEUR(),
				db.find(product.getpNo(), locale).getText(),
				product.getImageFile()
				))
			);
		}
		else if (locale.equals("de_DE")) {
			pro.forEach(product ->
			webpro.add(new WebshopProduct(
				product.getpNo(),
				0,
				product.getpName(),
				product.getPriceInEUR(),
				product.getPriceInEUR(),
				db.find(product.getpNo(), locale).getText(),
				product.getImageFile()
				))
			);
		}
		
		else {
			//default
		}
		
		return webpro;
		
	}
	
	public void convertWEB(DAO db, ArrayList<WebshopProduct> pro, String locale) {
		
		if (locale.equals("en_US")) {
			pro.forEach(x -> 
				{
				x.setDecsription(db.find(x.getId(), locale).getText());
				x.setPrice(x.getPriceInEUR() * 0.5);
				}
			);
		}
		else if (locale.equals("no_NO")) {
			pro.forEach(x -> 
				{
				x.setDecsription(db.find(x.getId(), locale).getText());
				x.setPrice(x.getPriceInEUR() * 10);
				}
			);
		}
		else if (locale.equals("de_DE")) {
			pro.forEach(x -> 
				{
				x.setDecsription(db.find(x.getId(), locale).getText());
				x.setPrice(x.getPriceInEUR());
				}
			);
		}
		else {
			//default
		}
		
	}

}
