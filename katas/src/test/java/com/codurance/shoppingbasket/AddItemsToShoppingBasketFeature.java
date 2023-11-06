package com.codurance.shoppingbasket;

import com.codurance.shoppingbasket.infrastructure.Database;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.MessageFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddItemsToShoppingBasketFeature {

    private UserID userId;
    private ShoppingBasketService shoppingBasketService;
    private Database database;
    private ShoppingBasket shoppingBasket;
    private Instant firstTimeAddingUnitsToShoppingBasket;

    @Before
    public void setUp() {
        initDatabase();
        Clock clock = FutureClock.instance(() -> firstTimeAddingUnitsToShoppingBasket);
        shoppingBasketService = new ShoppingBasketService(database, clock);
        userId = new UserID("USER_ID");
    }

    private void initDatabase() {
        database = new Database();
        insertProducts();
    }

    private void insertProducts() {
        database.createProduct("10001", "Lord of the Rings", 10.00);
        database.createProduct("10002", "The Hobbit", 5.00);
        database.createProduct("20001", "Game of Thrones", 9.00);
        database.createProduct("20110", "Breaking Bad", 7.00);
    }

    @Given("I add {int} units of {string} to my shopping basket")
    public void i_add_units_of_to_my_shopping_basket(int qty, String bookName) {
        firstTimeAddingUnitsToShoppingBasket = Instant.now().minus(1, ChronoUnit.MINUTES);
        shoppingBasketService.addItem(userId, database.findProductIdBy(bookName), qty);
    }

    @And("I add {int} units of {string}")
    public void i_add_units_of(int qty, String bookName) {
        shoppingBasketService.addItem(userId, database.findProductIdBy(bookName), qty);
    }

    @When("I check the content of my shopping basket")
    public void i_check_the_content_of_my_shopping_basket() {
        shoppingBasket = shoppingBasketService.basketFor(userId);
    }

    @Then("^it should contain the following information:")
    public void it_should_contain_the_following_information(String expectedOutput) {
        String actualOutput = MessageFormat.format(
                """
                        {0}
                        {1}
                        {2}
                        """,
                shoppingBasket.createdAtToString(),
                shoppingBasket.productOrderItemsToString(),
                shoppingBasket.totalAmountToString()
        );
        assertEquals(
                expectedOutput.replaceFirst("YYYY-MM-DD", LocalDate.ofInstant(firstTimeAddingUnitsToShoppingBasket, ZoneId.systemDefault()).toString()),
                actualOutput
        );
    }
}
