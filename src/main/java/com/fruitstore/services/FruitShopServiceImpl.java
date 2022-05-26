package com.fruitstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fruitstore.entities.ProductItem;
import com.fruitstore.entities.ProductOrder;

//import com.asaitec.exam.shop.model.Offer;
//import com.asaitec.exam.shop.model.ProductBill;

@Service
public class FruitShopServiceImpl implements FruitShopService {

	
	
	private Logger logger = LoggerFactory.getLogger(FruitShopServiceImpl.class);
	
	@Override
	public void calculateBill(Map<String, String> prodsPriceRaw, Map<String, String> prodsQuantityRaw) {
		// TODO Auto-generated method stub
		
		logger.info("call calculateBill() ");


		List<ProductItem> items = getOrder(prodsPriceRaw, prodsQuantityRaw);
		
		ProductOrder order = new ProductOrder();
		order.setItems(items);
		
		logger.info("Order ===> " + order.toString());

	
	
	
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
