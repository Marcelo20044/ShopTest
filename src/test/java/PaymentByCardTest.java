import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import data.DbUtils;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.MainPage;
import page.PaymentByCardPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentByCardTest {

    DbUtils cleaner = new DbUtils();
    MainPage main = new MainPage();
    PaymentByCardPage pay = new PaymentByCardPage();


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
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void successPaymentWithZerosInCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInCVC();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithDeclinedCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getDeclinedCardInfo();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.declinedNotificationCheck();
        String expected = "DECLINED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithShortCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithShortNumber();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLastRegistrationDate() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithLastRegistrationDate();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLateRegistrationDate() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithLateRegistrationDate();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNonExistentMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNonExistentMonth();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNumericValueInName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNumericValueInName();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithShortCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithShortCVC();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyCardNumber();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyMonth();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyYear();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyOwnerName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyOwnerName();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.fieldIsRequiredToFillNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithEmptyCVC();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCardNumber();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInMonth();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInYear();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCVC() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCVC();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInCardNumber() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInCardNumber();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInMonth() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInMonth();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInYear() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithZerosInYear();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameInRussian() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameInRussian();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooShort() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameTooShort();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooLong() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithNameTooLong();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithSpecialSymbolsInName() {
        cleaner.clean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfoWithSpecialSymbolsInName();
        main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }


}
