package ru.yandex.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.Client;

public class UserClient extends Client {

    private final static String NEW_USER_API = "/api/auth/register";
    private final static String AUTH_USER_API = "/auth/user";

    @Step("Регистрация пользователя")
    public ValidatableResponse create(User user) {
        return spec()
                .body(user)
                .when()
                .post(NEW_USER_API)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .when()
                .delete(AUTH_USER_API)
                .then();
    }
}
