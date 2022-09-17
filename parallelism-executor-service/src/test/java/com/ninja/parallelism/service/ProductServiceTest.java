package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Subhrajit Sadhukhan
 */
class ProductServiceTest {

    @Test
    @DisplayName("Sequential calls of services takes more than 2 secs")
    void testGetProductById_Sequential() {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewSummaryService reviewSummaryService = new ReviewSummaryService();
        ProductService productService = new ProductService(productInfoService, reviewSummaryService);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Product product = productService.getProductById_Sequential("2");
        stopWatch.stop();
        Double timeTaken = stopWatch.getTotalTimeSeconds();

        Assertions.assertEquals("macbook", product.getProductInfo().getProductName());
        org.assertj.core.api.Assertions.assertThat(timeTaken).isGreaterThan(2.0);
    }

    @Test
    @DisplayName("Parallel calls of services using Threads takes a little more than 1 sec")
    void testGetProductById_Parallel_With_Threads() {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewSummaryService reviewSummaryService = new ReviewSummaryService();
        ProductService productService = new ProductService(productInfoService, reviewSummaryService);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Product product = productService.getProductById_Parallel_With_Threads("2");
        stopWatch.stop();
        Double timeTaken = stopWatch.getTotalTimeSeconds();

        Assertions.assertEquals("macbook", product.getProductInfo().getProductName());
        org.assertj.core.api.Assertions.assertThat(timeTaken).isLessThan(1.1);
    }


    @Test
    @DisplayName("Parallel calls of services using Executor Service takes a little more than 1 sec")
    void testGetProductById_Parallel_With_ExecutorService() throws ExecutionException, InterruptedException, TimeoutException {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewSummaryService reviewSummaryService = new ReviewSummaryService();
        ProductService productService = new ProductService(productInfoService, reviewSummaryService);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Product product = productService.getProductById_Parallel_With_ExecutorService("2");
        stopWatch.stop();
        Double timeTaken = stopWatch.getTotalTimeSeconds();

        Assertions.assertEquals("macbook", product.getProductInfo().getProductName());
        org.assertj.core.api.Assertions.assertThat(timeTaken).isLessThan(1.2);
    }
}