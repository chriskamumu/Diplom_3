package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Find;
import com.codeborne.selenide.commands.Val;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selectors.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ConstructorPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement constructorHeader;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsSection;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesSection;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsSection;

    @Step("Нажать на кнопку Вход на странице Конструктор")
    public AuthorizationPagePO clickSignInButton(){
        signInButton.click();
        return page(AuthorizationPagePO.class);
    }

    @Step("Получить кнопку оформления заказа")
    public SelenideElement getCreateOrderButton(){
        return createOrderButton;
    }

    @Step("Получить заголовок страницы Конструктора")
    public SelenideElement getConstructorHeader(){
        return constructorHeader;
    }

    @Step("Нажать на секцию Булки")
    public ConstructorPagePO clickBunsButton(){
        bunsSection.click();
        return page(ConstructorPagePO.class);
    }

    @Step("Нажать на секцию Соусы")
    public ConstructorPagePO clickSaucesButton(){
        saucesSection.click();
        return page(ConstructorPagePO.class);
    }

    @Step("Нажать на секцию Начинки")
    public ConstructorPagePO clickFillingsButton(){
        fillingsSection.click();
        return page(ConstructorPagePO.class);
    }

    @Step("Определить выбранную секцию")
    public SelenideElement findSelectedSection(){
        return $(byCssSelector(".tab_tab_type_current__2BEPc>span"));
    }
}
