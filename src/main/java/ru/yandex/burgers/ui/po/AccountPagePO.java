package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/account";

    @FindBy(how = How.LINK_TEXT, using = "Профиль")
    private SelenideElement profileSection;

    @FindBy(how = How.CLASS_NAME, using = "Account_text__fZAIn")
    private SelenideElement accountText;

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement loggingOffButton;

    @Step("Получить текста на странице личного кабинета")
    public SelenideElement getAccountText(){
        return accountText;
    }

    @Step("Нажать на кнопку выхода на странице личного кабинета")
    public AuthorizationPagePO clickLoggingOffButton(){
        loggingOffButton.click();
        return page(AuthorizationPagePO.class);
    }
}
