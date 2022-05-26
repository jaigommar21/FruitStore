package com.fruitstore.controllers;

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

import com.fruitstore.entities.ProductItem;
import com.fruitstore.entities.ProductOrder;
import com.fruitstore.services.FruitShopService;
import com.fruitstore.utils.FileUtils;

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
	public String calculateBill(@RequestParam("prod_price_file") MultipartFile fileProdPrice,
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

		logger.info(" ----------- THE BILL ----------------");

		
		for (ProductItem prod : order.getItems()) {
			logger.info("Product=" + prod.getName() + "; Quantity = " + prod.getQuantity()) ;
		}

		logger.info("DISCOUNT >>>>>");
		
		for (String string : order.getPromotionApply()) {
			logger.info("" + string) ;
		}
		
		logger.info("Price List " + order.getPriceList()) ;
		logger.info("Price with Discount " + order.getPriceFinal()) ;
		
		
		
		return "bill";
	}
	
}
