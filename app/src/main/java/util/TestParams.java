package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestParams {

    private static int TIMEOUT;
    private static int WAIT;
    private static String BASE_URL;
    private static String SELENIUM_URL;
    private static String BROWSER;
    private static String USER_MD5;
    private static String SCREEN_SHOT_DIR;
    private static String MAIN_WINDOW;
    private static String DATA_FILE_PATH_XLS;



    // timeout
    public static void setTimeout(int property) {
        TIMEOUT = property;
    }

    public static int getTimeout() {
        return TIMEOUT;
    }

    // wait TestOperations
    public static void setWait(int property) {
        WAIT = property;
    }

    public static int getWait() {
        return WAIT;
    }

    // unitbvurl
    public static void setBaseUrl(String property) {
        BASE_URL = property;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    // selenium url
    public static void setSeleniumUrl(String property) {
        SELENIUM_URL = property;
    }

    public static String getSeleniumUrl() {
        return SELENIUM_URL;
    }

    // selenium url
    public static void setMainWindow(String property) {
        MAIN_WINDOW = property;
    }

    public static String getMainWindow() {
        return MAIN_WINDOW;
    }

    //browser
    public static void setBrowser(String property) {
        BROWSER = property;
    }

    public static String getBrowser() {
        return BROWSER;
    }

    // usermd5
    public static void setUserMd5(String property) {
        USER_MD5 = property;
    }

    public static String getUserMd5() {
        return USER_MD5;
    }

    // ScreenShotDir
    public static void setScreenShotDir(String property) {
        SCREEN_SHOT_DIR = property;
    }

    public static String getScreenShotDir() {
        return SCREEN_SHOT_DIR;
    }


    // xlsFilePath
    public static void setXlsFilePath(String property) {
        DATA_FILE_PATH_XLS = property;
    }

    public static String getXlsFilePath() {
        return DATA_FILE_PATH_XLS;
    }

    public static void readParams() {

        Properties prop = new Properties();
        Util.Log.info("Preparing test execution ");
        try {

            FileInputStream fis = new FileInputStream("D:/testreport/testRunner.properties");

            Util.Log.info("Loading unitbv.proprieties file ");

            prop.load(fis);
            setTimeout(Integer.parseInt(prop.getProperty("timeout")));
            setWait(Integer.parseInt(prop.getProperty("wait")));
            setBaseUrl(prop.getProperty("base.url"));
            setSeleniumUrl(prop.getProperty("selenium.url"));
            setBrowser(prop.getProperty("browser"));
            setUserMd5(prop.getProperty("user.md5"));
            setScreenShotDir(prop.getProperty("screen.shot.dir"));
            setXlsFilePath(prop.getProperty("file.path.xls"));


            Util.Log.info("File Loaded. Test parameters are: ");

        } catch (FileNotFoundException e) {
            e.getCause();
            Util.Log.error("unitbv.proprieties file not found ");
        } catch (IOException e) {
            e.getCause();
        }

        Util.Log.info("@ ____General Parameters___ @ ");
        Util.Log.info(" screen.shot.dir 				: " + getScreenShotDir());
        Util.Log.info(" unitbv.url 						: " + getBaseUrl());
        Util.Log.info(" selenium.url 					: " + getSeleniumUrl());
        Util.Log.info(" browser						    : " + getBrowser());
        Util.Log.info(" user.md5 						: " + getUserMd5());
        Util.Log.info(" timeout 						: " + getTimeout());
        Util.Log.info(" wait 							: " + getWait());
        Util.Log.info("@ ____Test Data Parameters (Xls)____@  ");

        Util.Log.info("@ ____________ @ ");
        Util.Log.info("Finished reading parameters. Begining test execution");

    }
}
