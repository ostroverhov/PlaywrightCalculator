package test;

import browser.BrowserFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Paths;

public class RunnerExtension implements AfterTestExecutionCallback {

    private static final String SCREENSHOT_PATH = "src/test/screenshots";

    @Override
    public void afterTestExecution(ExtensionContext context) {
        var testResult = context.getExecutionException().isPresent();
        if (testResult) {
            BrowserFactory.getPage().screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(SCREENSHOT_PATH, String.format("%s.png", context.getDisplayName().replace("()", ""))))
                    .setFullPage(true));
        }
    }
}
