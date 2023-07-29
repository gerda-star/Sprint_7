package ru.praktikum.qa_scooter;


import io.restassured.response.ValidatableResponse;
import ru.praktikum.qa_scooter.pojo.CourierDTO;

public class CourierHttpClient extends BaseHttpClient {

    private final  String url;

    public CourierHttpClient(String url) {
        super();
        this.url = url + "courier/";
    }

    public ValidatableResponse login(CourierDTO courierDTO) {
        return doPostRequest(url+"login/", courierDTO);
    }
    public ValidatableResponse createCourier(CourierDTO courierDTO) {
        return doPostRequest(url, courierDTO);
    }
    public ValidatableResponse deleteCourier(String id) {
        return doDeleteRequest(url + id);
    }
}
