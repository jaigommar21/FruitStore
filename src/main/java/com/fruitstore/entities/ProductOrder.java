package com.fruitstore.entities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitstore.rules.Promotion;

public class ProductOrder {

	List<ProductItem> items;

	Double priceList ;

	Double priceFinal = null;

	List<String> promotionApply = new ArrayList<String>();
	
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
		
		PromotionItem item = promotion.execute(items);
	
		double  discount = item.getDiscount();
		promotionApply.add(item.getMessage());
		
		logger.info("Discount ==> " + discount);
		
		if( priceFinal == null) 
			priceFinal = priceList - discount;
		else
			priceFinal = priceFinal - discount;
			
		logger.info("PriceList ==> " + priceList);
		logger.info("PriceFinal ==> " + priceFinal);

	}


	public Double getPriceFinal() {
		return priceFinal;
	}


	public void setPriceFinal(Double priceFinal) {
		this.priceFinal = priceFinal;
	}


	public List<String> getPromotionApply() {
		return promotionApply;
	}


	public void setPromotionApply(List<String> promotionApply) {
		this.promotionApply = promotionApply;
	}


	public List<ProductItem> getItems() {
		return items;
	}


	public void setPriceList(Double priceList) {
		this.priceList = priceList;
	}
	
	
	
}
