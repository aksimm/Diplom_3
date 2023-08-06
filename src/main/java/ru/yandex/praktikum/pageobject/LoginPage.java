package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.Client;
import static org.junit.Assert.assertTrue;


public class LoginPage extends Client {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name = 'Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    private final By registerText = By.xpath(".//main/div/h2[text()='Регистрация']");
    private final By recoverPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");
    private final By recoverPasswordText = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    private final By mainPageText = By.xpath(".//section/h1[text()='Соберите бургер']");
    private final By enterAccountButtonAfterLogin = By.xpath(".//p[text()='Личный Кабинет']");
    private final By profileText = By.xpath(".//a[text()='Профиль']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    private final By logoStellarBurgers = By.xpath(".//div/a[@href='/']");
    private final By loginLink = By.xpath(".//a[@href='/login']");

    @Step("Ввод в поле email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод в поле password")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
    }

    @Step("Регистрация пользователя")
    public void loginUser(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickLoginButton();

    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPasswordText));
    }

    @Step("Клик по кнопке 'Конструктор'.")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по кнопке 'Выйти'.")
    public void clickExitButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Клик по лого на главной странице'.")
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }

    @Step("Проверка вывода главной страницы после авторизации")
    public void checkMainPageAfterLogin() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageText));
        boolean mainPageTextIsDisplayed = driver.findElement(mainPageText).isDisplayed();
        assertTrue(mainPageTextIsDisplayed);
    }

    @Step("Проверка перехода с главной страницы в личный кабинет после авторизации")
    public void checkAccountPageFromMainPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageText));
        driver.findElement(enterAccountButtonAfterLogin).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(profileText));
        boolean profileTextIsDisplayed = driver.findElement(profileText).isDisplayed();
        assertTrue(profileTextIsDisplayed);
    }

    @Step("Проверка перехода из личного кабинета в конструктор")
    public void checkConstructorPageFromMainPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageText));
        driver.findElement(enterAccountButtonAfterLogin).click();
        clickConstructorButton();
        checkMainPageAfterLogin();
    }

    @Step("Проверка перехода из личного кабинета на главную страницу по нажатию на Лого Stellar Burgers")
    public void checkMainPageFromAccountPageByClickOnLogo() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageText));
        driver.findElement(enterAccountButtonAfterLogin).click();
        clickLogoStellarBurgers();
        checkMainPageAfterLogin();
    }

    @Step("Проверка выхода из аккаунта")
    public void checkExitFromAccount() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageText));
        driver.findElement(enterAccountButtonAfterLogin).click();
        clickExitButton();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
        boolean enterTextIsDisplayed = driver.findElement(enterText).isDisplayed();
        assertTrue(enterTextIsDisplayed);
    }
}
