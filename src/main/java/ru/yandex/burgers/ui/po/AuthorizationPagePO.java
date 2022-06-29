package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.burgers.model.UserCredentials;

import static com.codeborne.selenide.Selenide.page;

public class AuthorizationPagePO {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement headerEnter;

    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement registerButton;

    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement passwordRecoveryButton;

    @FindBy(how = How.XPATH, using = ".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][1]/div[@class='input__container']/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]/div[@class='input__container']/div/input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement signInButton;

    public SelenideElement getHeaderEnter(){
        return headerEnter;
    }

    public RegisterPagePO clickRegisterButton(){
        registerButton.click();
        return page(RegisterPagePO.class);
    }

    public AuthorizationPagePO setEmailField(String email){
        emailField.setValue(email);
        return page(AuthorizationPagePO.class);
    }

    public AuthorizationPagePO setPasswordField(String password){
        passwordField.setValue(password);
        return page(AuthorizationPagePO.class);
    }

    public AuthorizationPagePO clickSignInButton(){
        signInButton.click();
        return page(AuthorizationPagePO.class);
    }

    public ConstructorPagePO fillAllFiledAndClickSignInButton(UserCredentials userCredentials){
        setEmailField(userCredentials.getEmail())
                .setPasswordField(userCredentials.getPassword())
                .clickSignInButton();
        return page(ConstructorPagePO.class);
    }

    public PasswordRecoveryPagePO clickPasswordRecoveryButton(){
        passwordRecoveryButton.click();
        return page(PasswordRecoveryPagePO.class);
    }
}
