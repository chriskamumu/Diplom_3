package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class PasswordRecoveryPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement signInButton;

    @Step("Нажать на кнопку входа на странице восстановления пароля")
    public AuthorizationPagePO clickSignInButton(){
        signInButton.click();
        return page(AuthorizationPagePO.class);
    }
}
