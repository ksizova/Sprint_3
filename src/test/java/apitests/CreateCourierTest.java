package apitests;

import datafortests.CreateCourierRq;
import datafortests.DeleteCourierRq;
import datafortests.LoginCourierRq;
import datafortests.LoginCourierRs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;


public class CreateCourierTest {
    //Создаем тестовые данные
    public String login = "Iiiiiiiiiiiivan";
    public String password = "Ivan12!@";
    public String firstName = "Иван";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    //Успешное создание курьера
    @Test
    public void createCourierSuccessfullyTest() {
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response response =
                given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(courier)
                    .when()
                    .post("/api/v1/courier");
        response.then().statusCode(201);
        response.then().assertThat().body("ok", equalTo(true));
    }

    @After
    public void deleteCourier() {
        int id;
        LoginCourierRq loginCourierRq = new LoginCourierRq(login, password);
        LoginCourierRs loginCourier =
        given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierRq)
                .when()
                .post("/api/v1/courier/login")
                .body().as(LoginCourierRs.class);
        id = loginCourier.getId();

        DeleteCourierRq deleteCourierRq = new DeleteCourierRq(id);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(deleteCourierRq)
                        .when()
                        .delete("/api/v1/courier/:id");
        response.then().assertThat().statusCode(200);
    }

    //Создание дубля курьера (одинаковый login)
    @Before
    public void setUpTwo () {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
    public void createCourierSuccessfully() {
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response response =
                 given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(201);
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    public void createDoubleCourierSuccessfullyTest() {
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(409);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется"));
    }

    //Создание курьера без обязательного параметра - логин
    @Test
    public void createCourierWithoutLoginTest() {
        CreateCourierRq courier = new CreateCourierRq(null, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    //Создание курьера без обязательного параметра - пароль
    @Test
    public void createCourierWithoutPasswordTest() {
        CreateCourierRq courier = new CreateCourierRq(login, null, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    //Создание курьера без обязательного параметра - имя
    @Test
    public void createCourierWithoutFirstNameTest() {
        CreateCourierRq courier = new CreateCourierRq(login, password, null);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
