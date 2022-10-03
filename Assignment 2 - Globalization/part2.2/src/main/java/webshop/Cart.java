package webshop;

import java.util.ArrayList;

public class Cart {
	
	private int cartId;
	private ArrayList<WebshopProduct> products;
	public static ArrayList<WebshopProduct> staticItems = new ArrayList<WebshopProduct>();
	
	public Cart() {
		this.products = staticItems;
	}

	public Cart(int cartId, ArrayList<WebshopProduct> products) {
		this.cartId = cartId;
		this.products = products;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public ArrayList<WebshopProduct> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<WebshopProduct> products) {
		this.products = products;
	}
	
	public void add(int id) {
		WebshopProduct product = products.stream()
		.filter(x -> x.getId().equals(id))
		.findFirst()
		.get();
		product.setQuantity(product.getQuantity() + 1);
	}
	
	public Double getItemTotal(WebshopProduct item) {
		return item.getQuantity() * item.getPrice();
	}
	
	public Double grandTotal() {
		return 
		products.stream()
		.map(x -> x.getPrice() * x.getQuantity())
		.reduce(0.00, Double::sum)
		;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", products=" + products + "]";
	}
	
}
