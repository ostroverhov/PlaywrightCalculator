package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.BasePage;
import utils.Logger;

public class PrivacyOrTermsPage extends BasePage {

    private static final Logger logger = Logger.getInstance();
    private final Locator pageLabel = page.locator("body");

    public PrivacyOrTermsPage(Page page) {
        super(page, "Privacy or terms page", "body");
    }

    public String getPageMessage() {
        logger.info("Get page message");
        return pageLabel.textContent();
    }
}
