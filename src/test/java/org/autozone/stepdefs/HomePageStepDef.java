package org.autozone.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.autozone.factory.DriverFactory;
import org.autozone.pages.HomePage;
import org.testng.Assert;

public class HomePageStepDef {
    private final HomePage homePage = new HomePage(DriverFactory.getDriver());

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
    public void i_select_as_year(String year) {
        homePage.selectYear(year);
    }
    @When("I select {string} as make")
    public void i_select_as_make(String make) {
        homePage.selectMake(make);
    }
    @When("I select {string} as model")
    public void i_select_as_model(String model) {
        homePage.selectModel(model);
    }
    @Then("validate {string} is selected as vehicle")
    public void validate_is_selected_as_vehicle(String string) {
        System.out.println("TODO:");
    }
    @When("I search for {string} and navigate to product page")
    public void i_search_for_and_navigate_to_product_page(String string) {
        System.out.println("TODO:");
    }
    @Then("I add the product to cart and navigate to cart page")
    public void i_add_the_product_to_cart_and_navigate_to_cart_page() {
        System.out.println("TODO:");
    }
    @Then("I navigate to checkout page")
    public void i_navigate_to_checkout_page() {
        System.out.println("TODO:");
    }
    @Then("I continue as guest")
    public void i_continue_as_guest() {
        System.out.println("TODO:");
    }
    @Then("I continue with delivery option")
    public void i_continue_with_delivery_option() {
        System.out.println("TODO:");
    }
    @When("I add the Payment Information")
    public void i_add_the_payment_information(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println("TODO:");
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
