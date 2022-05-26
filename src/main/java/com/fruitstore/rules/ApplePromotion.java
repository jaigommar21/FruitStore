package com.fruitstore.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fruitstore.entities.ProductItem;
import com.fruitstore.entities.ProductOrder;

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
	public double execute(List<ProductItem> items) {
		// TODO Auto-generated method stub

		double discount = 0.0;

		for (ProductItem productItem : items) {

			if (APPLE_NAME.equals(productItem.getName())) {

				// Buy 3 Apples and pay 2.
				discount = ((int) (productItem.getQuantity() / 3)) * productItem.getPrice();
				logger.info("Apple quantity = " + productItem.getQuantity());
				logger.info("Apple discount = " + discount);

			}

		}

		return discount;

	}
}