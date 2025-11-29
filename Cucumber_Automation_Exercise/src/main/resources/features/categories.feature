@Categories
Feature: Product Categories
  As a user
  I want to browse products by category
  So that I can find specific types of products

  @Critical
  Scenario: Browse women's dress category
    Given I am on the home page
    Then category section should be visible
    When I scroll to category section
    And I click on "Women" category
    And I click on "Dress" subcategory
    Then page title should contain "Automation Exercise - Dress Products"
    And category title should be "WOMEN - DRESS PRODUCTS"

  @Critical
  Scenario: Browse men's tshirts category
    Given I am on the home page
    When I scroll to category section
    And I click on "Women" category
    And I click on "Dress" subcategory
    Then category products should be displayed
    When I click on "Men" category
    And I click on "Tshirts" subcategory
    Then page title should contain "Automation Exercise - Tshirts Products"