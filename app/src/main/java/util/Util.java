package util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static String number;

    public static String getNumber() {

        return number;
    }

    public static void setNumber(String noDemande) {

        number = noDemande;
    }

    /**
     * sysdate logger, logs the date in the specified format. In this case
     * dd-MM_HH-mm-ss
     */
    public static String logTime() {

        Format formatter;
        Date date = new Date();
        formatter = new SimpleDateFormat("dd-MM_HH-mm-ss");
        return formatter.format(date);
    }

    /**
     * string formatter for eliminating accents and bizarre characters form
     * logs. Formats in ASCII
     */
    public static String normalizeText(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		nfdNormalizedString = nfdNormalizedString.replaceAll("[^\\p{ASCII}]", "");
        return nfdNormalizedString;
    }


    public static void ScreenShot() {
        try {
            String newFileNamePath;
            new File(TestParams.getScreenShotDir()).mkdirs();
            File directory = new File(TestParams.getScreenShotDir());
            newFileNamePath = TestParams.getScreenShotDir() + "/" + logTime() + ".png";
            System.out.println(" [ERROR] - ScreenShot taken: " + newFileNamePath);
            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(1680, 1050));
            ImageIO.write(bi, "png", new File(newFileNamePath));
            Log.error("ScreenShot taken : " + newFileNamePath);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads data from xls file from the @sheetNeme - passed from
     * getDataFromXlsFile() - passed by sheetName() from the main test class
     *
     * @DATA_TAG_XLS - the delimiter in the xls sheet when defining the test
     * data table
     * @DATA_FILE_PATH_XLS - file location
     */

    public static String[][] getTableArray(String sheetName) {

        final String DATA_TAG_XLS = "Data";
        try {
            String[][] tabArray;
            Workbook workbook = Workbook.getWorkbook(new File(TestParams.getXlsFilePath()));
            Sheet sheet = workbook.getSheet(sheetName);
            int startRow, startCol, endRow, endCol, ci, cj;
            Cell tableStart = sheet.findCell(DATA_TAG_XLS);
            startRow = tableStart.getRow();
            startCol = tableStart.getColumn();
            Cell tableEnd = sheet.findCell(DATA_TAG_XLS, startCol + 1, startRow + 1, 100, 64000, false);
            endRow = tableEnd.getRow();
            endCol = tableEnd.getColumn();
            tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
            ci = 0;
            for (int i = startRow + 1; i < endRow; i++, ci++) {
                cj = 0;
                for (int j = startCol + 1; j < endCol; j++, cj++) {
                    tabArray[ci][cj] = sheet.getCell(j, i).getContents();
                }
            }
            return (tabArray);

        } catch (Exception e) {
            System.out.println(" [CONFIGURATION ERROR] - Sheet name : " + sheetName + " not found in " + TestParams.getXlsFilePath() +". Skipping test");
            Log.error(" [CONFIGURATION ERROR] - Sheet name : " + sheetName + " not found in " + TestParams.getXlsFilePath() + ". Skipping test");
        }
        return null;

    }

    /**
     * logger, visible in the reporter output from the xsl test report. Uses
     * Reporter.Log() from webdriver
     */
    public static class Log {

        public static String info(String message) {

            String s = logTime() + " [INFO] - " + message;
            Reporter.log(s);
            return (s);
        }

        public static String warn(String message) {

            String s = logTime() + " [WARN] - " + message;
            Reporter.log(s);
            return (s);
        }

        public static String error(String message) {

            String s = logTime() + " [ERROR] - " + message;
            Reporter.log(s);
            return (s);
        }

    }

}
