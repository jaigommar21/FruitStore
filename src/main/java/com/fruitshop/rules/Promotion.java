package com.fruitshop.rules;

import java.util.List;

import com.fruitshop.entities.ProductItem;
import com.fruitshop.entities.PromotionItem;

public interface Promotion {

	static String APPLE_NAME = "Apple";
	static String PEAR_NAME = "Pear";
	static String ORANGE_NAME = "Orange";
	
	
	PromotionItem execute(List<ProductItem> items);

}
