package createcouriertests;

import datafortests.CreateCourier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierWithoutFirstNameTest {

    public String login = "PlankMax";
    public String password = "Kuri12!@";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Создание курьера без обязательного параметра: имя. Негативный") // имя теста
    @Description("Тест проверяет ручку '/api/v1/courier', передает в теле логин и пароль без имени")
    @Test
    public void createCourierWithoutFirstNameSuccessfullyTest() {

        Response response = sendPOSTRequestCourierRegistration();
        compareCreateCourierStatusRs(response, 400);
        compareCreateCourierBodyRs(response, "Недостаточно данных для создания учетной записи");

    }

    @Step("Отправить POST-запрос '/api/v1/courier' для регистрации курьера")
    public Response sendPOSTRequestCourierRegistration() {
        CreateCourier createCourier = new CreateCourier();
        Response response = createCourier.getCreateCourier(login, password, null);
        return response;
    }

    @Step("Сравнить статус ответа")
    public void compareCreateCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }

    @Step("Сравнить тело ответа")
    public void compareCreateCourierBodyRs(Response response, String message) {
        response.then().assertThat().body("message", equalTo(message));
    }

}
