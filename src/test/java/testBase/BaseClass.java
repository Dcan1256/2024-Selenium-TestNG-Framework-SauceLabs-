package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager; // for log4j logger
import org.apache.logging.log4j.Logger; // for log4j logger
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {

    public static WebDriver driver;
    public static Logger logger;
    ResourceBundle config;
    @BeforeMethod(groups = {"Regression", "Sanity", "Master"})
    @Parameters({"os","browser"})
    public void setUp(String os, String br) throws MalformedURLException {
        //loading log4j2 file
        logger=LogManager.getLogger(this.getClass());
        //loading properties file
        config=ResourceBundle.getBundle("config");

        // Selenium Grid Execution !!!
        if (config.getString("execution_env").equalsIgnoreCase("remote")) {
            try {
                if (os.equalsIgnoreCase("windows")) {
                    if (br.equalsIgnoreCase("chrome")) {
                        ChromeOptions options = new ChromeOptions();
                        options.setCapability("platformName", "Windows");
                        driver = new RemoteWebDriver(new URL("http://172.20.10.11:4444"), options);
                    } else if (br.equalsIgnoreCase("edge")) {
                        EdgeOptions options = new EdgeOptions();
                        options.setCapability("platformName", "Windows");
                        driver = new RemoteWebDriver(new URL("http://172.20.10.11:4444"), options);
                    }
                } else if (os.equalsIgnoreCase("mac")) {
                    // Assuming Chrome for macOS as an example
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("platformName", "macOS");
                    driver = new RemoteWebDriver(new URL("http://172.20.10.11:4444"), options);
                } else {
                    System.out.println("No matching OS configuration.");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }







        // Local Execution !!!
        else if (config.getString("execution_env").equalsIgnoreCase("local"))
        {
            // launching browser based on condition - locally
            switch (br.toLowerCase()){
                case "chrome":
                    driver=new ChromeDriver();
                    break;
                case "edge" :
                    driver=new EdgeDriver();
                    break;
                case "firefox" :
                    driver=new FirefoxDriver();
                    break;
                default:
                    System.out.println("No matching browser... ");
                    break;
            }
        }




        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(config.getString("AppURL"));
        driver.manage().window().maximize();
    }

    @AfterMethod(groups = {"Regression", "Sanity", "Master"})
    public void tearDown(){
        driver.close();
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}
