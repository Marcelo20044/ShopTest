import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;
import data.DbUtils;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.PaymentByCardPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {
    DbUtils cleaner = new DbUtils();
    PaymentByCardPage pay = new PaymentByCardPage();

    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement year = $("[placeholder=\"22\"]");
    private SelenideElement owner = $$("[class=\"input__control\"]").get(3);
    private SelenideElement cvc = $("[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement errorMessage = $("[class=\"input__sub\"]");

    @Test
    public void successPayment() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.validDataEntry(cardInfo);
        TimeUnit.SECONDS.sleep(5);
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void successPaymentWithZerosInCVC() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.validDataEntryWithCVC000(cardInfo);
        TimeUnit.SECONDS.sleep(5);
        String expected = "APPROVED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithDeclinedCardNumber() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getDeclinedCardInfo();
        pay.validDataEntry(cardInfo);
        TimeUnit.SECONDS.sleep(5);
        String expected = "DECLINED";
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithShortCardNumber() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCardNumber(cardInfo);
        cardNumber.val("1234");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithLastRegistrationDate() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithOtherMonthAndYear(cardInfo);
        month.val("04");
        year.val("21");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Истёк срок действия карты"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithLateRegistrationDate() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithOtherMonthAndYear(cardInfo);
        month.val("04");
        year.val("28");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверно указан срок действия карты"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithNonExistentMonth() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherMonth(cardInfo);
        month.val("13");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверно указан срок действия карты"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithNumericValueInName() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("12345");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithShortCVC() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCVC(cardInfo);
        cvc.val("12");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCardNumber() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCardNumber(cardInfo);
        cardNumber.val("");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithEmptyMonth() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherMonth(cardInfo);
        month.val("");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithEmptyYear() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherYear(cardInfo);
        year.val("");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithEmptyOwnerName() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Поле обязательно для заполнения"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithEmptyCVC() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCVC(cardInfo);
        cvc.val("");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCardNumber() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCardNumber(cardInfo);
        cardNumber.val("abcd");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInMonth() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherMonth(cardInfo);
        month.val("ab");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInYear() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherYear(cardInfo);
        year.val("cd");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithStringValueInCVC() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCVC(cardInfo);
        cvc.val("abc");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithZerosInCardNumber() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherCardNumber(cardInfo);
        cardNumber.val("0000000000000000");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithZerosInMonth() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherMonth(cardInfo);
        month.val("00");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверно указан срок действия карты"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithZerosInYear() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherYear(cardInfo);
        year.val("00");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Истёк срок действия карты"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithNameInRussian() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("Вася");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithNameTooShort() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("A");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithNameTooLong() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("Abcdefghijklmnopqrstuvwxyz");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidDataEntryWithSpecialSymbolsInName() throws InterruptedException {
        cleaner.afterPaymentClean();
        open("http://localhost:8080/");
        val cardInfo = DataGenerator.getCardInfo();
        pay.invalidDataEntryWithAnotherOwnerName(cardInfo);
        owner.val("!#$%<>");
        continueButton.click();
        errorMessage.shouldHave(Condition.text("Неверный формат"));
        TimeUnit.SECONDS.sleep(5);
        String expected = null;
        String actual = DbUtils.getPaymentStatus();
        assertEquals(expected, actual);
    }


}
