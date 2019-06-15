package util;

import framework.TestOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestParams;
import util.Util;

public class VerifyPagesFromXls extends TestOperations {

    public String sheetName() {
        return "";
    }

    @Test(dataProvider = "GetDataFromXlsFile")
    public void verifyUrlUsingXPath(String elementChecked, String url, String xpath) {

        goToUrl(url);
        Util.Log.info("open page : " + url + " to check :" + elementChecked);
        assertElementPresent(xpath);

    }

    @DataProvider(name = "GetDataFromXlsFile")
    public Object[][] getDataFromXlsFile() throws Exception {
        return (Util.getTableArray(sheetName()));
    }

}
