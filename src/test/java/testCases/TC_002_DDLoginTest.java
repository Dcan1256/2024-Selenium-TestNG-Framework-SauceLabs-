package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import testBase.BaseClass;
import utilities.DataProviders;


import static testBase.BaseClass.driver;
import static testBase.BaseClass.logger;

public class TC_002_DDLoginTest extends BaseClass {


    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class, groups = {"Regression", "Sanity"})
    public void LoginDDT(String username, String password, String res){
        logger.info("*** TC_002_DDLoginTest Starting --> Trying to logging in with correct credentials ***");

            logger.debug("application logs....   ");
            LoginPage lp=new LoginPage(driver);
            logger.info("filling the username and password then clicking to the login button");
            lp.usernameBox.sendKeys(username);
            lp.pwdBox.sendKeys(password);
            lp.loginBtn.click();
            logger.info("checking login status");
            MainPage mp=new MainPage(driver);

            if(res.equals("Valid")){
                logger.info("Valid Data logged in");
                Assert.assertEquals(mp.productsHdr.getText(),"Products");
            }
            else if(res.equals("Invalid")){
                logger.info("invalid data not logged in ");
                Assert.assertTrue(lp.loginBtn.isDisplayed());
            }


        logger.info("*** TC_002_DDLoginTest Finished !!! ***");
    }
}
