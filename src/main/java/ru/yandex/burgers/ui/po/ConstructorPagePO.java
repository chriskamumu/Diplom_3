package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Find;
import com.codeborne.selenide.commands.Val;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

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

    public SelenideElement getConstructorHeader(){
        return constructorHeader;
    }

    public ConstructorPagePO clickBunsButton(){
        bunsSection.click();
        return page(ConstructorPagePO.class);
    }

    public ConstructorPagePO clickSaucesButton(){
        saucesSection.click();
        return page(ConstructorPagePO.class);
    }

    public ConstructorPagePO clickFillingsButton(){
        fillingsSection.click();
        return page(ConstructorPagePO.class);
    }

    public boolean isBunsSectionSelected(){
        return getElementAbove(bunsSection).attr("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isCauseSectionSelected(){
        return getElementAbove(saucesSection).attr("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isFillingsSectionSelected(){
        return getElementAbove(fillingsSection).attr("class").contains("tab_tab_type_current__2BEPc");
    }

    private SelenideElement getElementAbove(SelenideElement element){
        return element.find(By.xpath(".."));
    }

    public SelenideElement findSelectedSection(){
        return $(byCssSelector(".tab_tab_type_current__2BEPc>span"));
    }
}
