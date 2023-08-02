package ru.praktikum.qa_scooter.pojo;

public class CourierDTO {
    private String login;
    private String password;
    private String  firstName;
    private String id;

    public CourierDTO(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public CourierDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
