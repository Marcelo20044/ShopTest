package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement purchaseButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public PaymentByCardPage clickOnPurchaseButton(){
        purchaseButton.click();
        return new PaymentByCardPage();
    }

    public PurchaseOnCreditPage clickOnCreditButton(){
        creditButton.click();
        return new PurchaseOnCreditPage();
    }
}
