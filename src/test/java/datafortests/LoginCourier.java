package datafortests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class LoginCourier {

    public Response loginCourier(String login, String password) {

        LoginCourierRq loginCourierRq = new LoginCourierRq(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login");
        return response;

    }

}
