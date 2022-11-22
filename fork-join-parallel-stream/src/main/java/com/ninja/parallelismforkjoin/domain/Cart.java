package com.ninja.parallelismforkjoin.domain;

import com.ninja.parallelismforkjoin.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Subhrajit Sadhukhan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer cartId;
    private List<CartItem> cartItemList;
}
