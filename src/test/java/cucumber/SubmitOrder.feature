Feature: Purchase Order for Ecommerce Website

    Background: 
    Given I landed on Ecommerce Page

    @Regression
    Scenario Outline: Positive test of Submitting the Order
        Given Logged in with username <username> and password <password>
        When I add product <productName> from Cart
        And Checkout <productName> and choose <country> and submit the Order
        Then "THANKYOU FOR THE ORDER." message is diyplayed on Confirmation Page 

        Examples:
         | username                 | password        | productName     | country |
         | mistermonopoly@email.com | vbTf_.T6j-aKuAc | ADIDAS ORIGINAL | Austria |
         | whizardofozz@mail.com    | 123456789-X?y   | ZARA COAT 3     | India   |