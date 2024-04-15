package hinteregger.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hinteregger.AbstractComponents.AbstractComponent;

public class ShoppingCart extends AbstractComponent{
    
    private WebDriver driver;

    public ShoppingCart(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart ul h3")
    private List <WebElement> products;

    private By checkoutButton = By.xpath("//div[contains(@class, 'subtotal')]//button");
    private By paymentForm = By.cssSelector(".payment");

    public Boolean checkForElement(String productName){
        return products.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }

    public PaymentForm checkout(){
        driver.findElement(checkoutButton).click();
        waitForElementToAppearBy(paymentForm);
        return new PaymentForm(driver);
    }

        
}
