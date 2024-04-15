package hinteregger.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hinteregger.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="login")
    private WebElement loginButton;

    @FindBy(css="[class*='toast-message']")
    private WebElement errorMessage;

    public ProductCatalog login(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalog(driver);
    }  

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
