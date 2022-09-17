package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.ProductInfo;

/**
 * @author Subhrajit Sadhukhan
 */
public class ProductInfoServiceRunnable implements Runnable {
    private ProductInfoService productInfoService;
    private ProductInfo productInfo;
    private String productId;

    public ProductInfoServiceRunnable(ProductInfoService productInfoService, String productId) {
        this.productInfoService = productInfoService;
        this.productId = productId;
    }

    @Override
    public void run() {
        this.productInfo = productInfoService.getProductInfoByProductId(productId);
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }
}

