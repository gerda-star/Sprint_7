package ru.praktikum.qa_scooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class BaseHttpClient {

    public ValidatableResponse doPostRequest(String url, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(url).then();
    }
    public ValidatableResponse doGetRequest(String url) {
        return given(baseRequest()).get(url).then();
    }
    public ValidatableResponse doDeleteRequest(String url) {
        RequestSpecification request = given(baseRequest());
        return request.delete(url).then();
    }
    public  ValidatableResponse doPutRequest(String url){
        return given(baseRequest()).put(url).then();
    }


    private RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
