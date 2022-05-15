import data.DataGenerator;
import data.DbUtils;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.PaymentByCardPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {
    DbUtils cleaner = new DbUtils();

    @Test
    public void successPayment() throws InterruptedException {
        open("http://localhost:8080/");
        PaymentByCardPage pay = new PaymentByCardPage();
        val cardInfo = DataGenerator.getCardInfo();
        pay.validDataEntry(cardInfo);
        int expected = 4500000;
        TimeUnit.SECONDS.sleep(3);
        int actual = DbUtils.getPaymentAmount();
        assertEquals(expected, actual);
        cleaner.clean();
    }
}
