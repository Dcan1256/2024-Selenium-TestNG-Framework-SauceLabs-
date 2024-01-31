package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Web Elements
    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement productsHdr;
    //Action Methods
}
