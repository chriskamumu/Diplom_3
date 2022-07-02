package yandex;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
import ru.yandex.burgers.ui.po.ConstructorPagePO;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    private ConstructorPagePO mainPage;

    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        Configuration.startMaximized = true;
    }

    @Before
    public void setUp(){
        mainPage = open(ConstructorPagePO.URL, ConstructorPagePO.class);
    }

    @AfterClass
    public static void tearDownBrowser (){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void testClickFillingsSection(){
        String result = mainPage
                .clickFillingsButton()
                .findSelectedSection().getText();
        String message = "Не выбрана секция Начинки";
        Assert.assertEquals(message, "Начинки", result);
    }

    @Test
    public void testClickSaucesSection(){
        String result = mainPage
                .clickSaucesButton()
                .findSelectedSection().getText();
        String message = "Не выбрана секция Соусы";
        Assert.assertEquals(message, "Соусы", result);
    }

    @Test
    public void testClickBunsSection(){
        String result = mainPage
                .findSelectedSection().getText();
        String message = "Не выбрана секция Булки";
        Assert.assertEquals(message, "Булки", result);
    }


}
