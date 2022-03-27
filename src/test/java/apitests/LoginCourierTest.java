package apitests;

import com.google.gson.Gson;
import datafortests.CreateCourierRq;
import datafortests.LoginCourierRq;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginCourierTest {

    public String login = "KuriMaria";
    public String password = "Kuri12!@";
    public String firstName = "Кюри";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void loginCourierSuccessfullyTest() {
        //Создаем курьера
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response createCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        createCourier.then().statusCode(201);
        createCourier.then().assertThat().body("ok", equalTo(true));

        //Курьер логинится
        LoginCourierRq loginCourierRq = new LoginCourierRq(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(200);
        response.then().assertThat().body("id", notNullValue());

/*
        Response deleteCourier =
                 given()
                         .header("Content-type", "application/json")
                         .and()
                         .body(jsonStringId)
                         .when()
                         .delete("api/v1/courier/");
        deleteCourier.then().statusCode(200);
        }

 */

    }

    //Ошибка 400 при логине клиента без обязательного поля логин
    @Test
    public void loginCourierWithoutLoginSuccessfullyTest() {
        //Создаем курьера
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response createCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        createCourier.then().statusCode(201);
        createCourier.then().assertThat().body("ok", equalTo(true));

        //Курьер логинится
        LoginCourierRq loginCourierRq = new LoginCourierRq(null, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    //Ошибка 400 при логине клиента без обязательного поля пароль
    @Test
    public void loginCourierWithoutPasswordSuccessfullyTest() {
        //Создаем курьера
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response createCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        createCourier.then().statusCode(201);
        createCourier.then().assertThat().body("ok", equalTo(true));

        //Курьер логинится
        LoginCourierRq loginCourierRq = new LoginCourierRq(login, null);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    //Логин курьера с неверными данными
    @Test
    public void loginCourierWithBadDataSuccessfullyTest() {
        LoginCourierRq loginCourierRq = new LoginCourierRq(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(404);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));

    }

}
