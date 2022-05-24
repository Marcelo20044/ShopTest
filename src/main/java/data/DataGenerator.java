package data;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {

    private DataGenerator() {
    }

    static String generateMonth(int months) {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern("MM"));
    }

    static String generateYear(int years) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("yy"));
    }

    static String generateLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
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
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Vasya", "123");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442", generateMonth(1), generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithZerosInCVC() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Vasya", "000");
    }

    public static CardInfo getCardInfoWithShortNumber() {
        return new CardInfo("1234", generateMonth(1), generateYear(1), "Vasya", "000");
    }

    public static CardInfo getCardInfoWithLastRegistrationDate() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(0), generateLastYear(), "Vasya", "000");
    }

    public static CardInfo getCardInfoWithLateRegistrationDate() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(0), generateYear(6), "Vasya", "000");
    }

    public static CardInfo getCardInfoWithNonExistentMonth() {
        return new CardInfo("4444 4444 4444 4441", "13", generateYear(0), "Vasya", "000");
    }

    public static CardInfo getCardInfoWithNumericValueInName() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "12345", "123");
    }

    public static CardInfo getCardInfoWithShortCVC() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Vasya", "12");
    }

    public static CardInfo getCardInfoWithEmptyCardNumber() {
        return new CardInfo("", generateMonth(1), generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithEmptyMonth() {
        return new CardInfo("4444 4444 4444 4441", "", generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithEmptyYear() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), "", "Vasya", "123");
    }

    public static CardInfo getCardInfoWithEmptyOwnerName() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "", "123");
    }

    public static CardInfo getCardInfoWithEmptyCVC() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Vasya", "");
    }

    public static CardInfo getCardInfoWithStringValueInCardNumber() {
        return new CardInfo("abcd", generateMonth(1), generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithStringValueInMonth() {
        return new CardInfo("4444 4444 4444 4441", "ab", generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithStringValueInYear() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), "cd", "Vasya", "123");
    }

    public static CardInfo getCardInfoWithStringValueInCVC() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Vasya", "abc");
    }

    public static CardInfo getCardInfoWithZerosInCardNumber() {
        return new CardInfo("0000 0000 0000 0000", generateMonth(1), generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithZerosInMonth() {
        return new CardInfo("4444 4444 4444 4441", "00", generateYear(1), "Vasya", "123");
    }

    public static CardInfo getCardInfoWithZerosInYear() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), "00", "Vasya", "123");
    }

    public static CardInfo getCardInfoWithNameInRussian() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Вася", "123");
    }

    public static CardInfo getCardInfoWithNameTooShort() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "A", "123");
    }

    public static CardInfo getCardInfoWithNameTooLong() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "Abcdefghijklmnopqrstuvwxyz", "123");
    }

    public static CardInfo getCardInfoWithSpecialSymbolsInName() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(1), generateYear(1), "!#$%<>", "123");
    }
}
