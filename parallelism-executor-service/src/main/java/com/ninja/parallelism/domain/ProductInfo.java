package com.ninja.parallelism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Subhrajit Sadhukhan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    private String productId;
    private String productName;
    private List<ProductOption> productOptionList;
}
