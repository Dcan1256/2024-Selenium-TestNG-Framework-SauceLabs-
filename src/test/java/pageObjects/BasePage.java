package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    /**
     * We will need a constructor for each Page-Objects, and we will Page Factory model
     * to eliminate unnecessary code duplication with @FindBy annotation, so we will create the
     * constructor here and then extend this to the all of ou Page-Object
     */
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
