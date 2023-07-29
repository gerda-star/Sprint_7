package ru.praktikum.qa_scooter;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.qa_scooter.pojo.OrderDTO;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public class OrdersHttpClient extends BaseHttpClient{
    private final String url;


    public OrdersHttpClient(String url) {
        super();
        this.url = url + "orders/";

    }

    public ValidatableResponse createOrder(OrderDTO orderDTO) {
        return doPostRequest(url, orderDTO);
    }

    public ValidatableResponse cancelOrder(String track) {
        return doPutRequest(url+"cancel?track=" + track);
    }

    public ValidatableResponse getOrders(){
        return doGetRequest(url);
    }

}
