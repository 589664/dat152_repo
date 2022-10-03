package database;

public class Product {
	
	private Integer pNo;
	private String pName;
	private Double priceInEUR;
	private String imageFile;
	
	
	public Product() {
	}


	public Product(Integer pNo, String pName, Double priceInEUR, String imageFile) {
		this.pNo = pNo;
		this.pName = pName;
		this.priceInEUR = priceInEUR;
		this.imageFile = imageFile;
	}


	public Integer getpNo() {
		return pNo;
	}


	public void setpNo(Integer pNo) {
		this.pNo = pNo;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public Double getPriceInEUR() {
		return priceInEUR;
	}


	public void setPriceInEUR(Double priceInEUR) {
		this.priceInEUR = priceInEUR;
	}


	public String getImageFile() {
		return imageFile;
	}


	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pName=" + pName + ", priceInEUR=" + priceInEUR + ", imageFile=" + imageFile
				+ "]";
	}
}
