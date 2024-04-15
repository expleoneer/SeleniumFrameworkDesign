package hinteregger.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hinteregger.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{

    private WebDriver driver;

    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'mb-3')]")
    private List <WebElement> products;
    
    private By productsBy = By.xpath("//div[contains(@class, 'mb-3')]");
    private By addToCart = By.xpath(".//div[@class='card-body']/button[last()]");
    private By toastContainer = By.id("toast-container");
    private By spinner = By.tagName("ngx-spinner");

    public List<WebElement> getProductList(){
        waitForElementToAppearBy(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        return getProductList()
            .stream()
            .filter(p -> p.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
            .findFirst()
            .orElse(null);
    }

    public void addProductToCart(String name){
        WebElement productName = getProductByName(name);
        productName.findElement(addToCart).click();
        waitForElementToAppearBy(toastContainer);
        waitForElementToDisappearBy(spinner);
    }
}
