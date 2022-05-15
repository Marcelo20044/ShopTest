package data;

import lombok.Value;

public class DataGenerator {

    private DataGenerator() {
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "12", "22", "Vasya", "123");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "12", "22", "Vasya", "123");
    }
}
