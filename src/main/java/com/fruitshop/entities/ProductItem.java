package com.fruitshop.entities;

public class ProductItem {

	private String name;
	private int quantity;
	private double price;

	public ProductItem(String name, int quantity, double price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public ProductItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	// private double total;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotalPrice() {
		return this.price * this.quantity;
	}
	
	

	@Override
	public String toString() {
		return "ProductOrder [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
