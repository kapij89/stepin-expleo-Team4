package utils;

import com.microsoft.playwright.Locator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public abstract class WebActions {

  
    public static boolean waitUntilElementDisplayed(Locator locator, int timeoutSec) {
        boolean elementVisible = locator.isVisible();
        int timer = 0;
        while (!elementVisible && timer < timeoutSec) {
            try {
                Thread.sleep(1000);
                elementVisible = locator.isVisible();
                timer++;

            } catch (Exception e) {
                System.out.println(locator + "was not visible.");
            }
        }
        return elementVisible;
    }

    //base64 encoding: This is used to encrypt the password and save the encrypted value in config.properties file
    public static void encrypt() {
        try {
            byte[] encodedBytes = Base64.getEncoder().encode("yourPassword".getBytes("UTF-8"));
            String encodedValue = new String(encodedBytes);
        } catch (Exception e) {
            System.out.println("Password was not Encrypted.");
        }
    }

    

    public static String getRowColValue(String filePath, String sheetName, int rowNum, int colNum) {
        String fileName = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\functional\\" + filePath + ".xlsx";
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
    }
}