package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.netology.Data.DBHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


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

    public void continueClick() {
        continueButton.click();
    }

    public void acceptAssertion() {
        notificationTitleAccept.shouldBe(Condition.text("Успешно"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        notificationContentAccept.shouldBe(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void denialAssertion() {
        notificationTitleDenial.shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        notificationContentDenial.shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void numberFieldFormatError() {
        numberFieldError.shouldBe(Condition.text("Неверный формат"), Duration.ofSeconds(10)).shouldBe(Condition.visible);;
    }

    public void monthFieldFormatError() {
        monthFieldError.shouldBe(Condition.text("Неверный формат"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void monthFieldPeriodError() {
        monthFieldError.shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void yearFieldFormatError() {
        yearFieldError.shouldBe(Condition.text("Неверный формат"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void yearFieldMinusPeriodError() {
        yearFieldError.shouldBe(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void yearFieldPlusPeriodError() {
        yearFieldError.shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void holderFieldEmptyError() {
        holderFieldError.shouldBe(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void holderFieldFormatError() {
        holderFieldError.shouldBe(Condition.text("Неверный формат"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void cvcFieldFormatError() {
        cvcFieldError.shouldBe(Condition.text("Неверный формат"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void creditApprovedStatusAssertion() {
        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected,statusActual);
    }

    public void creditDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }
}
