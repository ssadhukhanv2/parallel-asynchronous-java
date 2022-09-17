package com.ninja.parallelism.service;

import com.ninja.parallelism.domain.ReviewSummary;

/**
 * @author Subhrajit Sadhukhan
 */
public class ReviewSummaryServiceRunnable implements Runnable {
    private ReviewSummaryService reviewSummaryService;
    private String productId;
    private ReviewSummary reviewSummary;

    public ReviewSummaryServiceRunnable(ReviewSummaryService reviewSummaryService, String productId) {
        this.reviewSummaryService = reviewSummaryService;
        this.productId = productId;
    }

    public ReviewSummary getReviewSummary() {
        return this.reviewSummary;
    }

    @Override
    public void run() {
        this.reviewSummary = reviewSummaryService.getReviewSummaryByProductId(productId);
    }
}
