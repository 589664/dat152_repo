package database;

import java.util.ArrayList;

public class DAO {
	
	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Description> descriptions = new ArrayList<Description>();
	
	public DAO() {
		
		//endre etterpå
		
		Product whiteC = new Product(0, "White Coffee Cup™", 6.50, "img/white.png");
		Product blackC = new Product(1, "Black Coffee Cup™", 4.75, "img/black.png");
		
		products.add(whiteC);
		products.add(blackC);
		
		Description engWDesc = new  Description(0, "en_US", "This is a white cup mongus");
		Description norWDesc = new  Description(0, "no_NO", "Dette er en hvitt kopp mongus");
		Description gerWDesc = new  Description(0, "de_DE", "Dies ist ein Mongus mit weißer Tasse mongus");
		
		Description engBDesc = new  Description(1, "en_US", "This is a black cup mongus");
		Description norBDesc = new  Description(1, "no_NO", "Dette er en svart kopp mongus");
		Description gerBDesc = new  Description(1, "de_DE", "Dies ist eine schwarze Tasse mongus");
		
		descriptions.add(engWDesc);
		descriptions.add(norWDesc);
		descriptions.add(gerWDesc);
		descriptions.add(engBDesc);
		descriptions.add(norBDesc);
		descriptions.add(gerBDesc);

	}
	
	//get & set

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(ArrayList<Description> descriptions) {
		this.descriptions = descriptions;
	}
	
	//other
	
	public Description find(Integer pNo, String locale) {
		
		return descriptions.stream()
		.filter(x -> (x.getpNo().equals(pNo) && x.getLangCode().equals(locale)))
		.findAny()
		.get();
	}
	
	
}
