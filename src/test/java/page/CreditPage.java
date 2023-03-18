package page;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import ru.netology.Data.DBHelper;

public class CreditPage extends BasePage {

    public void completeCreditFrom(String number, String month, String year, String holder, String cvc) {
        mainPage.clickCreditButton();
        titleCard.shouldBe(Condition.text("Кредит по данным карты"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolder.setValue(holder);
        cardCVC.setValue(cvc);
    }

    public void checkCreditApprovedStatus() {
        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected,statusActual);
    }

    public void checkCreditDeclinedStatus() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void checkCreditAcceptCount() {
        long countExpected = 1;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void checkCreditDenialCount() {
        long countExpected = 0;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }
}