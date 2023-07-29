package ru.praktikum.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.qa_scooter.pojo.CourierDTO;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends CourierBaseTest {


    @Test
    @DisplayName("Успешное создание курьера, запрос возвращает ok:true")
    @Description("Статус запроса на создание курьера с корректными данным должен быть 201, в теле ответа ok:true")
    public void testCreateCourier() {
        responseCreate = courierHttpClient.createCourier(courierDTO);
        responseCreate.assertThat()
                .body("ok", equalTo(true))
                .and()
                .statusCode(201);

    }
    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Статус запроса на создание уже существующего курьера должен быть 409")
    public void testCreateExistingCourier() {
        responseCreate = courierHttpClient.createCourier(courierDTO);
        courierHttpClient.createCourier(courierDTO).assertThat().statusCode(409);
    }

    @Test
    @DisplayName("Создание курьера с логином, который уже используется")
    @Description("Статус запроса на создание курьера с логином,который занят возвращает ошибку 'Этот логин уже используется'")
    public void testCreateCourierWithUsedLogin() {
        responseCreate = courierHttpClient.createCourier(courierDTO);
        CourierDTO courierDTOWithUsedLogin = generateCourier().setLogin(courierDTO.getLogin());

        courierHttpClient.createCourier(courierDTOWithUsedLogin).assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Статус запроса на создание курьера без логина должен быть 400")
    public void testCreateWithoutLogin() {
        courierDTO.setLogin(null);
        responseCreate = courierHttpClient.createCourier(courierDTO);
        responseCreate.assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Статус запроса на создание курьера без пароля должен быть 400")
    public void testCreateWithoutPassword() {
        courierDTO.setPassword(null);
        responseCreate = courierHttpClient.createCourier(courierDTO);
        responseCreate.assertThat().statusCode(400);
    }

    @After
    public void deleteCourier() {
        deleteCourier(courierDTO, responseCreate);
    }
}
