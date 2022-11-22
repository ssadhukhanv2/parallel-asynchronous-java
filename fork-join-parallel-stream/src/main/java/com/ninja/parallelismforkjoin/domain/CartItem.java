package com.ninja.parallelismforkjoin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Subhrajit Sadhukhan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer itemId;
    private String name;
    private double price;
    private Integer quantity;
    private boolean expired;
}
