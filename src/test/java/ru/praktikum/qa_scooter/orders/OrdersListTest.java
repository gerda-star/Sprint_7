package ru.praktikum.qa_scooter.orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrdersListTest extends OrdersBaseTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Запрос возвращает список заказов - проверка по наличию хотябы одного id")
    public void getOrdersListTest(){
        ordersHttpClient.getOrders().assertThat()
                .statusCode(200)
                .and()
                .body("orders.id",notNullValue());
    }
}
