Feature: Checkout flow for the product on Autozone

  Background:
    Given I am on the autozone home page

  Scenario: E2E successful checkout flow for the product
    Given I am on Add Vehicle modal
    When I select "2020" as year
    And I select "Audi" as make
    And I select "A3 Premium" as model
    Then validate "2020 Audi A3 Premium 2.0L FI Turbo DOHC 4cyl" is selected as vehicle
    When I search for "Duralast 24DC-DL Group Size 24 Deep Cycle Marine and RV Battery 550 CCA" and navigate to product page
    Then I add the product to cart and navigate to cart page
    Then I navigate to checkout page
    And I continue as guest
    And I continue with delivery option
    When I add the Payment Information
      | cardNumber       | expiry | cvv |
      | 4811111111111114 | 0224   | 123 |
    And I add the billing address
    And I complete the purchase
    Then validate order is successful
