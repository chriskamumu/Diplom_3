import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.*;
import ru.yandex.burgers.api.client.AuthClient;
import ru.yandex.burgers.model.User;
import ru.yandex.burgers.model.UserCredentials;
import ru.yandex.burgers.ui.po.ConstructorPagePO;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class GoToConstructorTest {

    private UserCredentials userCredentials;
    private String accessToken = "";
    private ConstructorPagePO mainPage;
    private AuthClient authClient;
    String expected = "Соберите бургер";
    String message = "На странице отсутствует заголовок Конструктора";

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
    public void testClickConstructorButtonFromAccount(){
        SelenideElement constructorHeader = mainPage
                .clickSignInButton()
                .fillAllFiledAndClickSignInButton(userCredentials)
                .clickAccountButtonByAuthorizedUser()
                .clickConstructorButton()
                .getConstructorHeader().shouldBe(Condition.visible);

        Assert.assertEquals(message, expected, constructorHeader.getText());
    }

    @Test
    public void testClickLogoFromAccount(){
        SelenideElement constructorHeader =  mainPage
                .clickSignInButton()
                .fillAllFiledAndClickSignInButton(userCredentials)
                .clickAccountButtonByAuthorizedUser()
                .clickLogoButton()
                .getConstructorHeader().shouldBe(Condition.visible);
        Assert.assertEquals(message, expected, constructorHeader.getText());
    }
}