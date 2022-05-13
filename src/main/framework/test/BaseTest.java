package test;

import browser.BrowserFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.Logger;

@ExtendWith(RunnerExtension.class)
public class BaseTest {

    private static final Logger logger = Logger.getInstance();
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    @BeforeAll
    static void launchBrowser() {
        browser = BrowserFactory.initBrowser();
    }

    @AfterAll
    static void closeBrowser() {
        BrowserFactory.closeBrowser();
    }

    @BeforeEach
    void createContextAndNavigateToApp(TestInfo testInfo) {
        logger.info("\nStart scenario " + testInfo.getDisplayName());
        context = BrowserFactory.getContext();
        page = BrowserFactory.getPage();
        BrowserFactory.navigateToApp();
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        logger.info("\nFinish test scenario " + testInfo.getDisplayName());
        BrowserFactory.closePage();
        BrowserFactory.closeContext();
    }
}
