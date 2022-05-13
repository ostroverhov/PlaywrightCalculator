package page;

import com.microsoft.playwright.Page;
import utils.Logger;

public class BasePage {

    private static final Logger logger = Logger.getInstance();
    protected final Page page;
    private final String namePage;
    private final String locator;

    public BasePage(Page page, String namePage, String locator) {
        this.page = page;
        this.namePage = namePage;
        this.locator = locator;
    }

    public boolean isPageOpened() {
        logger.info("Is page %s opened", namePage);
        return page.locator(locator).isVisible();
    }
}
