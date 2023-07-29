package ru.praktikum.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends CourierBaseTest {


    @Test
    @DisplayName("Авторизация курьера - базовый позитивный тест")
    @Description("Запрос на авторизацию вернул код 200 и id пользователя")
    public void testLoginCourier() {
        responseCreate = courierHttpClient.createCourier(courierDTO);
        ValidatableResponse response = courierHttpClient.login(courierDTO);
        deleteCourier(courierDTO, responseCreate);
        response.assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(200);
    }
    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Запрос на авторизацию без логина вернул 400 и сообщение 'Недостаточно данных для входа'")
    public void testLoginCourierWithoutLogin() {
        courierDTO.setLogin(null);
        courierHttpClient.login(courierDTO).assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Запрос на авторизацию без пароля вернул 400 и сообщение 'Недостаточно данных для входа'")
    public void testLoginCourierWithoutPassword() {
        courierDTO.setPassword(null);
        courierHttpClient.login(courierDTO).assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Запрос на авторизацию с несуществующим логином вернёт 404 и сообщение 'Учетная запись не найдена'")
    public void testLoginCourierWithInvalidLogin() {
        ValidatableResponse response = courierHttpClient.login(courierDTO);
        response.assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
    @Test
    @DisplayName("Авторизация курьера c неправильным паролем")
    @Description("Запрос на авторизацию с несуществующей парой вернёт 404 и сообщение 'Учетная запись не найдена'")
    public void testLoginCourierWithInvalidPassword() {
        createCourierWithId(courierDTO);
        courierDTO.setPassword(courierDTO.getPassword()+".");

        ValidatableResponse response = courierHttpClient.login(courierDTO);
        courierHttpClient.deleteCourier(courierDTO.getId());

        response.assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
