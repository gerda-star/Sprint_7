package ru.praktikum.qa_scooter;

public class OrderHttpClient extends BaseHttpClient{
    private final String url;

    public OrderHttpClient(String url) {
        super();
        this.url = url + '';
    }
}
