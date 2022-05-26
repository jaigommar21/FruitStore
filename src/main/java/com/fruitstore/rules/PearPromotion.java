package com.fruitstore.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitstore.entities.ProductItem;
import com.fruitstore.entities.ProductOrder;

/**
 * 
 * Get a free Orange for every 2 Pears you buy.
 * For every 4 euros spent on Pears, we will deduct one euro from your final invoice.
 * 
 * 
 * @author mbtec22
 *
 */
public class PearPromotion implements Promotion {

	private Logger logger = LoggerFactory.getLogger(ProductOrder.class);

	@Override
	public double execute(List<ProductItem> items) {
		// TODO Auto-generated method stub

		double discountPricePear = 0.0;
		double discountOrangeFree = 0.0;
		double discount = 0.0;
		int orangeFree = 0;

		for (ProductItem productItem : items) {

			if (PEAR_NAME.equals(productItem.getName())) {

				// Calculate how many free Orange are there?
				logger.info("Pear's quantity = " + productItem.getQuantity());
				orangeFree = ((int) (productItem.getQuantity() / 2));
				logger.info("Orange free = " + orangeFree);
				
				// Calculate discount for Pear price
				discountPricePear = ((int) (productItem.getPrice()*productItem.getQuantity()/ 4));
				logger.info("Pear's price = " + productItem.getPrice());
				logger.info("Discount for Pear's price = " + discountPricePear);
				
			}

		}

		for (ProductItem productItem : items) {

			if (ORANGE_NAME.equals(productItem.getName())) {

				// How many discount it is for free Orange?
				discountOrangeFree = orangeFree*productItem.getPrice();
				logger.info("Orange free discount = " + discountOrangeFree);
			}

		}
		
		discount = discountPricePear + discountOrangeFree;
		
		return discount;
	}

}
