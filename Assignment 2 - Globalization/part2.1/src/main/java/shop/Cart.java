package shop;

import java.util.ArrayList;

public class Cart {
	
	private int cartId;
	private ArrayList<Product> products;
	
	public Cart() {
	}

	public Cart(int cartId, ArrayList<Product> products) {
		this.cartId = cartId;
		this.products = products;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", products=" + products + "]";
	}
	
}
