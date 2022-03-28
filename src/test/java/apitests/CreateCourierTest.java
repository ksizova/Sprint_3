package apitests;

import datafortests.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;


public class CreateCourierTest {
    /*

    //Создаем тестовые данные
    public String login = "KuriMaria";
    public String password = "Kuri12!@";
    public String firstName = "Кюри";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }



    //Успешное создание курьера
    @Test
    public void createCourierSuccessfullyTest() {
        //Создаем курьера (регистрируемся)
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
    }


    //Удаляем тестовые данные
    @Test
    public void deleteCourierSuccessfullyTest() {

        GetCourierID courierID = new GetCourierID();
        String id = courierID.getCourierID(login, password);
        DeleteCourierRq deleteCourierRq = new DeleteCourierRq(id);
        Response deleteCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(deleteCourierRq)
                        .when()
                        .delete("api/v1/courier/" + id);
        deleteCourier.then().statusCode(200);
    }
    //Создание курьера без обязательного параметра - логин
    @Test
    public void createCourierWithoutLoginSuccessfullyTest() {
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
    public void createCourierWithoutPasswordSuccessfullyTest() {
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
    public void createCourierWithoutFirstNameSuccessfullyTest() {
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


    //Создание дубля курьера (одинаковый login)
    @Test
    public void createDoubleCourierSuccessfullyTest() {
        //Создание курьера
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response responseCreateFirst =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        responseCreateFirst.then().statusCode(201);
        responseCreateFirst.then().assertThat().body("ok", equalTo(true));

        //Создание дубля курьера
        Response responseCreateDouble =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        responseCreateDouble.then().statusCode(409);
        responseCreateDouble.then().assertThat().body("message", equalTo("Этот логин уже используется"));
    }


     */

}
