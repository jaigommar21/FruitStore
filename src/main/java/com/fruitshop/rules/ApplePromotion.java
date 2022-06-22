package com.fruitshop.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitshop.entities.ProductItem;
import com.fruitshop.entities.ProductOrder;
import com.fruitshop.entities.PromotionItem;

/**
 * 
 * Buy 3 Apples and pay 2.
 * 
 * 
 * @author mbtec22
 *
 */
public class ApplePromotion implements Promotion {

	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	@Override
	public PromotionItem execute(List<ProductItem> items) {
		// TODO Auto-generated method stub

		PromotionItem promoApply = new PromotionItem();
		double discount = 0.0;

		for (ProductItem productItem : items) {

			if (APPLE_NAME.equals(productItem.getName())) {

				// Buy 3 Apples and pay 2.
				discount = ((int) (productItem.getQuantity() / 3)) * productItem.getPrice();
				logger.info("Apple quantity = " + productItem.getQuantity());
				logger.info("Apple discount = " + discount + " Euros");
				promoApply.setProductName("Apple discount = " + discount + " Euros" + " for Apple quantity = " + productItem.getQuantity());

			}

		}

		promoApply.setProductName(APPLE_NAME);
		promoApply.setDiscount(discount);		
		
		return promoApply;
		
	}
}