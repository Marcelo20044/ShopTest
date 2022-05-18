package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCardPage {

    private SelenideElement purchaseButton = $(byText("Купить"));
    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement year = $("[placeholder=\"22\"]");
    private SelenideElement owner = $$("[class=\"input__control\"]").get(3);
    private SelenideElement cvc = $("[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $("[class=\"notification__content\"]");


    public void validDataEntry(DataGenerator.CardInfo info) {
        Configuration.timeout = 15000;
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        year.val(info.getYear());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
        continueButton.click();
        successNotification.shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void validDataEntryWithCVC000(DataGenerator.CardInfo info) {
        Configuration.timeout = 15000;
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        year.val(info.getYear());
        owner.val(info.getOwner());
        cvc.val("000");
        continueButton.click();
        successNotification.shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void invalidDataEntryWithAnotherCardNumber(DataGenerator.CardInfo info) {
        purchaseButton.click();
        month.val(info.getMonth());
        year.val(info.getYear());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
    }

    public void invalidDataEntryWithOtherMonthAndYear(DataGenerator.CardInfo info) {
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
    }

    public void invalidDataEntryWithAnotherMonth(DataGenerator.CardInfo info) {
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        year.val(info.getYear());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
    }

    public void invalidDataEntryWithAnotherYear(DataGenerator.CardInfo info) {
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
    }

    public void invalidDataEntryWithAnotherOwnerName(DataGenerator.CardInfo info) {
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        year.val(info.getYear());
        cvc.val(info.getCvc());
    }

    public void invalidDataEntryWithAnotherCVC(DataGenerator.CardInfo info) {
        purchaseButton.click();
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        year.val(info.getYear());
        owner.val(info.getOwner());
    }


}
