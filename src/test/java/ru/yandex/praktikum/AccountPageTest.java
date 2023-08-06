package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.user.User;
import ru.yandex.praktikum.user.UserGenerator;
import static ru.yandex.praktikum.Client.APP_URL;
import ru.yandex.praktikum.user.UserClient;

public class AccountPageTest {
    WebDriver driver;
    private User user = new User();
    private final UserClient client = new UserClient();
    private final UserGenerator generator = new UserGenerator();
    private String accessToken;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("Переход с главной страницы в личный кабинет после авторизации")
    public void accountPageFromMainPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkAccountPageFromMainPage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void constructorPageFromAccountPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkConstructorPageFromMainPage();
    }
    @Test
    @DisplayName("Переход из личного кабинета по клику на лого Бургрера")
    public void mainPageFromAccountPageByClickOnLogo(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkMainPageFromAccountPageByClickOnLogo();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitAccountTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkExitFromAccount();
    }

    @After
    public void endUp() {
        client.delete(accessToken);
        driver.quit();
    }
}
