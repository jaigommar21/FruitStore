package com.fruitstore.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitstore.entities.ProductItem;
import com.fruitstore.entities.ProductOrder;
import com.fruitstore.entities.PromotionItem;

/**
 * 
 * For every 4 euros spent on Pears, we will deduct one euro from your final invoice.
 * 
 * 
 * @author mbtec22
 *
 */
public class PearPromotion implements Promotion {

	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	@Override
	public PromotionItem execute(List<ProductItem> items) {
		// TODO Auto-generated method stub

		double discountPricePear = 0.0;
		double discount = 0.0;
		
		PromotionItem promoApply = new PromotionItem();

		for (ProductItem productItem : items) {

			if (PEAR_NAME.equals(productItem.getName())) {

				// Calculate discount for Pear price
				discountPricePear = ((int) (productItem.getPrice()*productItem.getQuantity()/ 4));
				logger.info("Pear's price = " + productItem.getPrice());
				logger.info("Discount for Pear's price = " + discountPricePear);
				
				promoApply.setMessage("Discount for Pear's price = " + discountPricePear);

			}

		}
		
		discount = discountPricePear;
		
		promoApply.setDiscount(discount);
		
		return promoApply;
	}

}
