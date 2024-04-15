package hinteregger.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import hinteregger.TestComponents.BaseTest;
import hinteregger.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
    String productName = "ADIDAS ORIGINAL";

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException {
        String errorMessage = "Incorrect email or password.";

        landingPage.login("wrongemail@email.com", "vbTf_.T6j-aKuAc");
        String message = landingPage.getErrorMessage();
        Assert.assertEquals(message, errorMessage);
    }
}
