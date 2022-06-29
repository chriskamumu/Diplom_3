package ru.yandex.burgers.ui.po;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class CommonPagePO {

    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement accountButton;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton;

    @FindBy(how = How.LINK_TEXT, using = "Конструктор")
    private SelenideElement constructorButton;

    @FindBy(how = How.LINK_TEXT, using = "Лента Заказов")
    private SelenideElement orderFeedButton;

    public AccountPagePO clickAccountButton(){
        accountButton.click();
        return page(AccountPagePO.class);
    }

    public ConstructorPagePO clickLogoButton(){
        logoButton.click();
        return page(ConstructorPagePO.class);
    }

    public ConstructorPagePO clickConstructorButton(){
        constructorButton.click();
        return page(ConstructorPagePO.class);
    }

    public OrderFeedPagePO clickOrderFeedButton(){
        orderFeedButton.click();
        return page(OrderFeedPagePO.class);
    }

}
