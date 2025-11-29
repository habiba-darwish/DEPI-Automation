@Products
Feature: Product Management
  As a user
  I want to search and view products
  So that I can find items I'm interested in

  Background:
    Given I am on the home page
    And I verify home page is displayed

  @Smoke @Search
  Scenario Outline: Search for different products
    When I navigate to products page
    Then I should see "ALL PRODUCTS" text
    When I search for "<productName>"
    Then I should see "SEARCHED PRODUCTS" text
    And search results should contain "<productName>"

    Examples:
      | productName        |
      | Blue Top          |
      | Men Tshirt        |
      | Sleeveless Dress  |

  @Critical @ProductDetails
  Scenario: View product details
    When I navigate to products page
    And I scroll to view product section
    And I click on view first product button
    Then product details should be visible
      | name         |
      | category     |
      | price        |
      | availability |
      | condition    |
      | brand        |

  @Brands
  Scenario: View products by brand
    When I navigate to products page
    And I scroll to brands section
    Then brands section should be visible
    When I click on "POLO" brand
    Then I should see "BRAND - POLO PRODUCTS" title
    And brand products section should be visible
    When I click on "H&M" brand
    Then I should see "BRAND - H&M PRODUCTS" title