package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By enterAccountButton = By.xpath(".//a[@href='/account']");
    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private final By activeIngredient = By.cssSelector(".tab_tab_type_current__2BEPc");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickAccountButton() {
        driver.findElement(enterAccountButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterText));
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickOnBunsButton() {
        driver.findElement(bunsButton).click();

    }
    @Step("Клик по кнопке 'Соусы'")
    public void clickOnSaucesButton() {
        driver.findElement(saucesButton).click();
    }
    @Step("Клик по кнопке 'Начинки'")
    public void clickOnFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Получение названия активного раздела")
    public String getTextOfActiveIngridient(){
        return driver.findElement(activeIngredient).getText();
    }

    @Step("Проверка перехода к разделу Булки")
    public void checkBuns() {
        clickOnSaucesButton();
        clickOnBunsButton();
        String expected = "Булки";
        assertEquals(expected, getTextOfActiveIngridient());


    }
    @Step("Проверка перехода к разделу 'Соусы'")
    public void checkSauses() {
        clickOnSaucesButton();
        String expected = "Соусы";
        assertEquals(expected, getTextOfActiveIngridient());
    }
    @Step("Проверка перехода к разделу 'Начинки'")
    public void checkFillings() {
        clickOnFillingsButton();
        String expected = "Начинки";
        assertEquals(expected, getTextOfActiveIngridient());
    }
}
