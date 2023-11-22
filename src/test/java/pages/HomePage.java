package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage extends CommonPage {

    @FindBy(how = How.XPATH, using = "//img[@alt='Beymen']")
    public WebElement beymenLogo;

    @FindBy(how = How.XPATH, using = "(//*[@class='o-header__search--input'])[last()]")
    public WebElement productSearchBox;

    @FindBy(how = How.XPATH, using = "//*[@id='productListTitle']")
    public WebElement productListTitle;

    @FindBy(how = How.XPATH, using = "//*[@id='productList']/div//span[@class='m-productCard__newPrice']")
    public List<WebElement> productPrice;

    @FindBy(how = How.XPATH, using = "//*[@id='productList']/div//span[@class='m-productCard__desc']")
    public List<WebElement> productName;

    @FindBy(how = How.XPATH, using = "//div[@class='m-productCard__stockCartIcon']")
    public List<WebElement> productCart;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'m-variation__item') and not(contains(@class,'-disabled'))])[1]")
    public WebElement firstEnableSizeOnCart;

    @FindBy(how = How.XPATH, using = "//button[@id='addBasket']")
    public WebElement addBasketButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Ürün sepetinize eklenmiştir.']")
    public WebElement productAddedBasketSuccessfullyText;

    @FindBy(how = How.XPATH, using = "//*[@title='Sepetim']")
    public WebElement myBasketButton;

}
