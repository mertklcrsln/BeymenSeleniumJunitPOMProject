package utilities;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

public class ScreenShotOnFailure implements MethodRule {

    private final WebDriver driver;

    public ScreenShotOnFailure(WebDriver driver) {
        this.driver = driver;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    Driver.TakeScreenshot(frameworkMethod.getName());
                    throw t;
                }
            }
        };
    }
}