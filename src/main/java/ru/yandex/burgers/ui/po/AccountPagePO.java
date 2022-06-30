package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
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

    public SelenideElement getAccountText(){
        return accountText;
    }

    public AuthorizationPagePO clickLoggingOffButton(){
        loggingOffButton.click();
        return page(AuthorizationPagePO.class);
    }
}
