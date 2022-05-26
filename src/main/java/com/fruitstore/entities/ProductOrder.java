package com.fruitstore.entities;

import java.util.List;

public class ProductOrder {

	List<ProductItem> items;
	
	
	public void setItems(List<ProductItem> items) {
		this.items = items;
	}


	@Override
	public String toString() {
		return "ProductOrder [items=" + items + "]";
	}
	
	
	
	
	
	
}
