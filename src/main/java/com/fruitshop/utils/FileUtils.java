package com.fruitshop.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fruitshop.controllers.FruitShopController;

public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FruitShopController.class);

    public static Map<String,String> parseToLines(MultipartFile file) throws Exception{
        

    	Map<String,String> result = new HashMap<String, String>();
        
    	String line;
    	String[] data;
    	
    	InputStream is = file.getInputStream();
        
    	BufferedReader br = new BufferedReader(new InputStreamReader(is));

    	br.readLine();
    
    	while ((line = br.readLine()) != null) {
    		logger.info(line);
    		data = line.split(",");
    		result.put(data[0],data[1]);
        }
    	
     
        return result;

    }
    
}
