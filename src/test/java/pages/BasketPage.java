package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasketPage extends CommonPage {


    @FindBy(how = How.XPATH, using = "//*[@class='m-basket__header--title' and text()='ALIŞVERİŞ SEPETİM']")
    public WebElement shoppingCartTitle;

    @FindBy(how = How.XPATH, using = "//span[@class='m-basket__productInfoName']")
    public WebElement basketProductName;

    @FindBy(how = How.XPATH, using = "//span[@class='m-productPrice__salePrice']")
    public WebElement basketProductPrice;

    @FindBy(how = How.XPATH, using = "//select[@id='quantitySelect0-key-0']")
    public WebElement basketProductCountSelectbox;

    @FindBy(how = How.XPATH, using = "//option[@value='2']")
    public WebElement basketTwoProductCountOption;

    @FindBy(how = How.XPATH, using = "//*[@id='notifyMessage' and text()='Ürün adetiniz güncellenmiştir.']")
    public WebElement productCountSuccessfullyUpdatedText;

    @FindBy(how = How.XPATH, using = "(//button[@id='removeCartItemBtn0-key-0'])[1]")
    public WebElement removeProductButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizden ürün başarılı bir şekilde silinmiştir.']")
    public WebElement removedProductSuccessfullyText;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement emptyBasketText;


    public void verifyProductPriceForBasket(String oneProductPrice,WebElement twoProductPriceElement){
        int firstPrice = Integer.parseInt(oneProductPrice.replaceAll(" ","").replace("T","").replace("L","").replace(".",""));
        String[] subs = twoProductPriceElement.getText().split(",");
        int twoProductPrice = Integer.parseInt(subs[0].replaceAll(" ","").replace(".",""));
        Assert.assertEquals("Ürünün ilk fiyatı ile sepete ekldiğindeki fiyatının eşit değil",firstPrice,twoProductPrice);

    }
    public void changeProductCountTwoandVerifyPrice(String oneProductPrice,WebElement twoProductPriceElement) throws InterruptedException {
        Thread.sleep(2000);
        int firstPrice = Integer.parseInt(oneProductPrice.replaceAll(" ","").replace("T","").replace("L","").replace(".",""));
        String[] subs = twoProductPriceElement.getText().split(",");
        int twoProductPrice = Integer.parseInt(subs[0].replaceAll(" ","").replace(".",""));
        Assert.assertEquals("Ürünün ilk fiyatı ile sepete ekldiğindeki fiyatının iki katı eşit değil",firstPrice*2,twoProductPrice);
    }

}
