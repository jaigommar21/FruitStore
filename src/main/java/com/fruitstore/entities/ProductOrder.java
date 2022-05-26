package com.fruitstore.entities;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitstore.rules.Promotion;

public class ProductOrder {

	List<ProductItem> items;

	Double priceList ;

	Double priceFinal = null;

	
	public void setItems(List<ProductItem> items) {
		this.items = items;
	}

	
	public Double getPriceList() {
		return priceList;
	}

	@Override
	public String toString() {
		return "ProductOrder [items=" + items + "]";
	}
	
	public void calculatePriceList() {
		
		this.priceList = 0.0;
		
		for (ProductItem productItem : items) {
			
			this.priceList += productItem.getPrice()*productItem.getQuantity() ;
			
		}
		
	}
	
	
	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	
	public void applyPromotion(Promotion promotion) {
		
		double discount = promotion.execute(items);
	
		logger.info("Discount ==> " + discount);
		
		if( priceFinal == null) 
			priceFinal = priceList - discount;
		else
			priceFinal = priceFinal - discount;
			
		logger.info("PriceList ==> " + priceList);
		logger.info("PriceFinal ==> " + priceFinal);

		
		
	}
	
	
}
