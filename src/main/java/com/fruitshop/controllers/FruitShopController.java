package com.fruitshop.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fruitshop.entities.ProductItem;
import com.fruitshop.entities.ProductOrder;
import com.fruitshop.entities.PromotionItem;
import com.fruitshop.services.FruitShopService;
import com.fruitshop.utils.FileUtils;

@Controller
@RequestMapping("/fruit")
public class FruitShopController {

	
	private Logger logger = LoggerFactory.getLogger(FruitShopController.class);

	@Autowired
	FruitShopService fruitShopService;	
	
	@GetMapping("/")
	public String index(Model model) throws Exception {
		logger.info("call index()");
		
		return "index";
	
	}
	
	@PostMapping("/calculate_bill")
	public String calculateBill(Model model , @RequestParam("prod_price_file") MultipartFile fileProdPrice,
			@RequestParam("prod_quan_file") MultipartFile fileProdQuantity) throws Exception{
		
		
		logger.info("call store ");
		
		Map<String,String> prodsPriceRaw =  null;
		
		if(fileProdPrice != null && !fileProdPrice.isEmpty()) {

			prodsPriceRaw = FileUtils.parseToLines(fileProdPrice);
			
			logger.info(prodsPriceRaw.toString());
			
			logger.info("fileProdPrice ok");
			
		}
		
		Map<String,String> prodsQuantityRaw =  null;
		
		if(fileProdQuantity != null && !fileProdQuantity.isEmpty()) {
			
			prodsQuantityRaw = FileUtils.parseToLines(fileProdQuantity);
			
			logger.info(prodsQuantityRaw.toString());
			
			logger.info(" fileProdQuantity ok");
			
		}
		
		ProductOrder order = fruitShopService.calculateBill(prodsPriceRaw,prodsQuantityRaw);

		logger.info(" ----------- THE ORDER ----------------");

		logger.info("<<<<< ITEMS >>>>>");

		for (ProductItem prod : order.getItems()) {
			logger.info("Product=" + prod.getName() +
						"; Quantity = " + prod.getQuantity() + 
						"; Unit Price = " + prod.getPrice() + " Euros"+ 
						"; Total Price = " + prod.getTotalPrice() + " Euros") ;
		}

		logger.info("<<<<< PRICE LIST >>>>>");

		logger.info("Price List " + order.getPriceList() + " Euros");

		logger.info("<<<<< DISCOUNT >>>>>");
		
		for (PromotionItem promoItem : order.getPromotionsApply()) {
			logger.info("For '" + promoItem.getProductName() + "' there are " + promoItem.getDiscount() + " Euros") ;
		}
		
		logger.info("<<<<< PRICE WITH DISCOUNT >>>>>");

		logger.info("Price with Discount " + order.getPriceFinal() + " Euros");
		
		model.addAttribute("order", order);
		
		return "order";
	}
	
}
