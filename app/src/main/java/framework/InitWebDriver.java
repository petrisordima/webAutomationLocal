package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.TestParams;
import util.Util;

import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * The main. Where all test operations are initialized and
 * prepares the test execution, loading its parameters
 *
 * @author pdima
 */
public class InitWebDriver {

    public static WebDriver driver;

    @BeforeSuite
    public static void openDriver() throws Exception {


        TestParams.readParams();
        System.setProperty("webdriver.gecko.driver", "app/resources/geckodriver.exe");
        Util.Log.info("Start Driver");
        System.out.println("  [INFO] - browser set in testRunner.propreties = " + TestParams.getBrowser());
        System.out.println("  [INFO] - wait betwen operation set in testRunner.propreties = " + TestParams.getWait());
        if (TestParams.getSeleniumUrl() != null) {
            driver = new RemoteWebDriver(new URL(TestParams.getSeleniumUrl()), DesiredCapabilities.firefox());
        } else {
            if (TestParams.getBrowser().equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "D:\\kits\\ChromeDriver\\chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                if (TestParams.getBrowser().equals("firefox")) {
                    driver = new FirefoxDriver();
                } else {
                    if (TestParams.getBrowser().equals("ie")) {
                        //System.setProperty("webdriver.ie.driver", "D:\\kits\\ChromeDriver\\iedriver.exe");
                        driver = new InternetExplorerDriver();
                    } else
                        driver = new FirefoxDriver();
                    System.out.println(" [CONFIGURATION ERROR] - Browser misspelled. Check browser parameter in unitbv.proprieties. Firefox set as default");
                    Util.Log.error("[CONFIGURATION ERROR] - Browser misspelled. Check browser parameter in unitbv.proprieties. Firefox set as default");
                }

            }
        }

        driver.manage().timeouts().implicitlyWait(TestParams.getTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();

        String mainWindow = driver.getWindowHandle();


    }

    @AfterSuite
    public void closeDriver() {

        Util.Log.info("Stop Driver");
        driver.close();
    }

}
