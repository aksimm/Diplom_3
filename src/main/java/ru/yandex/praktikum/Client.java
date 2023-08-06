package ru.yandex.praktikum;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    public final static String APP_URL = "https://stellarburgers.nomoreparties.site/";
    public final static String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";

    public RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(APP_URL);
    }
}