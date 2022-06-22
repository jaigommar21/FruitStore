package com.fruitshop.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitshop.entities.ProductItem;
import com.fruitshop.entities.ProductOrder;
import com.fruitshop.entities.PromotionItem;

/**
 * 
 * Get a free Orange for every 2 Pears you buy.
 * 
 * 
 * @author mbtec22
 *
 */
public class OrangePromotion implements Promotion {

	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	
	@Override
	public PromotionItem execute(List<ProductItem> items) {
		// TODO Auto-generated method stub

		PromotionItem promoApply = new PromotionItem();

		double discountOrangeFree = 0.0;
		double discount = 0.0;
		int orangeFree = 0;

		for (ProductItem productItem : items) {

			if (PEAR_NAME.equals(productItem.getName())) {

				// Calculate how many free Orange are there?
				logger.info("Pear's quantity = " + productItem.getQuantity());
				orangeFree = ((int) (productItem.getQuantity() / 2));
				logger.info("Orange free = " + orangeFree);
				
				
			}

		}

		for (ProductItem productItem : items) {

			if (ORANGE_NAME.equals(productItem.getName())) {

				// How many discount it is for free Orange?
				discountOrangeFree = orangeFree*productItem.getPrice();
				logger.info("Orange free discount = " + discountOrangeFree);

				promoApply.setProductName("Orange discount = " + discountOrangeFree + " Euros" + " for Orange free = " + orangeFree);

			}

		}
		
		discount = discountOrangeFree;
		
		promoApply.setProductName(ORANGE_NAME);
		promoApply.setDiscount(discount);
		
		return promoApply;
	}

}
