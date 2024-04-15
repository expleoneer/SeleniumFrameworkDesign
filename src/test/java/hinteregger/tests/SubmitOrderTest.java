package hinteregger.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hinteregger.TestComponents.BaseTest;
import hinteregger.pageobjects.ConfirmationPage;
import hinteregger.pageobjects.OrdersPage;
import hinteregger.pageobjects.PaymentForm;
import hinteregger.pageobjects.ProductCatalog;
import hinteregger.pageobjects.ShoppingCart;

public class SubmitOrderTest extends BaseTest{

    String confirmationMessage = "THANKYOU FOR THE ORDER."; 

    @Test(dataProvider = "GetData", groups = {"Purchase"})
    public void SubmittingOrderTest(HashMap<String, String> input){

        ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));
        productCatalog.addProductToCart(input.get("product"));
        
        ShoppingCart shoppingCart = productCatalog.goToCartPage();
        Assert.assertTrue(shoppingCart.checkForElement(input.get("product")));

        PaymentForm paymentForm = shoppingCart.checkout();
        paymentForm.selectCountry(input.get("country"));
        
        ConfirmationPage confirmationPage = paymentForm.checkout();
        Assert.assertEquals(confirmationPage.getConfirmationMessage(), confirmationMessage);
    }

    @Test(dependsOnMethods = {"SubmittingOrderTest"}, dataProvider = "GetData")
    public void OrderHistoryTest(HashMap<String, String> input){
        ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));

        OrdersPage ordersPage = productCatalog.goToOrdersPage();
        Assert.assertTrue(ordersPage.checkForElement(input.get("product")));
    }

    @DataProvider
    public Object[][] GetData() throws IOException{
        List<HashMap<String, String>> data = getJSONDataToHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\hinteregger\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
