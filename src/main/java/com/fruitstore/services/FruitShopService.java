package com.fruitstore.services;

import java.util.Map;

import com.fruitstore.entities.ProductOrder;

public interface FruitShopService {

	ProductOrder calculateBill(Map<String, String> prodsPriceRaw, Map<String, String> prodsQuantityRaw);

}
