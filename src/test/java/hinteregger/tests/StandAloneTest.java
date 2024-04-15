package hinteregger.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import hinteregger.TestComponents.BaseTest;
import hinteregger.pageobjects.ConfirmationPage;
import hinteregger.pageobjects.PaymentForm;
import hinteregger.pageobjects.ProductCatalog;
import hinteregger.pageobjects.ShoppingCart;

public class StandAloneTest extends BaseTest{

    @Test
    public void OrderProductEnd2EndTest() throws IOException {
        // Demo comment
        String productName = "ADIDAS ORIGINAL";
        String country = "Austria";
        String confirmationMessage = "THANKYOU FOR THE ORDER."; 
        
        ProductCatalog productCatalog = landingPage.login("mistermonopoly@email.com", "vbTf_.T6j-aKuAc");
        productCatalog.getProductList();
        productCatalog.getProductByName(productName);
        productCatalog.addProductToCart(productName);
        productCatalog.goToCartPage();
            
        ShoppingCart shoppingCart = productCatalog.goToCartPage();
        Assert.assertTrue(shoppingCart.checkForElement(productName));

        PaymentForm paymentForm = shoppingCart.checkout();
        paymentForm.selectCountry(country);

        ConfirmationPage finalMask = paymentForm.checkout();
        Assert.assertEquals(finalMask.getConfirmationMessage(), confirmationMessage);
    }

}
