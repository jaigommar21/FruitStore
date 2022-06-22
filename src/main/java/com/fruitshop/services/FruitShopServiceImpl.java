package com.fruitshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fruitshop.entities.ProductItem;
import com.fruitshop.entities.ProductOrder;
import com.fruitshop.rules.ApplePromotion;
import com.fruitshop.rules.OrangePromotion;
import com.fruitshop.rules.PearPromotion;
import com.fruitshop.rules.Promotion;


@Service
public class FruitShopServiceImpl implements FruitShopService {

	
	
	private Logger logger = LoggerFactory.getLogger(FruitShopServiceImpl.class);
	
	@Override
	public ProductOrder calculateBill(Map<String, String> prodsPriceRaw, Map<String, String> prodsQuantityRaw) {
		// TODO Auto-generated method stub
		
		logger.info("call calculateBill() ");

		List<ProductItem> items = getOrder(prodsPriceRaw, prodsQuantityRaw);
		
		ProductOrder order = new ProductOrder();
		order.setItems(items);
		
		logger.info("Order ===> " + order.toString());
		
		order.calculatePriceList();
		logger.info("PriceList ===> " + order.getPriceList());
		
		Promotion[] promotions = {  new ApplePromotion(), 
									new OrangePromotion(),
									new PearPromotion() };

		for (Promotion promotion : promotions) {
			order.applyPromotion(promotion);	
		}
		
		/*
		// Apply Orange promotion
		order.applyPromotion(new ApplePromotion());
		
		// Apply Orange promotion
		order.applyPromotion(new OrangePromotion());

		// Apply Pear promotion
		order.applyPromotion(new PearPromotion());
		*/
		
		return order;
	}

	
	
	private List<ProductItem>  getOrder(Map<String, String> prodsPriceRaw, Map<String, String> prodsQuantityRaw) {

		List<ProductItem> items = new ArrayList<ProductItem>();
		
		
		
		for (String nameProduct :prodsPriceRaw.keySet()) {
			
			logger.info(nameProduct);
			logger.info(prodsPriceRaw.get(nameProduct));
			logger.info(prodsQuantityRaw.get(nameProduct));

			ProductItem item =  new ProductItem();
			item.setName(nameProduct);
			item.setPrice(Double.parseDouble(prodsPriceRaw.get(nameProduct).trim()));
			item.setQuantity(Integer.parseInt(prodsQuantityRaw.get(nameProduct).trim()));
			
			logger.info(item.toString());
			items.add(item);
			
		}
		
		return items;
	}

	
}
