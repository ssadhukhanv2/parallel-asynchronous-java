package com.ninja.parallelism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Subhrajit Sadhukhan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSummary {
    private int numberOfReviews;
    private double avgRating;
}
