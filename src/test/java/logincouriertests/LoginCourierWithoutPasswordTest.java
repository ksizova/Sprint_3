package logincouriertests;

import datafortests.CreateCourier;
import datafortests.DeleteCourier;
import datafortests.GetCourierID;
import datafortests.LoginCourier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginCourierWithoutPasswordTest {

    public String login = "Ensh";
    public String password = "Ensh12!@";
    public String firstName = "Эйнштейн";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Логин не существующего. Негативный") // имя теста
    @Description("Логиен курьера с несуществующими данными: логин и пароль")
    @Test
    public void loginCourierWithoutPasswordSuccessfullyTest() {

        Response createResponse = sendPOSTRequestCourierRegistration();
        compareCreateCourierStatusRs(createResponse, 201);
        compareCreateCourierBodyRs(createResponse, true);
        Response loginCourier = sendPOSTRequestCourierLogin();
        compareLoginCourierStatusRs(loginCourier, 400);
        compareLoginCourierBodyRs(loginCourier, "Недостаточно данных для входа");
        Response deleteResponce = sendDeleteRequestCourier();
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

    @Step("Отправка POST-запроса '/api/v1/courier/login' для логина курьера")
    public Response sendPOSTRequestCourierLogin() {
        LoginCourier loginCourier = new LoginCourier();
        Response response = loginCourier.loginCourier(login, null);
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareLoginCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }

    @Step("Сравнить тело ответа")
    public void compareLoginCourierBodyRs(Response response, String message) {
        response.then().assertThat().body("message", equalTo(message));
    }

    @Step("Удалить курьера")
    public Response sendDeleteRequestCourier() {
        GetCourierID getCourierID = new GetCourierID();
        String id = getCourierID.getCourierID(login, password);
        DeleteCourier deleteCourier = new DeleteCourier();
        Response response = deleteCourier.deleteCourier(login, password, id);
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareDeleteCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }

}
