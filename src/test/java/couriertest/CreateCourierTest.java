package couriertest;

import datafortests.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateCourierTest {

    //Создаем тестовые данные
    public String login = "KuriMaria";
    public String password = "Kuri12!@";
    public String firstName = "Кюри";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Создание и удаление курьера: позитивный") // имя теста
    @Description("Тест построен по принципу end-to-end: успешное создание, получение ID, удаление курьера")
    @Test
    public void createCourierTest() {
        Response createResponse = sendPOSTRequestCourierRegistration();
        compareCreateCourierStatusRs(createResponse, 201);
        compareCreateCourierBodyRs(createResponse, true);
        getID();
        Response deleteResponce = deleteCourier();
        compareDeleteCourierStatusRs(deleteResponce, 200);
    }

    @Step("Отправить POST-запрос '/api/v1/courier' для регистрации курьера")
    public Response sendPOSTRequestCourierRegistration() {
        CreateCourier createCourier = new CreateCourier();
        Response response = createCourier.getCreateCourier(login, password, firstName);
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareCreateCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }

    @Step("Сравнить тело ответа")
    public void compareCreateCourierBodyRs(Response response, boolean ok) {
        response.then().assertThat().body("ok", equalTo(ok));
    }

    @Step("Получить ID клиента")
    public String getID() {
        GetCourierID getCourierID = new GetCourierID();
        String id = getCourierID.getCourierID(login, password);
        return id;
    }

    @Step("Удалить курьера")
    public Response deleteCourier() {
        DeleteCourier deleteCourier = new DeleteCourier();
        Response response = deleteCourier.deleteCourier(login, password, getID());
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareDeleteCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }
    
}
