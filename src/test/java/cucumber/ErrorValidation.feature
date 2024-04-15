Feature: Error Validation

    @ErrorValidation
    Scenario Outline: Positive test of Submitting the Order
        Given I landed on Ecommerce Page
        And Logged in with username <username> and password <password>
        Then  "Incorrect email or password." error message is displayed

        Examples:
         | username                 | password        |
         | mistermonopoly@email.com | wrongpassword   |
         | whizardofozz@mail.com    | wrongpassword   |

         