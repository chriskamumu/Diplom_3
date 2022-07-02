package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.*;
import ru.yandex.burgers.api.client.AuthClient;
import ru.yandex.burgers.model.User;
import ru.yandex.burgers.model.UserCredentials;
import ru.yandex.burgers.ui.po.ConstructorPagePO;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class GoToAccountTest {

    private UserCredentials userCredentials;
    private String accessToken = "";
    private ConstructorPagePO mainPage;
    private AuthClient authClient;

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Before
    public void setUp() {
        User user = new User("new_testlogin_kr0307@mail.ru", "password", "name");
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

    @AfterClass
    public static void tearDownBrowser (){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void testGoToAccountByUnauthorizedUser(){
        SelenideElement signingInHeader = mainPage
                .clickAccountButtonByUnauthorizedUser()
                .getSigningInHeader()
                .shouldBe(Condition.visible);
        String expected = "Вход";
        String message = "Не найден заголовок страницы Авторизации";
        Assert.assertEquals(message, expected, signingInHeader.getText());

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
