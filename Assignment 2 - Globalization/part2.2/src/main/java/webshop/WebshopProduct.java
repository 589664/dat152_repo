package webshop;

import java.util.Objects;

public class WebshopProduct {
	
	private Integer id;
	private Integer quantity;
	private String name;
	private Double price;
	private Double priceInEUR;
	private String decsription;
	private String imageFile;
	
	public WebshopProduct() {
	}

	public WebshopProduct(Integer id, Integer quantity, String name, Double price, Double priceInEUR,
			String decsription, String imageFile) {
		this.id = id;
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.priceInEUR = priceInEUR;
		this.decsription = decsription;
		this.imageFile = imageFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceInEUR() {
		return priceInEUR;
	}

	public void setPriceInEUR(Double priceInEUR) {
		this.priceInEUR = priceInEUR;
	}

	public String getDecsription() {
		return decsription;
	}

	public void setDecsription(String decsription) {
		this.decsription = decsription;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	//other methods

	@Override
	public String toString() {
		return "WebshopProduct [id=" + id + ", quantity=" + quantity + ", name=" + name + ", price=" + price
				+ ", priceInEUR=" + priceInEUR + ", decsription=" + decsription + ", imageFile=" + imageFile + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(decsription, id, imageFile, name, price, priceInEUR, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebshopProduct other = (WebshopProduct) obj;
		return Objects.equals(decsription, other.decsription) && Objects.equals(id, other.id)
				&& Objects.equals(imageFile, other.imageFile) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(priceInEUR, other.priceInEUR)
				&& Objects.equals(quantity, other.quantity);
	}
	
}
