package tests;

import org.junit.*;
import pages.*;
import utilities.ConfigReader;
import utilities.MyMethods;
import utilities.ScreenShotOnFailure;

import static utilities.Driver.closeDriver;
import static utilities.Driver.getDriver;


public class CommonTest {

    public static CommonPage commonPage;
    public static HomePage homePage;
    public static BasketPage basketPage;
    public static MyMethods myMethods;

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(getDriver());

    @BeforeClass
    public static void setup() {
        commonPage = new CommonPage();
        homePage = new HomePage();
        basketPage = new BasketPage();
        myMethods = new MyMethods();
        getDriver().get(ConfigReader.getProperty("baseURL"));
    }

    @AfterClass
    public static void tearDown() {
        closeDriver();
    }
}
