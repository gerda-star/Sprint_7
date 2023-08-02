package ru.praktikum.qa_scooter.orders;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.qa_scooter.OrdersHttpClient;
import ru.praktikum.qa_scooter.pojo.OrderDTO;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrdersCreateTest extends OrdersBaseTest{

    private final String[] color;

    public OrdersCreateTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getColor() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }
    @Test
    @DisplayName("Создание заказа с вариативностью параметра цвет")
    @Description("Запрос на создание заказа возвращает track, когда указан цвет:\n"+
            "'BLACK', 'GREY', 'BLACK, GREY', ''")
    public void createOrderTest(){
        orderDTO = generateOrderDTOForCreate(this.color);
        ValidatableResponse response = ordersHttpClient.createOrder(orderDTO);
        orderDTO.setTrack(response.extract().path("track").toString());
        response.assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());


    }

    @After()
    public void cancelOrder(){
        ordersHttpClient.cancelOrder(orderDTO.getTrack());
    }


}

