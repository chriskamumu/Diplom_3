package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Val;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ConstructorPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    public AuthorizationPagePO clickSignInButton(){
        signInButton.click();
        return page(AuthorizationPagePO.class);
    }

    public boolean isSignInButtonVisible(){
        return signInButton.is(Condition.visible);
    }

    public SelenideElement getCreateOrderButton(){
        return createOrderButton;
    }

}
