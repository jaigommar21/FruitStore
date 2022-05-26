package com.fruitstore.services;

import java.util.Map;

public interface FruitShopService {

	void calculateBill(Map<String, String> prodsPriceRaw, Map<String, String> prodsQuantityRaw);

}
