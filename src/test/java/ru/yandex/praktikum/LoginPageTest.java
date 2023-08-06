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
import ru.yandex.praktikum.pageobject.RecoverPasswordPage;
import ru.yandex.praktikum.user.User;
import ru.yandex.praktikum.user.UserGenerator;
import ru.yandex.praktikum.user.UserClient;

import static ru.yandex.praktikum.Client.APP_URL;

public class LoginPageTest {
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
    @DisplayName("Авторизация по кнопке 'Войти в аккаунт' на главной странице")
    public void loginFromLoginButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkMainPageAfterLogin();
    }

    @Test
    @DisplayName("Авторизация через кнопку «Личный кабинет»")
    public void loginFromAccountButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkMainPageAfterLogin();
    }

    @Test
    @DisplayName("Авторизация со страницы регистрации")
    public void loginFromRegistrationPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        loginPage.clickLoginLink();
        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkMainPageAfterLogin();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void loginFromRecoverPasswordPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordLink();

        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickLoginButtonFromRecoverPasswordLink();

        user = generator.random();
        ValidatableResponse response = client.create(user);
        accessToken = response.extract().path("accessToken").toString();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        loginPage.checkMainPageAfterLogin();
    }

    @After
    public void endUp() {
        client.delete(accessToken);
        driver.quit();
    }
}
