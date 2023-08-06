package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageobject.MainPage;

import static ru.yandex.praktikum.Client.APP_URL;

public class MainPageTest {
    WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void bunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkBuns();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void sausesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkSauses();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void fillingsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkFillings();
    }

    @After
    public void endUp() {
        driver.quit();
    }
}
