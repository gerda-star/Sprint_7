package ru.praktikum.qa_scooter.orders;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.qa_scooter.OrdersHttpClient;
import ru.praktikum.qa_scooter.pojo.OrderDTO;

import static org.hamcrest.Matchers.notNullValue;
import static ru.praktikum.qa_scooter.config.ApiConfig.API_URL;


public class OrdersBaseTest {

    protected OrderDTO orderDTO;
    protected final OrdersHttpClient ordersHttpClient = new OrdersHttpClient(API_URL);

    @Step("Формируем данные для создания заказа")
    public OrderDTO generateOrderDTOForCreate(String[] color) {
        String firstName = "Naruto";
        String lastName = "Uchiha";
        String address = "Konoha, 142 apt.";
        String metroStation = "4";
        String phone = "+7 800 355 35 35";
        int rentTime = 5;
        String deliveryDate = "2020-06-06";
        String comment = "Saske, come back to Konoha";
        return new OrderDTO(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }


}
