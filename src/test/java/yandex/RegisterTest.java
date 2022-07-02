package yandex;

import com.codeborne.selenide.Condition;
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

public class RegisterTest {

    private ConstructorPagePO mainPage;
    private User user;
    private String accessToken = "";
    private AuthClient authClient;

    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Before
    public void setUp(){
        mainPage = open(ConstructorPagePO.URL, ConstructorPagePO.class);
        authClient = new AuthClient();
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


    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Test
    public void testRegisterSuccessful(){
        user = new User("random_kr0307@mail.ru", "password", "Кристина");
        mainPage
                .clickSignInButton()
                .clickRegisterButton()
                .fillAllFiledAndClickRegisterButton(user)
                .getSigningInHeader().shouldBe(Condition.visible);

        accessToken = authClient.login(new UserCredentials(user.getEmail(), user.getPassword()))
                .assertThat().statusCode(SC_OK).extract().path("accessToken");

    }

    @Test
    public void testRegisterWithIncorrectPassword(){
        user = new User("random_kr0307@mail.ru", "pass5", "Кристина");
        mainPage
                .clickSignInButton()
                .clickRegisterButton()
                .setPasswordField(user.getPassword())
                .clickRegisterButton()
                .getIncorrectPasswordErrorMessage().shouldBe(Condition.visible);

    }

}
