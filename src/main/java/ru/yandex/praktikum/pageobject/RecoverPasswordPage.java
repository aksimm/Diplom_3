package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    WebDriver driver;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By recoverButton = By.xpath(".//form/button[text()='Восстановить']");
    private final By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");


    @Step("Ввод Email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Клик по кнопке 'Восстановить'")
    public void clickOnRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginButtonFromRecoverPasswordLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Восстановление пароля")
    public void recoverPassword(String email) {
        fillEmailField(email);
        clickOnRecoverButton();
    }
}
