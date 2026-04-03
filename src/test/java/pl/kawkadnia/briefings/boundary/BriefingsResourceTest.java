package pl.kawkadnia.briefings.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BriefingsResourceTest {

    @Test
    void getTodayReturns404WhenEmpty() {
        given()
                .when().get("/api/briefings/today")
                .then()
                .statusCode(404);
    }

    @Test
    void getByDateReturns404WhenNotFound() {
        given()
                .when().get("/api/briefings/2020-01-01")
                .then()
                .statusCode(404);
    }

    @Test
    void getDatesReturns200() {
        given()
                .when().get("/api/briefings/dates")
                .then()
                .statusCode(200);
    }
}
