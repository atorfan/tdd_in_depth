package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

public class ShoppingBasket {

    private String basketId;
    private final UserID userId;
    private final LocalDate createdAt;
    private List<ProductOrderItem> productOrderItems;

    public ShoppingBasket(UserID userId, LocalDate createdAt) {
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public ShoppingBasket(String basketId, UserID userId, LocalDate createdAt, List<ProductOrderItem> productOrderItems) {
        this(userId, createdAt);
        this.basketId = basketId;
        this.productOrderItems = productOrderItems;
    }

    public String productOrderItemsToString() {
        StringBuilder shoppingBasketItems = new StringBuilder();
        for (int i = 0; i < productOrderItems.size(); i++) {
            ProductOrderItem item = productOrderItems.get(i);
            if (i != 0) {
                shoppingBasketItems.append("\n");
            }
            shoppingBasketItems.append(
                    MessageFormat.format("- {0} x {1} // {0} x {2,number,#0.00} = £{3,number,#0.00}",
                    item.quantity(), item.name(), item.unitPrice(), item.unitPrice() * item.quantity())
            );
        }
        return shoppingBasketItems.toString();
    }

    public String getBasketId() {
        return basketId;
    }

    public String totalAmountToString() {
        BigDecimal totalAmount = productOrderItems
                .stream()
                .map(ProductOrderItem::totalAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return MessageFormat.format("- Total: £{0,number,#0.00}", totalAmount);
    }

    public String createdAtToString() {
        return MessageFormat.format("- Creation date of the shopping basket: {0}", createdAt.toString());
    }

    public UserID userId() {
        return userId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public LocalDate createdAt() {
        return createdAt;
    }
}
