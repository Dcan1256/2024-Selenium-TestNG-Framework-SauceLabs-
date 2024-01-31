package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

public class LoginPage extends BasePage {

    WebDriver driver;

    /**
     * We are invoking the BasePage Constructor here for our current Page-Object
     * @param driver
     */
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //Elements

    @FindBy(xpath = "//*[@id=\"user-name\"]")
    public WebElement usernameBox;

    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement pwdBox;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    public WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"login_credentials\"]/h4")
    public WebElement acceptableUserNames;

    //Action Methods
    public void login(){
        ResourceBundle config=ResourceBundle.getBundle("config");
        usernameBox.sendKeys(config.getString("username"));
        pwdBox.sendKeys(config.getString("pwd"));
        loginBtn.click();
    }

    public void loginDDT(String username, String pwd){
        usernameBox.sendKeys(username);
        pwdBox.sendKeys(pwd);
        loginBtn.click();
    }
}
