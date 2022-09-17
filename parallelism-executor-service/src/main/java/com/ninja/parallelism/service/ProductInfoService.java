package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.Inventory;
import com.ninja.parallelism.domain.ProductInfo;
import com.ninja.parallelism.domain.ProductOption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ninja.parallelism.util.CommonUtil.delay;


/**
 * @author Subhrajit Sadhukhan
 */
public class ProductInfoService {
    private static Map<String, ProductInfo> productIdProductInfoMapping = new HashMap<>();

    static {
        productIdProductInfoMapping
                .put("1", new ProductInfo("1", "ipad",
                        List.of(new ProductOption(101, "8 inches", "red", 75000, new Inventory(40)),
                                new ProductOption(102, "10.2 inches", "blue", 95000, new Inventory(20)))));
        productIdProductInfoMapping
                .put("2", new ProductInfo("2", "macbook",
                        List.of(new ProductOption(201, "4.7 inches", "blue", 69000, new Inventory(90)),
                                new ProductOption(202, "6 inches", "green", 75000, new Inventory(50)),
                                new ProductOption(203, "6.5 inches", "black", 125000, new Inventory(30)))));
    }

    public ProductInfo getProductInfoByProductId(String productId) {
        delay(1000);
        return productIdProductInfoMapping.get(productId);
    }

}
