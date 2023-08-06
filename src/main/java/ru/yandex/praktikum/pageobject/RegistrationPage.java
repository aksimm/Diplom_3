package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");
    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    private final By registerText = By.xpath(".//main/div/h2[text()='Регистрация']");

    @Step("Ввод в поле name")
    public void fillNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод в поле email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод в поле password")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Регистрация пользователя")
    public void registerNewUser(String name, String email,String password){
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickRegisterButton();
    }

    @Step("Проверка успешной регистарции")
    public void checkSuccessfulRegistration() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
        String expected = driver.findElement(enterText).getText();
        Assert.assertEquals(expected, "Вход");
    }

    @Step("Проверка регистрации с некорректным паролем")
    public void checkUnsuccessfulRegistration() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
        String expected = driver.findElement(wrongPasswordText).getText();
        Assert.assertEquals(expected, "Некорректный пароль");
    }
}
