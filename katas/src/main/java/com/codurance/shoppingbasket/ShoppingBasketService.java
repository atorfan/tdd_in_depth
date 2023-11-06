package com.codurance.shoppingbasket;

import com.codurance.shoppingbasket.infrastructure.Database;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;

public class ShoppingBasketService {

    private final Database database;
    private final Clock clock;

    public ShoppingBasketService(Database database, Clock clock) {
        this.database = database;
        this.clock = clock;
    }

    public void addItem(UserID userId, ProductID productId, int quantity) {
        database.insert(Database.PRODUCT_ORDER, new HashMap<>() {{
            put(Database.BASKET_ID, findOrCreateShoppingBasketFor(userId).getBasketId());
            put(Database.ITEM_ID, productId.value());
            put(Database.ITEM_QUANTITY, String.valueOf(quantity));
        }});
    }

    private ShoppingBasket findOrCreateShoppingBasketFor(UserID userId) {
        ShoppingBasket shoppingBasket = basketFor(userId);
        if (shoppingBasket == null) {
            shoppingBasket = new ShoppingBasket(userId, LocalDate.ofInstant(clock.instant(), clock.getZone()));
            database.save(shoppingBasket);
        }
        return shoppingBasket;
    }

    public ShoppingBasket basketFor(UserID userId) {
        return database.findShoppingBasketFor(userId);
    }
}
