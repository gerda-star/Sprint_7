package ru.praktikum.qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import ru.praktikum.qa_scooter.CourierHttpClient;
import ru.praktikum.qa_scooter.pojo.CourierDTO;

import org.apache.commons.lang3.RandomStringUtils;

import static ru.praktikum.qa_scooter.config.ApiConfig.API_URL;

public class CourierBaseTest {

    protected final CourierHttpClient courierHttpClient = new CourierHttpClient(API_URL);
    protected CourierDTO courierDTO;
    protected ValidatableResponse responseCreate;


    @Before
    public void DataPreparing() {
        courierDTO =  generateCourier();
    }


    @Step("Формируем данные для создания курьера")
    public CourierDTO generateCourier() {
        String login = RandomStringUtils.randomAlphanumeric(9);;
        String password = RandomStringUtils.randomAlphanumeric(4);;
        String firstName = "saske";
        return new CourierDTO(login, password, firstName);
    }

    @Step("Создаем курьера и запоминаем id")
    public void createCourierWithId(CourierDTO courierDTO) {
        courierHttpClient.createCourier(courierDTO);
        courierDTO.setId(courierHttpClient.login(courierDTO).extract().path("id").toString());

    }

    @Step
    public void deleteCourier(CourierDTO courierDTO, ValidatableResponse response) {
        if (response.extract().statusCode() == 201) {
            String id = courierHttpClient.login(courierDTO).extract().path("id").toString();
            courierHttpClient.deleteCourier(id);
        }
    }
}



