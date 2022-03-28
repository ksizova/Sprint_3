package logincouriertests;

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

public class LoginCourierWithBadAuthTest {

    public String login = "Hertz";
    public String password = "Hertz12!@";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Логин не существующего курьера. Негативный") // имя теста
    @Description("Логиен курьера с несуществующими данными: логин и пароль")
    @Test
    public void loginCourierWithBadDataSuccessfullyTest() {

        Response responseLogin = sendPOSTRequestCourierLogin();
        compareLoginCourierStatusRs(responseLogin,404);
        compareLoginCourierBodyRs(responseLogin, "Учетная запись не найдена");

    }

    @Step("Отправка POST-запроса '/api/v1/courier/login' для логина курьера")
    public Response sendPOSTRequestCourierLogin() {
        LoginCourier loginCourier = new LoginCourier();
        Response response = loginCourier.loginCourier(login, password);
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


}
