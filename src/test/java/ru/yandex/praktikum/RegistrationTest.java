package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;
import ru.yandex.praktikum.user.User;
import ru.yandex.praktikum.user.UserGenerator;
import static ru.yandex.praktikum.Client.APP_URL;

public class RegistrationTest {
    WebDriver driver;
    private User user = new User();
    private final UserGenerator generator = new UserGenerator();

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationNewUserTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        user = generator.random();
        registrationPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.checkSuccessfulRegistration();
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registrationNewUserWithWrongPasswordTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        user = generator.random();
        user.setPassword("123");
        registrationPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.checkUnsuccessfulRegistration();
    }

    @After
    public void endUp() {
        driver.quit();
    }
}
