package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCardPage {

    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement year = $("[placeholder=\"22\"]");
    private SelenideElement owner = $$("[class=\"input__control\"]").get(3);
    private SelenideElement cvc = $("[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $("[class=\"notification__content\"]");
    private SelenideElement errorMessage = $("[class=\"input__sub\"]");


    public void dataEntryForPayment(DataGenerator.CardInfo info) {
        Configuration.timeout = 15000;
        cardNumber.val(info.getNumber());
        month.val(info.getMonth());
        year.val(info.getYear());
        owner.val(info.getOwner());
        cvc.val(info.getCvc());
        continueButton.click();
    }

    public void successNotificationCheck() {
        successNotification.shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void declinedNotificationCheck() {
        successNotification.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void invalidFormatNotificationCheck() {
        errorMessage.shouldHave(Condition.text("Неверный формат"));
    }

    public void expiredCardNotificationCheck() {
        errorMessage.shouldHave(Condition.text("Истёк срок действия карты"));
    }

    public void invalidExpirationCardNotificationCheck() {
        errorMessage.shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void fieldIsRequiredToFillNotificationCheck() {
        errorMessage.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }


}
