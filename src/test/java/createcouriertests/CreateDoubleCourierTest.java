package createcouriertests;
import datafortests.CreateCourier;
import datafortests.DeleteCourier;
import datafortests.GetCourierID;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreateDoubleCourierTest {

    public String login = "Maxwell";
    public String password = "Maxwell12!@";
    public String firstName = "Максвелл";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Создание дубля курьера. Негативный") // имя теста
    @Description("Тест построен по принципу end-to-end: успешное создание, создание дубля курьера, удаление курьера")
    @Test
    public void createDoubleCourierSuccessfullyTest() {

        Response createCourierResponse = sendPOSTRequestCourierRegistration();
        compareCreateCourierStatusRs(createCourierResponse, 201);
        compareCreateCourierBodyRs(createCourierResponse, true);
        Response createDoubleCourier = sendPOSTRequestCourierRegistration();
        compareCreateDoubleCourierStatusRs(createDoubleCourier, 409);
        compareCreateDoubleCourierBodyRs(createDoubleCourier, "Этот логин уже используется");
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

    @Step("Сравнить статус ответа")
    public void compareCreateDoubleCourierStatusRs(Response response, int status) {
        MatcherAssert.assertThat(response.statusCode(), equalTo(status));
    }

    @Step("Сравнить тело ответа")
    public void compareCreateDoubleCourierBodyRs(Response response, String message) {
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

    @After
    public void afterTest() {
        sendDeleteRequestCourier();
    }

}
