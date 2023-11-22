package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CommonPage {
    public CommonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesButton;

    @FindBy(how = How.XPATH, using = "//*[@class='o-modal__closeButton bwi-close']")
    public WebElement closePopup;

}
