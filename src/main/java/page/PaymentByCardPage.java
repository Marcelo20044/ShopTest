package page;

import data.DataGenerator;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCardPage {

    public void validDataEntry(DataGenerator.CardInfo info) {
        $(byText("Купить")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").val(info.getNumber());
        $("[placeholder=\"08\"]").val(info.getMonth());
        $("[placeholder=\"22\"]").val(info.getYear());
        $$("[class=\"input__control\"]").get(3).val(info.getOwner());
        $("[placeholder=\"999\"]").val(info.getCvc());
        $(byText("Продолжить")).click();
    }
}
