import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.*;
import ru.yandex.burgers.api.client.AuthClient;
import ru.yandex.burgers.model.User;
import ru.yandex.burgers.model.UserCredentials;
import ru.yandex.burgers.ui.po.AuthorizationPagePO;
import ru.yandex.burgers.ui.po.ConstructorPagePO;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class GoToAccountTest {

    private UserCredentials userCredentials;
    private String accessToken = "";
    private ConstructorPagePO mainPage;
    private AuthorizationPagePO authorizationPage;
    private AuthClient authClient;

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Before
    public void setUp() {
        User user = new User("new_login_kr@mail.ru", "password", "name");
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        authClient = new AuthClient();
        accessToken = authClient.register(user).statusCode(SC_OK).extract().path("accessToken");
        mainPage = open(ConstructorPagePO.URL, ConstructorPagePO.class);
    }

    @After
    public void tearDown() {
        if (!accessToken.equals("")) {
            authClient.delete(accessToken).assertThat().statusCode(SC_ACCEPTED);
            accessToken = "";
        }
    }

    @Test
    public void testGoToAccountByUnauthorizedUser(){
        SelenideElement headerEnter = mainPage
                .clickAccountButtonByUnauthorizedUser()
                .getHeaderEnter()
                .shouldBe(Condition.visible);
        String expected = "Вход";
        String message = "Не найден заголовок страницы Авторизации";
        Assert.assertEquals(message, expected, headerEnter.getText());

    }

    @Test
    public void testGoToAccountByAuthorizedUser(){
        SelenideElement accountText = mainPage
                .clickSignInButton()
                .fillAllFiledAndClickSignInButton(userCredentials)
                .clickAccountButtonByAuthorizedUser()
                .getAccountText().shouldBe(Condition.visible);
        String expected = "В этом разделе вы можете изменить свои персональные данные";
        String message = "Не найден текст Личного кабинета";
        Assert.assertEquals(message, expected, accountText.getText());
    }
}
