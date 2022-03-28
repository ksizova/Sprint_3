package couriertest;

import datafortests.CreateCourier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;


public class CreateCourierWithoutPasswordTest {

    public String login = "PlankMax";
    public String firstName = "Планк";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Создание курьера без обязательного параметра: пароль") // имя теста
    @Description("Тест проверяет ручку '/api/v1/courier', передает в теле логин и имя без пароля")
    @Test
    public void createCourierWithoutPasswordSuccessfullyTest() {

        Response response = sendPOSTRequestCourierRegistration();
        compareCreateCourierStatusRs(response, 400);
        compareCreateCourierBodyRs(response, "Недостаточно данных для создания учетной записи");

    }

    @Step("Отправить POST-запрос '/api/v1/courier' для регистрации курьера")
    public Response sendPOSTRequestCourierRegistration() {
        CreateCourier createCourier = new CreateCourier();
        Response response = createCourier.getCreateCourier(login, null, firstName);
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareCreateCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), CoreMatchers.equalTo(status));
    }

    @Step("Сравнить тело ответа")
    public void compareCreateCourierBodyRs(Response response, String message) {
        response.then().assertThat().body("message", CoreMatchers.equalTo(message));
    }

}
