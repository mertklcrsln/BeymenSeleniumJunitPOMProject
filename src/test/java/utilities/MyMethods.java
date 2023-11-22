package utilities;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

import static utilities.Driver.driver;


public class MyMethods {


    public void checkCurrentUrl(String expectedUrl) {
        System.out.println("Expected Link: " + expectedUrl + "\nActual Link:" + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }

    public void checkCurrentUrlStartsWith(String expectedUrl) {

        System.out.println("Expected Link Start With: " + expectedUrl + "\nActual Link:" + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().startsWith(expectedUrl));
    }

    public boolean ifElementPresentInSeconds(WebElement elementKey, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            w.until(ExpectedConditions.visibilityOf(elementKey));
            System.out.println("Element is on the screen");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("elementTimeOutForSeconds"))));
            return true;
        } catch (Exception e) {
            System.out.println("Element is NOT on the screen" + e.getMessage());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("elementTimeOutForSeconds"))));
            return false;
        }

    }

    public void seeElement(WebElement element) {
        element.isDisplayed();
        System.out.println(element + " Element is display");
    }

    public void pressKey(Keys key){
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();

    }
    public void doNotSeeElementInSeconds(WebElement element, int seconds)
    {
        System.out.println("Element is NOT on the screen");
        Assert.assertFalse("Element is on the screen!",ifElementPresentInSeconds(element, seconds));
    }


//    public Boolean checkElementClickable(WebElement elementFindBy) {
//        try {
//            if (elementFindBy.isDisplayed() && elementFindBy.isEnabled()) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Throwable var3) {
//            fail("Element: " + elementFindBy + " checkElementClickable kontrol edilirken bir problem oluştu !!");
//            return false;
//        }
//    }



    public void scrollToCenterOfElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
            Thread.sleep(1000);
            System.out.println("Scrolling successful.");
        } catch (Exception e) {
            Assert.fail("Exception "+e);
            System.out.println("Scrolling Failed!");
        }
    }

    public void ifExistsClick(WebElement element) throws InterruptedException {
        if (ifElementPresentInSeconds(element, 10)) {
            Thread.sleep(1000);
            Assert.assertTrue("Element is on the screen but not clickable", element.isDisplayed());
            element.click();
            System.out.println(element + " Element Found and Clicked!");
        } else System.out.println(element.toString() + " Element NOT Found!");
    }

    public void mouseoverToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).perform();
    }

    public void switchToWindow(int windowNumber) {
        Object[] allWindows = driver.getWindowHandles().toArray();
        for (Object allWindow : allWindows) System.out.println(allWindow.toString());
        driver.switchTo().window(allWindows[windowNumber - 1].toString());
    }

    public void switchToLastOpenedWindow() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public void verifyTextOfAllElementOfListEqualsExpectedText(List<WebElement> list, String expectedText) {
        scrollToCenterOfElement(list.get(1));
        int index = 1;
        for (WebElement element : list) {
            String elementText = element.getText();
            System.out.println("Expected text: " + expectedText + "\n" + Integer.toString(index++) + ". Element text: " + elementText);
            Assert.assertEquals("there are not equals",elementText, expectedText);
        }
    }

    public void verifyElementTextEqualsExpectedText(WebElement element, String expectedText) {

        String elementText = element.getText();
        System.out.println("Expected text: " + expectedText + "\nElement text: " + elementText);
        Assert.assertEquals(elementText, expectedText);

    }

    public void clearInTextboxElement(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
        element.clear();
        Thread.sleep(1000);
    }

    public void waitUntilElementInvisibleInSeconds(WebElement element, int seconds) throws InterruptedException {
        while (ifElementPresentInSeconds(element, 1) && seconds > 0) {
            System.out.println("Element still on the screen!");
            Thread.sleep(1000);
            seconds--;
        }
        Assert.assertFalse("Element is visible!",ifElementPresentInSeconds(element, 1));
    }
    public void takeScreenShot(String saveText){
        Driver.TakeScreenshot(saveText);
    }
    public static String getDataFromSheet(String xlsxPath, String a,String b) throws IOException {
            FileInputStream excelFile = new FileInputStream(new File(xlsxPath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            cellIterator.hasNext();
            if (a.equals("1") && b.equals("1")){
                Cell currentCell = cellIterator.next();
                System.out.println(currentCell.getStringCellValue());
                return currentCell.getStringCellValue();
            }else if(a.equals("1") && b.equals("2")){
                Cell currentCell = cellIterator.next();
                currentCell.getStringCellValue();
                currentCell = cellIterator.next();
                System.out.println(currentCell.getStringCellValue());
                return  currentCell.getStringCellValue();
            }else return "xlsx bos geldi";
    }

    public void saveProductInfoInTxt(WebElement product, WebElement price) throws IOException {
        String productText = product.getText();

        String priceText = price.getText();

        File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(productText+": "+priceText);
        bWriter.close();
    }
}
