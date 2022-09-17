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
public class ProductOption {
    private Integer productOptionId;
    private String size;
    private String color;
    private double price;
    private Inventory inventory;
}
