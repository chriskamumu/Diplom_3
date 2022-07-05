package tests.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
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
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class RegisterTest {

    private ConstructorPagePO mainPage;
    private User user;
    private AuthClient authClient;

    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        Configuration.startMaximized = true;
    }

    @Before
    public void setUp(){
        mainPage = open(ConstructorPagePO.URL, ConstructorPagePO.class);
        authClient = new AuthClient();
    }

    @After
    public void tearDown() {
        ValidatableResponse response = authClient.login(new UserCredentials(user.getEmail(), user.getPassword()));

        if (response.extract().path("accessToken") != null)
        {
            String accessToken = response.extract().path("accessToken");
            authClient.delete(accessToken).assertThat().statusCode(SC_ACCEPTED);
        }
    }

    @AfterClass
    public static void tearDownBrowser (){
        WebDriverRunner.closeWebDriver();
    }


    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Test
    public void testRegisterSuccessful(){
        user = UserUtils.buildRandom();
        mainPage
                .clickSignInButton()
                .clickRegisterButton()
                .fillAllFiledAndClickRegisterButton(user)
                .getSigningInHeader().shouldBe(Condition.visible);

    }

    @Test
    public void testRegisterWithIncorrectPassword(){
        user = UserUtils.buildRandomEmailAndName("pass5");
        mainPage
                .clickSignInButton()
                .clickRegisterButton()
                .setPasswordField(user.getPassword())
                .clickRegisterButton()
                .getIncorrectPasswordErrorMessage().shouldBe(Condition.visible);

    }

}
