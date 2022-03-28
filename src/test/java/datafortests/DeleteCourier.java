package datafortests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteCourier {

    public Response deleteCourier(String login, String password,String id) {
        Response deleteCourierRs =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .delete("api/v1/courier/" + id);
        return deleteCourierRs;
    }

}
