import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import data.DataGenerator;
import data.DbUtils;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.MainPage;
import page.PurchaseOnCreditPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PurchaseOnCreditTest {

    DbUtils cleaner = new DbUtils();
    MainPage main = new MainPage();
    PurchaseOnCreditPage pay = new PurchaseOnCreditPage();


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void dataDelete() {
        DbUtils cleaner = new DbUtils();
        cleaner.clean();
    }

    @Test
    public void successPayment() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void successPaymentWithZerosInCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInCVC();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithDeclinedCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getDeclinedCardInfo();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.declinedNotificationCheck();
        String expected = "DECLINED";
        String actual = DbUtils.getCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithShortCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithShortNumber();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLastRegistrationDate() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithLastRegistrationDate();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLateRegistrationDate() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithLateRegistrationDate();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNonExistentMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNonExistentMonth();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNumericValueInName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNumericValueInName();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithShortCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithShortCVC();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyCardNumber();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyMonth();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyYear();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyOwnerName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyOwnerName();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.fieldIsRequiredToFillNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyCVC();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCardNumber();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInMonth();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInYear();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCVC();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInCardNumber();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInMonth();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInYear();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameInRussian() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameInRussian();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooShort() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameTooShort();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooLong() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameTooLong();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithSpecialSymbolsInName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithSpecialSymbolsInName();
        main.clickOnCreditButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getCreditStatus();
        assertNull(actual);
    }
}
