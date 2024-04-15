package hinteregger.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hinteregger.pageobjects.OrdersPage;
import hinteregger.pageobjects.ShoppingCart;

public class AbstractComponent {

    private WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul//li[4]//button")
    WebElement shoppingCartButton;

    @FindBy(xpath = "//ul//li[3]//button")
    WebElement ordersButton;

    public ShoppingCart goToCartPage(){
        shoppingCartButton.click();
        return new ShoppingCart(driver);
    }
    
    public OrdersPage goToOrdersPage(){
        ordersButton.click();
        return new OrdersPage(driver);
    }
    
    public void waitForElementToAppearBy(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    
    public void waitForElementToDisappearBy(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementsToAppear(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementsToDisappear(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }
}   
