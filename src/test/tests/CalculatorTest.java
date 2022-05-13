package tests;

import browser.BrowserFactory;
import enums.BottomPageButtons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.PrivacyOrTermsPage;
import test.BaseTest;

public class CalculatorTest extends BaseTest {

    private static final String ALERT_MESSAGE = "Please enter an integer";
    private static final String PRIVACY_PAGE_MESSAGE = "This is the privacy document. We are not yet ready with it. Stay tuned!";
    private static final String TERMS_PAGE_MESSAGE = "This is the terms and conditions document. We are not yet ready with it. Stay tuned!";
    private static final String TEST_STRING = "asd";
    private final MainPage mainPage = new MainPage(BrowserFactory.getPage());
    private final PrivacyOrTermsPage privacyOrTermsPage = new PrivacyOrTermsPage(BrowserFactory.getPage());

    @Test
    void checkMainPageOpened() {
        Assertions.assertTrue(mainPage.isPageOpened());
    }

    @Test
    void checkPrivacyPage() {
        mainPage.clickPageButton(BottomPageButtons.PRIVACY);
        var privacyPageMessage = privacyOrTermsPage.getPageMessage();
        Assertions.assertTrue(privacyOrTermsPage.isPageOpened());
        Assertions.assertEquals(PRIVACY_PAGE_MESSAGE, privacyPageMessage);
    }

    @Test
    void checkTermsPage() {
        mainPage.clickPageButton(BottomPageButtons.TERMS);
        var privacyPageMessage = privacyOrTermsPage.getPageMessage();
        Assertions.assertTrue(privacyOrTermsPage.isPageOpened());
        Assertions.assertEquals(TERMS_PAGE_MESSAGE, privacyPageMessage);
    }

    @Test
    void checkAlert() {
        mainPage.clickCalculateButton();
        var alertMessage = mainPage.getAlertMessage();
        Assertions.assertEquals(ALERT_MESSAGE, alertMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120"
    })
    void checkCalculation(String value, String result) {
        mainPage.typeText(value);
        mainPage.clickCalculateButton();
        var resultText = mainPage.getResultText();
        Assertions.assertTrue(resultText.contains(value));
        Assertions.assertTrue(resultText.contains(result));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, -10 })
    void checkCalculationWithNegativeInteger(int value) {
        mainPage.typeText(String.valueOf(value));
        mainPage.clickCalculateButton();
        Assertions.assertFalse(mainPage.isResultLabelDisplayed());
    }

    @Test
    void checkAlertWithStringInsteadOfInteger() {
        mainPage.typeText(TEST_STRING);
        mainPage.clickCalculateButton();
        var alertMessage = mainPage.getAlertMessage();
        Assertions.assertEquals(ALERT_MESSAGE, alertMessage);
    }
}
