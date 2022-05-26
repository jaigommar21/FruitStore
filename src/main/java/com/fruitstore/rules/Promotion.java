package com.fruitstore.rules;

import java.util.List;

import com.fruitstore.entities.ProductItem;

public interface Promotion {

	static String APPLE_NAME = "Apple";
	static String PEAR_NAME = "Pear";
	static String ORANGE_NAME = "Orange";
	
	
	double execute(List<ProductItem> items);

}
