package hinteregger.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import hinteregger.TestComponents.BaseTest;
import hinteregger.pageobjects.ConfirmationPage;
import hinteregger.pageobjects.LandingPage;
import hinteregger.pageobjects.PaymentForm;
import hinteregger.pageobjects.ProductCatalog;
import hinteregger.pageobjects.ShoppingCart;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{

    LandingPage landingPage;
    ProductCatalog productCatalog;
    ShoppingCart shoppingCart;
    PaymentForm paymentForm;
    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_page() throws IOException{
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        productCatalog = landingPage.login(username, password);
    }

    @When("^I add product (.+) from Cart$")
    public void I_add_product_from_Cart(String product){
        productCatalog.addProductToCart(product);
    }

    @And("^Checkout (.+) and choose (.+) and submit the Order$")
    public void Checkout_and_choose_country_and_submit_the_Order(String product, String country){
        shoppingCart = productCatalog.goToCartPage();
        Assert.assertTrue(shoppingCart.checkForElement(product));
        paymentForm = shoppingCart.checkout();
        paymentForm.selectCountry(country);
        confirmationPage = paymentForm.checkout();
    }

    @Then("{string} message is diyplayed on Confirmation Page")
    public void Confirmation_message_is_displayed_on_Confirmation_Page(String message){
        Assert.assertEquals(confirmationPage.getConfirmationMessage(), message);
        close();
    }

    @Then("{string} error message is displayed")
    public void Incorrect_email_or_password_error_message_is_displayed(String message) {
        Assert.assertEquals(landingPage.getErrorMessage(), message);
        close();
    }

}
