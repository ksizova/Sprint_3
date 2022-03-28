package datafortests;

import static io.restassured.RestAssured.given;
//Метод для получения ID клиента
public class GetCourierID {

    public String getCourierID(String login, String password) {

        String id;
        LoginCourierRq loginCourierRq = new LoginCourierRq(login, password);
        LoginCourierRs loginCourier =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginCourierRq)
                        .when()
                        .post("/api/v1/courier/login")
                        .body().as(LoginCourierRs.class);
        return id = String.valueOf(loginCourier.getId());

    }
}
