package hinteregger.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hinteregger.AbstractComponents.AbstractComponent;

public class PaymentForm extends AbstractComponent{

    private WebDriver driver;

    public PaymentForm(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//section[contains(@class, 'ta-results')]//button")
    private List<WebElement> optionsButtons;
    
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(xpath = "//a[contains(@class, 'submit')]")
    private WebElement checkoutButton;

    @FindBy(css = ".hero-primary")
    private WebElement finalMaskBy;

    public List<WebElement> getOptionsButtons(){
        waitForElementsToAppear(optionsButtons);
        return optionsButtons;
    }

    public void selectCountry(String country){
        countryInput.sendKeys(country.substring(0, 2));
        JavascriptExecutor jsExecutor = ((JavascriptExecutor)driver);
        getOptionsButtons()
        .stream()
        .filter(p -> p.getText().equalsIgnoreCase(country))
        .findFirst()
        .ifPresent(p -> {
            jsExecutor.executeScript("arguments[0].scrollIntoView();", p);
            jsExecutor.executeScript("arguments[0].click();", p);
        });  
    }

    public ConfirmationPage checkout(){
        checkoutButton.click();
        waitForElementToAppear(finalMaskBy);
        return new ConfirmationPage(driver);
    }
}
