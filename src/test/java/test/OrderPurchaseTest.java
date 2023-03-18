package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.OrderPage;
import ru.netology.Data.DBHelper;
import ru.netology.Data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class OrderPurchaseTest {

    public OrderPage orderPage = new OrderPage();

    @BeforeEach
    void setup() {
        DBHelper.clearDB();
        open("http://localhost:8080");
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Полные пути оплаты

    @Test
    @DisplayName("(1) Positive - CreditCard -Buying Tour Successful")
    void shouldSuccessfulBuyByCreditCard() {

        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }


    @Test
    @DisplayName("(2) Positive - Buying Tour Failed")
    void shouldFailBuyByCard() {
        orderPage.completePayFrom(DataHelper.getDeclinedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayDeclinedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }


    @Test
    @DisplayName("(3) Negative - Buying Tour CardField Invalid")
    void shouldFailBuyByCardInvalidCardField() {
        orderPage.completePayFrom(DataHelper.getRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }


    // Поле "Номер карты"

    @Test
    @DisplayName("(4) Positive - Valid CardField 16 symbols")
    void shouldSuccessfulBuyByValidCardFieldSixteenSymbols() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(5) Negative - CardField 15 symbols")
    void shouldFailBuyByInvalidCardFieldFifteenSymbols() {
        orderPage.completePayFrom(DataHelper.getFifteenRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkNumberFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(6) Negative - CardField One symbols")
    void shouldFailBuyByInvalidCardFieldOneSymbols() {
        orderPage.completePayFrom(DataHelper.getOneRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkNumberFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(7) Negative - Empty CardField")
    void shouldFailBuyByInvalidCardFieldEmpty() {
        orderPage.completePayFrom(DataHelper.getEmptyNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkNumberFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(8) Negative - CardField All Symbols - Zeroes")
    void shouldFailBuyByInvalidAllZeroesCardField() {
        orderPage.completePayFrom(DataHelper.getZeroNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    // Поле "Месяц"

    @Test
    @DisplayName("(9) Positive - MonthField - 01")
    void shouldSuccessfulBuyByValidFirstMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getFirstMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(10) Positive - MonthField - 02")
    void shouldSuccessfulBuyByValidSecondMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getSecondMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(11) Positive - MonthField - 11")
    void shouldSuccessfulBuyByValidElevenMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getElevenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(12) Positive - MonthField - 12")
    void shouldSuccessfulBuyByValidTwelveMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getTwelveMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(13) Negative - MonthField - Empty")
    void shouldFailBuyByInvalidEmptyMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkMonthFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(14) Negative - CreditCard - MonthField - Zero")
    void shouldFailBuyByInvalidZeroMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getZeroMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkMonthFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(15) Negative - MonthField - 13")
    void shouldFailBuyByInvalidThirteenMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getThirteenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkMonthFieldPeriodError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(16) Negative - MonthField - One Symbol")
    void shouldFailBuyByInvalidOneSymbolMonthMonthFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getOneSymbolMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkMonthFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    // Поле "Год"

    @Test
    @DisplayName("(17) Positive - YearField - Current Year")
    void shouldSuccessfulBuyByValidCurrentYearYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(18) Positive - YearField - Current Year + 1")
    void shouldSuccessfulBuyByValidCurrentYearPlusOneYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(19) Positive - YearField - Current Year + 2")
    void shouldSuccessfulBuyByValidCurrentYearPlusTwoYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(2), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(20) Negative - YearField - Current Year + 7")
    void shouldSuccessfulBuyByValidCurrentYearPlusSevenYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(7), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkYearFieldPlusPeriodError,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(21) Negative - YearField - Current Year + 8")
    void shouldSuccessfulBuyByValidCurrentYearPlusEightYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(8), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkYearFieldPlusPeriodError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(22) Negative - YearField - Current Year Empty")
    void shouldFailBuyByInvalidYearEmptyYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getEmptyYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkYearFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }


    @Test
    @DisplayName("(23) Negative - YearField - Current Year 1 Symbol")
    void shouldFailBuyByInvalidYearOneSymbolYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getOneSymbolYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkYearFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(24) Negative - YearField - Current Year -1")
    void shouldFailBuyByValidCurrentYearMinusOneYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(-1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkYearFieldMinusPeriodError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(25) PayCard - YearField - Zero")
    void shouldFailBuyByValidCurrentYearPlusNineYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(9), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkYearFieldPlusPeriodError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(26) Negative - YearField - Zero")
    void shouldFailBuyByInvalidZeroYearFieldCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getZeroYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkYearFieldMinusPeriodError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    // Поле "Владелец"

    @Test
    @DisplayName("(27) Positive - HolderField - 3 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldThreeSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(28) Positive - HolderField - 4 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldFourSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(29) Positive - HolderField  With SpaceBar")
    void shouldSuccessfulBuyByValidHolderFieldWithSpaceBarCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??????? ???????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(30) Positive - HolderField  With Dash")
    void shouldSuccessfulBuyByValidHolderFieldWithDashCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????-???????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(31) Positive - HolderField - 19 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldNineteenSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(32) Positive - HolderField - 20 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldTwentySymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(33) Positive - HolderField - 2 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwoSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(34) Negative - HolderField - Empty")
    void shouldFailBuyByInvalidHolderFieldEmptyCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEmptyHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkDenialAssertion,
                orderPage::checkHolderFieldEmptyError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(35) Positive - HolderField - Digits")
    void shouldFailBuyByInvalidHolderFieldDigitsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getDigitsHolder("########"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(36) Positive - HolderField SpecialCharacters")
    void shouldFailBuyByInvalidHolderFieldSpecialCharactersCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpecialCharactersHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(37) Negative - HolderField Spacebars")
    void shouldFailBuyByInvalidHolderFieldSpacebarsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpacesHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkHolderFieldEmptyError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(38) Positive - HolderField Cyrillic")
    void shouldFailBuyByInvalidHolderFieldCyrillicPayCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getCyrillicHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }


    @Test
    @DisplayName("(39) Positive - HolderField - 21 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwentyOneSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("?????????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    // Поле "CVC"

    @Test
    @DisplayName("(40) Positive - CVCField - 3 Symbols")
    void shouldSuccessfulBuyByValidCVCFieldThreeSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayApprovedStatusAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(41) Negative - CVCField - 1 Symbol")
    void shouldFailBuyByInvalidCVCFieldOneSymbolCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getOneSymbolCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkCvcFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(42) Negative - CVCField - 2 Symbols")
    void shouldFailBuyByInvalidCVCFieldTwoSymbolsCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getTwoSymbolsCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkCvcFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(43) Negative - CVCField Empty")
    void shouldFailBuyByInvalidCVCFieldEmptyCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getEmptyCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkCvcFieldFormatError,
                orderPage::checkPayDenialCountAssertion,
                orderPage::checkOrderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(44) Positive - CVCField - Zero")
    void shouldFailBuyByInvalidCVCFieldZeroPayCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getZeroSymbolsCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::checkAcceptAssertion,
                orderPage::checkPayAcceptCountAssertion,
                orderPage::checkOrderAcceptCountAssertion
        );
    }
}

