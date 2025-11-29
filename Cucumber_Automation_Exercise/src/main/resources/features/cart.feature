@Cart
Feature: Shopping Cart Management
  As a user
  I want to manage my shopping cart
  So that I can purchase items

  Background:
    Given I am on the home page

  @Critical @AddToCart
  Scenario: Add multiple products to cart
    When I navigate to products page
    And I scroll to view products
    And I add first product to cart
    And I click continue shopping
    And I add second product to cart
    And I view cart
    Then cart page should be displayed
    And cart should contain 2 products
    And product prices and totals should be correct

  @Normal @Quantity
  Scenario: Verify product quantity in cart
    When I click view first product
    And I set product quantity to "4"
    And I click add to cart button
    And I view cart
    Then product quantity should be "4"

  @Critical @RemoveFromCart
  Scenario: Remove product from cart
    When I scroll to view products
    And I add first product to cart
    And I view cart
    Then cart should contain 1 products
    When I remove first product from cart
    Then cart should be empty

  @Recommended
  Scenario: Add recommended product to cart
    When I scroll to recommended items
    Then "RECOMMENDED ITEMS" text should be visible
    When I add recommended product to cart
    And I view cart
    Then cart should contain the recommended product