package ru.praktikum.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.qa_scooter.pojo.OrderDTO;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.praktikum.qa_scooter.config.ApiConfig.API_URL;

@RunWith(Parameterized.class)
public class OrdersCreateTest {

    private final OrdersHttpClient ordersHttpClient = new OrdersHttpClient(API_URL);

    private final String[] color;

    public OrdersCreateTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK", "GREY"},
                {""}
        };
    }


    @Test
    @DisplayName("Создание заказа с вариативностью параметра цвет")
    @Description("Запрос на создание заказа возвращает track, когда указан цвет:\n"+
                    "'BLACK', 'GREY', 'BLACK, GREY', ''")
    public void createOrderTest(){
        OrderDTO orderDTO = generateOrder();
        ValidatableResponse response = ordersHttpClient.createOrder(orderDTO);

        response.assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());


    }
    @Step
    public OrderDTO generateOrder() {
        String firstName = "Naruto";
        String lastName = "Uchiha";
        String address = "Konoha, 142 apt.";
        String metroStation = "4";
        String phone = "+7 800 355 35 35";
        int rentTime = 5;
        String deliveryDate = "2020-06-06";
        String comment = "Saske, come back to Konoha";
        String[] color = this.color;
        return new OrderDTO(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
