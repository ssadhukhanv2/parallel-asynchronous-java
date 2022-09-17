package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.ReviewSummary;

import java.util.HashMap;
import java.util.Map;

import static com.ninja.parallelism.util.CommonUtil.delay;

/**
 * @author Subhrajit Sadhukhan
 */
public class ReviewSummaryService {
    private static Map<String, ReviewSummary> productIdReviewMapping = new HashMap<>();

    static {
        productIdReviewMapping.put("1", new ReviewSummary(34, 4.8));
        productIdReviewMapping.put("2", new ReviewSummary(54, 3.9));
    }

    public ReviewSummary getReviewSummaryByProductId(String productId) {
        delay(1000);
        return productIdReviewMapping.get(productId);
    }
}
