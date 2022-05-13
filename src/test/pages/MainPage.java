package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import enums.BottomPageButtons;
import page.BasePage;
import utils.Logger;

public class MainPage extends BasePage {

    private static final Logger logger = Logger.getInstance();
    private final Locator calculateButton = page.locator("[type='submit']");
    private final Locator enterIntegerAlert = page.locator("//*[@id='resultDiv']");
//    private final Locator privacyButton = page.locator("a[href='/privacy']");
//    private final Locator termsButton = page.locator("a[href='/terms']");
    private final Locator numberInput = page.locator("input");
    private final Locator resultLabel = page.locator("//*[@id='resultDiv' and contains(text(), 'The factorial')]");

    public MainPage(Page page) {
        super(page, "Main page", "h1");
    }

    public void clickCalculateButton() {
        logger.info("Click calculate button");
        calculateButton.click();
    }

    public void clickPageButton(BottomPageButtons button) {
        logger.info("Click %s button", button.getDescription());
        page.locator(String.format("a[href='/%s']", button.getDescription())).click();
    }

    public String getAlertMessage() {
        logger.info("Get alert message");
        return enterIntegerAlert.textContent();
    }

    public void typeText(String text) {
        logger.info("Type text %s", text);
        numberInput.fill(text);
    }

    public String getResultText() {
        logger.info("Get result text");
        return resultLabel.textContent();
    }

    public boolean isResultLabelDisplayed() {
        logger.info("Is result label displayed");
        return resultLabel.isVisible();
    }
}
