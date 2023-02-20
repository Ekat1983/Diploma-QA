package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    protected final MainPage mainPage = new MainPage();


    protected final SelenideElement continueButton = $(".form-field button");
    protected final SelenideElement titleCard = $x("//*[@id='root']/div/h3");


    protected final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    protected final SelenideElement cardMonth = $("[placeholder='08']");
    protected final SelenideElement cardYear = $("[placeholder='22']");
    protected final SelenideElement cardHolder = $x("//*[.='Владелец'] //input");
    protected final SelenideElement cardCVC = $("[placeholder='999']");


    protected final SelenideElement notificationTitleAccept = $(".notification_status_ok");
    protected final SelenideElement notificationContentAccept = $(".notification_status_ok");

    protected final SelenideElement notificationTitleDenial = $(".notification_status_error");
    protected final SelenideElement notificationContentDenial = $(".notification_status_error");


    protected final SelenideElement numberFieldError = $x("//span[contains(text(),'Номер карты')]").parent().$(".input__sub");
    protected final SelenideElement monthFieldError = $x("//span[contains(text(),'Месяц')]").parent().$(".input__sub");
    protected final SelenideElement yearFieldError = $x("//span[contains(text(),'Год')]").parent().$(".input__sub");
    protected final SelenideElement holderFieldError = $x("//span[contains(text(),'Владелец')]").parent().$(".input__sub");
    protected final SelenideElement cvcFieldError = $x("//span[contains(text(),'CVC/CVV')]").parent().$(".input__sub");


}
