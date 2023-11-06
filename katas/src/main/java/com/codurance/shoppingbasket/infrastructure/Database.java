package com.codurance.shoppingbasket.infrastructure;

import com.codurance.shoppingbasket.ProductID;
import com.codurance.shoppingbasket.ProductOrderItem;
import com.codurance.shoppingbasket.ShoppingBasket;
import com.codurance.shoppingbasket.UserID;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Database {

    public static final String ID = "id";

    public static final String PRODUCT = "product";
    public static final String NAME = "name";
    public static final String PRICE = "price";

    public static final String SHOPPING_BASKET = "shopping_basket";
    public static final String OWNER_ID = "owner_id";
    public static final String CREATED_AT = "created_at";

    public static final String PRODUCT_ORDER = "product_order";
    public static final String BASKET_ID = "basket_id";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_QUANTITY = "item_quantity";

    private int nextObjectId = 1;

    private final Map<String, List<Map<String, String>>> records = new HashMap<>() {{
        put(PRODUCT, new ArrayList<>());
        put(SHOPPING_BASKET, new ArrayList<>());
        put(PRODUCT_ORDER, new ArrayList<>());
    }};

    private List<Map<String, String>> findBy(String objectType, String field, String value) {
        return records
                .get(objectType)
                .stream()
                .filter(element -> element.get(field).equals(value))
                .toList();
    }

    public ProductID findProductIdBy(String productName) {
        Map<String, String> product = findBy(Database.PRODUCT, Database.NAME, productName).iterator().next();
        return new ProductID(product.get(Database.ID));
    }

    public void createProduct(String id, String name, Double price) {
        insert(
                Database.PRODUCT,
                Map.of(
                        Database.ID, id,
                        Database.NAME, name,
                        Database.PRICE, String.valueOf(price)
                )
        );
    }

    public int insert(String objectType, Map<String, String> assignments) {
        if (!assignments.containsKey(ID)) {
            assignments.put(ID, Integer.toString(nextObjectId++));
        }

        records.get(objectType).add(assignments);

        return Integer.parseInt(assignments.get(ID));
    }

    public ShoppingBasket findShoppingBasketFor(UserID userId) {
        return findBy(SHOPPING_BASKET, OWNER_ID, userId.value())
                .stream()
                .findFirst()
                .map(this::toShoppingBasket)
                .orElse(null);
    }

    private ShoppingBasket toShoppingBasket(Map<String, String> shoppingBasket) {
        String id = shoppingBasket.get(Database.ID);
        UserID userId = new UserID(shoppingBasket.get(Database.OWNER_ID));
        LocalDate createdAt = LocalDate.parse(shoppingBasket.get(Database.CREATED_AT));
        return new ShoppingBasket(id, userId, createdAt, findProductOrderItemsFor(shoppingBasket));
    }

    private List<ProductOrderItem> findProductOrderItemsFor(Map<String, String> shoppingBasket) {
        return findBy(Database.PRODUCT_ORDER, Database.BASKET_ID, shoppingBasket.get(Database.ID))
                .stream()
                .map(this::toProductOrderItem)
                .collect(Collectors.toList());
    }

    private ProductOrderItem toProductOrderItem(Map<String, String> productOrderItem) {
        String productId = productOrderItem.get(Database.ITEM_ID);
        String itemQuantity = productOrderItem.get(Database.ITEM_QUANTITY);
        Map<String, String> productMap = findBy(Database.PRODUCT, Database.ID, productId).iterator().next();
        String name = productMap.get(Database.NAME);
        double unitPrice = Double.parseDouble(productMap.get(Database.PRICE));
        return new ProductOrderItem(Integer.parseInt(itemQuantity), name, unitPrice);
    }

    public void save(ShoppingBasket shoppingBasket) {
        Map<String, String> shoppingBasketMap = new HashMap<>();
        shoppingBasketMap.put(OWNER_ID, shoppingBasket.userId().value());
        shoppingBasketMap.put(CREATED_AT, shoppingBasket.createdAt().toString());
        shoppingBasket.setBasketId(String.valueOf(insert(SHOPPING_BASKET, shoppingBasketMap)));
    }
}
