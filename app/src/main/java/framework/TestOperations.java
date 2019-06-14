package framework;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import util.TestParams;
import util.Util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Contains a collection of operations builded on Selenium Webdriver framework
 */
public class TestOperations extends InitWebDriver {

    /**
     * login method with implicit connection. It requires an encoded username
     * Administration/ Administration administration.
     */

    public static void login(String username, String password) {

        Util.Log.info("login with user : " + username);
        try {
            //login code
        } catch (Throwable t) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Could not login"));
        }
    }

    /**
     * identifies an element by the provided @xPath and clicks it.
     *
     * @param xPath - element xPath
     */

    public static void click(String xPath) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            String elementText = element.getText();
            element.click();
            Util.Log.info("Click element : " + Util.normalizeText(elementText));

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));

        }

    }

    /**
     * identifies an element by the provided @xPath and doubleClicks it.
     */

    public static void doubleClick(String xPath) {

        Actions action = new Actions(driver);

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            action.doubleClick(element).perform();
            Util.Log.info("DoubleClicked element : " + Util.normalizeText(xPath));

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));

        }

    }

    /**
     * clicks a @button element with the provided displayed text.
     *
     * @param buttonText
     */

    public static void clickButton(String buttonText) {


        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath("//button[(text()= '" + buttonText + "')]"));
            String ElementText = element.getText();
            element.click();
            Util.Log.info("Click button : " + Util.normalizeText(ElementText));

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element "
                    + Util.normalizeText("//button[(text()= '" + buttonText + "')]") + " not found on page "));
        }

    }

    /**
     * clicks an element with the provided displayed text.
     *
     * @param elementText
     */

    public static void clickElementWithText(String elementText) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + elementText + "')]"));
            String ElementText = element.getText();
            element.click();
            Util.Log.info("Click element with text : " + Util.normalizeText(ElementText));

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element "
                    + Util.normalizeText("Element with text : " + elementText + " not found on page ")));
        }

    }

    /**
     * identifies an element by the passed input field @id and sends the text@InputText.
     *
     * @param inputText
     * @param xPath
     */

    public static void sendText(String inputText, String xPath) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            element.clear();
            element.sendKeys(inputText);
            Util.Log.info("Text : " + Util.normalizeText(inputText) + " sent to : " + xPath);

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));
        }

    }

    /**
     * identifies an element by the passed input field @xPath and sends the text
     * InputText and simulates the enter key. To be used where validate button
     * is not present.
     *
     * @param inputText
     * @param xPath
     */

    public static void sendText_enter(String inputText, String xPath) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            element.clear();
            element.sendKeys(inputText + Keys.RETURN);
            Util.Log.info("Text : " + Util.normalizeText(inputText) + " sent to : " + xPath);

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));
        }

    }

    /**
     * identifies an element by His label and sends the text
     * InputText to the inpul field asociated.
     */

    public static void sendTextbyLabel(String label, String inputText) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + label + "')]//following-sibling::*//input[@type='text']"));
            element.clear();
            element.sendKeys(inputText);
            Util.Log.info("Text : " + Util.normalizeText(inputText) + " sent to : " + Util.normalizeText(label));

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element with label : " + Util.normalizeText(label) + " not found on page "));
        }

    }

    /**
     * verifies if the element passed @xPath is present and its label .getText()
     * equals the defined @Text .
     *
     * @param xPath
     * @param text
     */

    public static boolean assertTextEquals(String xPath, String text) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            String ElementText = element.getText();
            Assert.assertEquals(ElementText, text);
            Util.Log.info("Element " + Util.normalizeText(text) + " was checked on page");
            return true;

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));
            return false;
        }


    }

    /**
     * verifies if the element passed @xPath is present.
     *
     * @param xPath
     */

    public static boolean assertElementPresent(String xPath) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            String elementText = element.getText();
            Util.Log.info("Element  " + Util.normalizeText(elementText) + " was found on page");
            return true;

        } catch (Throwable T) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page"));
            return false;
        }

    }

    /**
     * checks if the element passed @xPath is present.
     */

    public static boolean checkElementPresent(String xPath) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            String elementText = element.getText();
            Util.Log.info("Element  " + Util.normalizeText(elementText) + " was checked on page");
            return true;

        } catch (Throwable T) {
            return false;
        }

    }

    /**
     * Selects a value from a dropdown list. @n_thValue = the order of the
     * desired item starting from 0
     */

    public static void dropdownSelect(String xPath, int n_thValue) {

        try {
            wait(TestParams.getWait());
            WebElement element = driver.findElement(By.xpath(xPath));
            element.click();
            element.click();
            new Select(element).selectByIndex(n_thValue);
            Util.Log.info("Value " + n_thValue + " selected fot dropdown : " + xPath);
            wait(1);

        } catch (Throwable t) {
            Util.ScreenShot();
            System.out.println(t);
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + "  not found on page "));
        }

    }


    /**
     * verifies if a browser modal pop-up is displayed (Ex Request Error 0)
     */

    public static void checkBrowserAlertAndAcceptIt() {

        if (isAlertPresent()) {
            //ModuleUtil.ScreenShot();
            acceptAlert();
        } else {
            Util.Log.info("No alert found");

        }
    }

    /**
     * Extracts a number from a string xPath.getText() (No Demand, No
     * reservation...). The method returns the number and it is set in the util
     * class DemandeNumber. Use noDemande = DemandeNumber.getNumber() to use it
     * in next test steps.
     */

    public static void saveNumber(String xPath) {

        try {
            wait(TestParams.getWait());
            String noDemande = driver.findElement(By.xpath(xPath)).getText().replaceAll("\\D+", "");
            Util.setNumber(noDemande);
            Util.Log.info("Demande number is: " + noDemande);
        } catch (NoSuchElementException e) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Element " + Util.normalizeText(xPath) + " not found on page "));
        }
    }

    /**
     * used to navigate to a specific page. It contains the verification for
     * modal pop-ups (isAlertPresent) and accepts them
     */

    public static void goToUrl(String baseUrl) {

        Util.Log.info("Page  opened");
        driver.get(baseUrl);
        wait(1);
        //checkBrowserAlertAndAcceptIt();

    }

    /**
     * switches focus on the opened pop-up .
     */

    public static void goToPopUp() {


        Util.Log.info("Switch to pop-up window");

        Set<?> windowsHandles = driver.getWindowHandles();
        Iterator<?> iterator = windowsHandles.iterator();

        if (TestParams.getMainWindow() != null) {

            while (iterator.hasNext()) {
                String popupHandle = iterator.next().toString();
                if (!popupHandle.contains(TestParams.getMainWindow())) {
                    driver.switchTo().window(popupHandle);
                    wait(TestParams.getWait());
                }

            }
        } else {

            Assert.fail(Util.Log.error("mainWindow not set, could not identunitbv pop-up"));

        }

    }

    /**
     * switches focus on a frame identified by name @framename
     */

    public static void goToFrameByName(String frameName) {

        Util.Log.info("Switching to frame : " + Util.normalizeText(frameName));
        try {
            wait(TestParams.getWait());
            driver.switchTo().frame(driver.findElement(By.name(frameName)));
        } catch (Throwable t) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error("Frame :  " + Util.normalizeText(frameName) + " not found"));
        }
    }

    /**
     * switches focus on a frame identified by xPath
     */

    public static void goToFrameByPath(String xPath) {
        Util.Log.info("Switching to frame : " + Util.normalizeText(xPath));
        try {
            wait(TestParams.getWait());
            driver.switchTo().frame(driver.findElement(By.xpath(xPath)));
        } catch (Throwable t) {
            Util.ScreenShot();
            Assert.fail(Util.Log.error(" [ERROR] Frame :  " + Util.normalizeText(xPath) + " not found"));
        }
    }

    /**
     * Obvious
     */

    public static void goToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * accepts browser alerts
     */
    public static void acceptAlert() {
        try {
            Alert Alert = driver.switchTo().alert();
            String AlertText = Alert.getText();
            Util.Log.warn("Alert found on page : " + Util.normalizeText(AlertText) + " . Accepting it");
            Alert.accept();
        } catch (NoAlertPresentException Ex) {
            Assert.fail(Util.Log.error("Alert not present"));

        }
    }

    /**
     * verifies if a browser modal pop-up is displayed (Ex Request Error 0)
     */

    private static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    /**
     * verifies if a text matches a xpath.getText() used to verify if a certain
     * error message is displayed on page
     */

    private static boolean checkIfTextContainsString(String text, String string) {
        boolean contains;
        contains = string.toLowerCase().contains(text.toLowerCase());
        return contains;
    }

    /**
     * obvious :) - it pauses the execution for 1/2 seconds wait(2); = 1 second
     */

    public static void wait(int seconds) {
        int milliSeconds = seconds * 100;
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * file uploader using the robot function
     */
    public static void uploadFile(String filePath) {

        StringSelection ss = new StringSelection(filePath);

        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        try {
            Robot robot = new Robot();
            TestOperations.wait(1);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            TestOperations.wait(4);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            TestOperations.wait(2);

        } catch (AWTException e) {

            e.printStackTrace();
        }
    }

    /**
     * file uploader using the robot function
     */
    public static void autoDownloadFile() {


        try {
            Robot robot = new Robot();
            TestOperations.wait(1);

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);


            TestOperations.wait(2);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            TestOperations.wait(4);

        } catch (AWTException e) {

            e.printStackTrace();
        }

    }

}