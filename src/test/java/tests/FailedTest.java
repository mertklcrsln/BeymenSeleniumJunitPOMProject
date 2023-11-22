package tests;

import org.junit.Test;
import utilities.ConfigReader;

public class FailedTest extends CommonTest {

    @Test
    public void failTestCaseWithTakeScreenshot() throws InterruptedException {

        myMethods.seeElement(homePage.beymenLogo);
        myMethods.checkCurrentUrl(ConfigReader.getProperty("failedTestBaseURL"));

    }
}
