package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/account";

    @FindBy(how = How.LINK_TEXT, using = "Профиль")
    private SelenideElement profileSection;

    @FindBy(how = How.CLASS_NAME, using = "Account_text__fZAIn")
    private SelenideElement accountText;

    public SelenideElement getAccountText(){
        return accountText;
    }
}
