package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.burgers.model.User;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPagePO extends CommonPagePO{

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private SelenideElement headerEnter;

    @FindBy(how = How.XPATH, using = ".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][1]/div[@class='input__container']/div/input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]/div[@class='input__container']/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][3]/div[@class='input__container']/div/input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordErrorMessage;

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement signInButton;

    public RegisterPagePO setNameField(String name){
        nameField.setValue(name);
        return page(RegisterPagePO.class);
    }

    public RegisterPagePO setEmailField(String email){
        emailField.setValue(email);
        return page(RegisterPagePO.class);
    }

    public RegisterPagePO setPasswordField(String password){
        passwordField.setValue(password);
        return page(RegisterPagePO.class);
    }

    public RegisterPagePO clickRegisterButton(){
        registerButton.click();
        return page(RegisterPagePO.class);
    }

    public AuthorizationPagePO fillAllFiledAndClickRegisterButton(User user){
        setNameField(user.getName())
                .setEmailField(user.getEmail())
                .setPasswordField(user.getPassword())
                .clickRegisterButton();
        return page(AuthorizationPagePO.class);
    }

    public SelenideElement getIncorrectPasswordErrorMessage(){
        return incorrectPasswordErrorMessage;
    }

    public AuthorizationPagePO clickSignInButton(){
        signInButton.click();
        return page(AuthorizationPagePO.class);
    }
}
