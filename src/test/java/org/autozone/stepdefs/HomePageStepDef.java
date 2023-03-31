package org.autozone.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.autozone.factory.DriverFactory;
import org.autozone.pages.*;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class HomePageStepDef {
    private final HomePage homePage = new HomePage(DriverFactory.getDriver());
    private final SearchPage searchPage = new SearchPage(DriverFactory.getDriver());
    private final ProductPage productPage = new ProductPage(DriverFactory.getDriver());
    private final CartPage cartPage = new CartPage(DriverFactory.getDriver());
    private final CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

    @Given("I am on the autozone home page")
    public void i_am_on_the_autozone_home_page() {
        DriverFactory.getDriver().get("https://www.autozone.com/");
        Assert.assertTrue(homePage.isHomePage());
    }

    @Given("I am on Add Vehicle modal")
    public void i_am_on_page() {
        homePage.clickOnAddVehicle();
        Assert.assertTrue(homePage.isAddVehicleModal());
    }
    @When("I select {string} as year")
    public void i_select_as_year(String year) throws InterruptedException {
        homePage.selectYear(year);
    }
    @When("I select {string} as make")
    public void i_select_as_make(String make) throws InterruptedException {
        homePage.selectMake(make);
    }
    @When("I select {string} as model")
    public void i_select_as_model(String model) throws InterruptedException {
        homePage.selectModel(model);
    }
    @Then("validate {string} is selected as vehicle")
    public void validate_is_selected_as_vehicle(String vehicle) {
        Assert.assertTrue(homePage.isVehicleSelected(vehicle));
    }
    @When("I search for {string} and navigate to product page")
    public void i_search_for_and_navigate_to_product_page(String keyword) {
        homePage.enterSearchKeyword(keyword);
        homePage.clickOnSearch();
        Assert.assertTrue(searchPage.isSearchPage());
        Assert.assertTrue(searchPage.isSearchListAvailable());
        searchPage.navigateToProduct();
        Assert.assertTrue(productPage.isProductPage());
    }
    @Then("I add the product to cart and navigate to cart page")
    public void i_add_the_product_to_cart_and_navigate_to_cart_page() {
        productPage.addToCart();
        productPage.navigateToCart();
        Assert.assertTrue(cartPage.isCartPage());
    }
    @Then("I navigate to checkout page")
    public void i_navigate_to_checkout_page() {
        cartPage.navigateToCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPage());
    }
    @Then("I continue as guest")
    public void i_continue_as_guest() {
        checkoutPage.clickOnGuestLink();
    }
    @Then("I continue with delivery option")
    public void i_continue_with_delivery_option() {
        Assert.assertTrue(checkoutPage.isDeliveryOptionEnabled());
        checkoutPage.clickOnContinueForDeliveryOption();
    }
    @When("I add the Payment Information")
    public void i_add_the_payment_information(io.cucumber.datatable.DataTable dataTable) {
        Assert.assertTrue(checkoutPage.isPaymentInformationEnabled());
        List<Map<String, String>> data = dataTable.asMaps();
        String cardNumber = data.get(0).get("cardNumber");
        String expiry = data.get(0).get("expiry");
        String cvv = data.get(0).get("cvv");
        checkoutPage.enterCardNumber(cardNumber);
        checkoutPage.enterExpiry(expiry);
        checkoutPage.enterSecurityNumber(cvv);
        checkoutPage.clickOnContinuePayment();
    }
    @When("I add the billing address")
    public void i_add_the_billing_address() {
        System.out.println("TODO:");
    }
    @When("I complete the purchase")
    public void i_complete_the_purchase() {
        System.out.println("TODO:");
    }
    @Then("validate order is successful")
    public void validate_order_is_successful() {
        System.out.println("TODO:");
    }
}
