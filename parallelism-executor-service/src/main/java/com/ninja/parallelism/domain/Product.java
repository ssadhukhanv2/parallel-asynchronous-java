package com.ninja.parallelism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Subhrajit Sadhukhan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {
    private String productId;
    private ProductInfo productInfo;
    private ReviewSummary reviewSummary;
}
