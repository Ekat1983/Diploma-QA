package page;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import ru.netology.Data.DBHelper;

public class OrderPage extends BasePage {


    public void completePayFrom(String number, String month, String year, String holder, String cvc) {
        mainPage.clickPayButton();
        titleCard.shouldBe(Condition.text("Оплата по карте"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolder.setValue(holder);
        cardCVC.setValue(cvc);
    }

    public void checkPayApprovedStatusAssertion() {
        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getPaymentStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void checkPayDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getPaymentStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void checkPayAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getPaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void checkPayDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getPaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }
}