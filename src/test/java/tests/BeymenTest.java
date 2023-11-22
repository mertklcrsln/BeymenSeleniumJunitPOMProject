package tests;

import org.junit.Test;
import org.openqa.selenium.Keys;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class BeymenTest extends CommonTest {

    @Test
    public void beymenTest() throws IOException, InterruptedException {

        myMethods.checkCurrentUrl(ConfigReader.getProperty("baseURL"));
        myMethods.seeElement(basketPage.acceptCookiesButton);
        commonPage.acceptCookiesButton.click();
        commonPage.closePopup.click();

        myMethods.seeElement(homePage.beymenLogo);
        myMethods.seeElement(homePage.productSearchBox);
        homePage.productSearchBox.sendKeys(myMethods.getDataFromSheet("./urunler.xlsx","1","1"));

        myMethods.takeScreenShot("first search");
        myMethods.clearInTextboxElement(homePage.productSearchBox);
        homePage.productSearchBox.sendKeys(myMethods.getDataFromSheet("./urunler.xlsx","1","2"));
        myMethods.takeScreenShot("second search");
        myMethods.pressKey(Keys.ENTER);

        myMethods.seeElement(homePage.productListTitle);
        myMethods.checkCurrentUrlStartsWith("https://www.beymen.com/search?q=g%C3%B6mlek");
        myMethods.seeElement(homePage.productName.get(1));
        int randomProductNumber = ThreadLocalRandom.current().nextInt(1, homePage.productPrice.size());
        myMethods.saveProductInfoInTxt(homePage.productName.get(randomProductNumber),homePage.productPrice.get(randomProductNumber));
        String choosenProductName = homePage.productName.get(randomProductNumber).getText();
        String choosenProductPrice = homePage.productPrice.get(randomProductNumber).getText();
        homePage.productCart.get(randomProductNumber).click();
        myMethods.seeElement(homePage.firstEnableSizeOnCart);
        homePage.firstEnableSizeOnCart.click();
        myMethods.takeScreenShot("selected product size before add basket");
        homePage.addBasketButton.click();
        myMethods.seeElement(homePage.productAddedBasketSuccessfullyText);
        myMethods.takeScreenShot("successfully added basket");
        homePage.myBasketButton.click();

        myMethods.seeElement(basketPage.shoppingCartTitle);
        myMethods.checkCurrentUrl("https://www.beymen.com/cart");
        myMethods.verifyElementTextEqualsExpectedText(basketPage.basketProductName,choosenProductName);
        basketPage.verifyProductPriceForBasket(choosenProductPrice,basketPage.basketProductPrice);
        myMethods.seeElement(basketPage.basketProductCountSelectbox);
        basketPage.basketProductCountSelectbox.click();
        basketPage.basketTwoProductCountOption.click();
        myMethods.seeElement(basketPage.productCountSuccessfullyUpdatedText);
        basketPage.changeProductCountTwoandVerifyPrice(choosenProductPrice,basketPage.basketProductPrice);
        myMethods.takeScreenShot("change Product Count Two and Verify Price");
        myMethods.seeElement(basketPage.removeProductButton);
        basketPage.removeProductButton.click();
        myMethods.seeElement(basketPage.removedProductSuccessfullyText);
        myMethods.takeScreenShot("removed product successfully");
        myMethods.seeElement(basketPage.emptyBasketText);
        myMethods.takeScreenShot("empty basket");

    }
}
