import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import data.DbUtils;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.MainPage;
import page.PaymentByCardPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentByCardTest {

    MainPage main;
    PaymentByCardPage pay;


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        DbUtils.clean();
        open("http://localhost:8080/");
        main = new MainPage();
    }


    @Test
    public void successPayment() {
        val cardInfo = DataGenerator.getCardInfo();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void successPaymentWithZerosInCVC() {
        val cardInfo = DataGenerator.getCardInfoWithZerosInCVC();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.successNotificationCheck();
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithDeclinedCardNumber() {
        val cardInfo = DataGenerator.getDeclinedCardInfo();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.declinedNotificationCheck();
        String expected = "DECLINED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithShortCardNumber() {
        val cardInfo = DataGenerator.getCardInfoWithShortNumber();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLastRegistrationDate() {
        val cardInfo = DataGenerator.getCardInfoWithLastRegistrationDate();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithLateRegistrationDate() {
        val cardInfo = DataGenerator.getCardInfoWithLateRegistrationDate();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNonExistentMonth() {
        val cardInfo = DataGenerator.getCardInfoWithNonExistentMonth();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNumericValueInName() {
        val cardInfo = DataGenerator.getCardInfoWithNumericValueInName();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithShortCVC() {
        val cardInfo = DataGenerator.getCardInfoWithShortCVC();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCardNumber() {
        val cardInfo = DataGenerator.getCardInfoWithEmptyCardNumber();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyMonth() {
        val cardInfo = DataGenerator.getCardInfoWithEmptyMonth();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyYear() {
        val cardInfo = DataGenerator.getCardInfoWithEmptyYear();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyOwnerName() {
        val cardInfo = DataGenerator.getCardInfoWithEmptyOwnerName();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.fieldIsRequiredToFillNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCVC() {
        val cardInfo = DataGenerator.getCardInfoWithEmptyCVC();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCardNumber() {
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCardNumber();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInMonth() {
        val cardInfo = DataGenerator.getCardInfoWithStringValueInMonth();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInYear() {
        val cardInfo = DataGenerator.getCardInfoWithStringValueInYear();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCVC() {
        val cardInfo = DataGenerator.getCardInfoWithStringValueInCVC();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInCardNumber() {
        val cardInfo = DataGenerator.getCardInfoWithZerosInCardNumber();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInMonth() {
        val cardInfo = DataGenerator.getCardInfoWithZerosInMonth();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidExpirationCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithZerosInYear() {
        val cardInfo = DataGenerator.getCardInfoWithZerosInYear();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.expiredCardNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameInRussian() {
        val cardInfo = DataGenerator.getCardInfoWithNameInRussian();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooShort() {
        val cardInfo = DataGenerator.getCardInfoWithNameTooShort();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithNameTooLong() {
        val cardInfo = DataGenerator.getCardInfoWithNameTooLong();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    public void invalidDataEntryWithSpecialSymbolsInName() {
        val cardInfo = DataGenerator.getCardInfoWithSpecialSymbolsInName();
        pay = main.clickOnPurchaseButton();
        pay.dataEntryForPayment(cardInfo);
        pay.invalidFormatNotificationCheck();
        String actual = DbUtils.getPaymentStatus();
        assertNull(actual);
    }


}
