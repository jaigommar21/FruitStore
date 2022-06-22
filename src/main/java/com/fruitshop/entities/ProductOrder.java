package com.fruitshop.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitshop.rules.Promotion;

public class ProductOrder {
	
	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	int NUMBER_DECIMAL = 2;
	
	List<ProductItem> items;

	BigDecimal priceList = null;

	BigDecimal priceFinal = null;

	List<PromotionItem> promotionsApply = new ArrayList<PromotionItem>();
	
	public void calculatePriceList() {
		
		this.priceList = new BigDecimal(0.0);
		
		for (ProductItem productItem : items) {
			
			//this.priceList += productItem.getPrice()*productItem.getQuantity() ;
			
			BigDecimal priceProdItem = new BigDecimal(productItem.getPrice()*productItem.getQuantity());
			
			this.priceList 
				= this.priceList.add(priceProdItem)
					.setScale(NUMBER_DECIMAL, RoundingMode.HALF_UP);
		}
		
	}

	
	public void applyPromotion(Promotion promotion) {
		
		PromotionItem item = promotion.execute(items);
		promotionsApply.add(item);
		
		double  discount = item.getDiscount();
		logger.info("Discount ==> " + discount);
		
		if( priceFinal == null) 
			priceFinal 
				= priceList.subtract(new BigDecimal(discount))
					.setScale(NUMBER_DECIMAL, RoundingMode.HALF_UP);
		else
			priceFinal 
				= priceFinal.subtract(new BigDecimal(discount))
					.setScale(NUMBER_DECIMAL, RoundingMode.HALF_UP);;

					
		logger.info("PriceList ==> " + priceList);
		logger.info("PriceFinal ==> " + priceFinal);

	}


	public Double getPriceFinal() {
		return priceFinal.doubleValue();
	}


	public List<PromotionItem> getPromotionsApply() {
		return promotionsApply;
	}

	public List<ProductItem> getItems() {
		return items;
	}

	public void setItems(List<ProductItem> items) {
		this.items = items;
	}

	
	public BigDecimal getPriceList() {
		return priceList;
	}

	@Override
	public String toString() {
		return "ProductOrder [items=" + items + "]";
	}
	
}
