package browser;

import com.microsoft.playwright.*;
import utils.ConfigReader;
import utils.Logger;

public class BrowserFactory {

    private static final Logger logger = Logger.getInstance();
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static BrowserContext context;

    private BrowserFactory() {
    }

    public static Browser initBrowser() throws IllegalBrowserNameException {
        if (browser == null) {
            var browserName = ConfigReader.getParameter("Browser");
            logger.info("Browser [%s] init", browserName);
            var isHeadless = Boolean.parseBoolean(ConfigReader.getParameter("isHeadless"));
            playwright = Playwright.create();
            switch (browserName) {
                case "chrome" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                default -> {
                    logger.warn("Illegal browser");
                    throw new IllegalBrowserNameException();
                }
            }
        }
        return browser;
    }

    public static Page getPage() {
        if (page == null) {
            page = getContext().newPage();
        }
        return page;
    }

    public static BrowserContext getContext() {
        if (context == null) {
            context = initBrowser().newContext();
        }
        return context;
    }

    public static void navigateToApp() {
        logger.info("Navigate to url %s", ConfigReader.getParameter("Url"));
        page.navigate(ConfigReader.getParameter("Url"));
    }

    public static void closePage() {
        logger.info("Close page");
        page.close();
        page = null;
    }

    public static void closeContext() {
        logger.info("Close context");
        context.close();
        context = null;
    }

    public static void closeBrowser() {
        logger.info("Close browser");
        playwright.close();
    }
}
