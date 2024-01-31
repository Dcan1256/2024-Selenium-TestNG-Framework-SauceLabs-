package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import testBase.BaseClass;


public class TC_001_LoginTest extends BaseClass {

    @Test(groups = {"Regression", "Sanity"})

    public void loginTest(){
        logger.info("*** TC_001_LoginTest Starting --> Trying to logging in with correct credentials ***");
        logger.debug("application logs....   ");
        LoginPage lp=new LoginPage(driver);
        logger.info("filling the username and password then clicking to the login button");
        lp.login();
        logger.info("checking login status");
        MainPage mp=new MainPage(driver);
        Assert.assertEquals(mp.productsHdr.getText(),"Products");
        if(mp.productsHdr.getText().equalsIgnoreCase("Products")){
            logger.info("Test Passed !!!");
        }else{
            logger.error("Test FAILED !!!");
        }
        logger.info("*** TC_001_LoginTest Finished !!! ***");
    }

}
