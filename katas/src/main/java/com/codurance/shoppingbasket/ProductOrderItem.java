package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public record ProductOrderItem(int quantity, String name, double unitPrice) {

    public BigDecimal totalAmount() {
        return BigDecimal.valueOf(quantity * unitPrice);
    }
}
