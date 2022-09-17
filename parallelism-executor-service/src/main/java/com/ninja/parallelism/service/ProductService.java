package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.Product;
import com.ninja.parallelism.domain.ProductInfo;
import com.ninja.parallelism.domain.ReviewSummary;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

import static com.ninja.parallelism.util.LoggerUtil.log;


/**
 * @author Subhrajit Sadhukhan
 */
public class ProductService {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ProductInfoService productInfoService;
    private ReviewSummaryService reviewSummaryService;
    private StopWatch stopWatch;

    public ProductService(ProductInfoService productInfoService, ReviewSummaryService reviewSummaryService) {
        this.productInfoService = productInfoService;
        this.reviewSummaryService = reviewSummaryService;
        this.stopWatch = new StopWatch();
    }

    public Product getProductById_Sequential(String productId) {
        // Takes more than 2 secs to execute
        stopWatch.start();
        ProductInfo productInfo = productInfoService.getProductInfoByProductId(productId);
        ReviewSummary reviewSummary = reviewSummaryService.getReviewSummaryByProductId(productId);
        stopWatch.stop();
        log("Time Taken for Sequential Calls for services: " + stopWatch.getTotalTimeMillis());
        return new Product(productId,
                productInfo,
                reviewSummary);

    }

    public Product getProductById_Parallel_With_Threads(String productId) {
        stopWatch.start();

        ProductInfoServiceRunnable productInfoServiceRunnable = new ProductInfoServiceRunnable(productInfoService, productId);
        ReviewSummaryServiceRunnable reviewSummaryServiceRunnable = new ReviewSummaryServiceRunnable(reviewSummaryService, productId);

        Thread productInfoServiceThread = new Thread(productInfoServiceRunnable);
        Thread reviewSummaryServiceThread = new Thread(reviewSummaryServiceRunnable);

        productInfoServiceThread.start();
        reviewSummaryServiceThread.start();

        try {
            productInfoServiceThread.join();
            reviewSummaryServiceThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ProductInfo productInfo = productInfoServiceRunnable.getProductInfo();
        ReviewSummary reviewSummary = reviewSummaryServiceRunnable.getReviewSummary();
        stopWatch.stop();
        log("Time Taken for Parallel Calls for services using Threads: " + stopWatch.getTotalTimeMillis());
        return new Product(productId,
                productInfo,
                reviewSummary);

    }

    public Product getProductById_Parallel_With_ExecutorService(String productId) throws ExecutionException, InterruptedException, TimeoutException {
        stopWatch.start();
        Callable<ProductInfo> productInfoCallable = () -> productInfoService.getProductInfoByProductId(productId);
        Callable<ReviewSummary> reviewSummaryCallable = () -> reviewSummaryService.getReviewSummaryByProductId(productId);
        Future<ProductInfo> productInfoFuture = executorService.submit(productInfoCallable);
        Future<ReviewSummary> reviewSummaryFuture = executorService.submit(reviewSummaryCallable);

        ProductInfo productInfo = productInfoFuture.get();
        ReviewSummary reviewSummary = reviewSummaryFuture.get(1, TimeUnit.SECONDS);

        executorService.shutdown();
        stopWatch.stop();

        log("Time Taken for Parallel Calls for services using Executor Service: " + stopWatch.getTotalTimeMillis());
        return new Product(productId, productInfo, reviewSummary);

    }


}
