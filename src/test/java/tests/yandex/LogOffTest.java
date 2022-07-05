package tests.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.ScreenShooter;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import ru.yandex.burgers.api.client.AuthClient;
import ru.yandex.burgers.model.User;
import ru.yandex.burgers.model.UserCredentials;
import ru.yandex.burgers.ui.po.ConstructorPagePO;
import utils.UserUtils;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class LogOffTest {

    private UserCredentials userCredentials;
    private ConstructorPagePO mainPage;
    private AuthClient authClient;

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        Configuration.startMaximized = true;
    }

    @Before
    public void setUp() {
        User user = UserUtils.buildRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        authClient = new AuthClient();
        authClient.register(user).statusCode(SC_OK).extract().path("accessToken");
        mainPage = open(ConstructorPagePO.URL, ConstructorPagePO.class);
    }

    @After
    public void tearDown() {
        ValidatableResponse response = authClient.login(userCredentials).assertThat().statusCode(SC_OK);
        if (response.extract().path("accessToken") != null)
        {
            String accessToken = response.extract().path("accessToken");
            authClient.delete(accessToken).assertThat().statusCode(SC_ACCEPTED);
        }    }

    @AfterClass
    public static void tearDownBrowser (){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void testLoggingOff(){
        SelenideElement signingInHeader = mainPage
                .clickSignInButton()
                .fillAllFiledAndClickSignInButton(userCredentials)
                .clickAccountButtonByAuthorizedUser()
                .clickLoggingOffButton()
                .getSigningInHeader().shouldBe(Condition.visible);
        String expected = "Вход";
        String message = "Не найден заголовок страницы Авторизации";
        Assert.assertEquals(message, expected, signingInHeader.getText());
    }

}
