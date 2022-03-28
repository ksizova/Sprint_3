package datafortests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateCourier {

    public Response getCreateCourier(String login, String password, String firstName) {
        CreateCourierRq courier = new CreateCourierRq(login, password, firstName);
        Response createCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        return createCourier;
    }

}
