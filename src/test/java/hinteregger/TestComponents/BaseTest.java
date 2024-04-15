package hinteregger.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import hinteregger.data.DataReader;
import hinteregger.pageobjects.LandingPage;

public class BaseTest extends DataReader{

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initalizeDriver() throws IOException{

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\hinteregger\\resources\\GlobalData.properties");
        properties.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        
        //properties class 
        if(browserName != null && browserName.contains("chrome")){
            //ChromeBrowser
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        }       
        
        if(browserName.contains("firefox")){
            //FireFoxDriver
            FirefoxOptions options = new FirefoxOptions();
            
            if(browserName.contains("headless")){
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        }

        if(browserName.contains("edge")){
            //EdgeDriver
            EdgeOptions options = new EdgeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException{
        driver = initalizeDriver();
        landingPage = new LandingPage(driver);   
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void close()
    {
        driver.close();
    }

    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException{
        TakesScreenshot ts = (TakesScreenshot)driver; 
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
        File dest = new File(path);
        FileUtils.copyFile(src, dest);
        return path;
    }    
}
