package test.java.Lesson_8;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestMethodsTest {

    @Test
    public void getRequestTest() {
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
        .when()
                .get("/get")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void postRawTextTest() {
        String data = "{\"test\": \"value\"}";

        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType(ContentType.TEXT)
                .body(data)
        .when()
                .post("/post")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(data));
    }

    @Test
    public void postFormDataTest() {
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
        .when()
                .post("/post")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void putRequestTest() {
        String data = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType(ContentType.TEXT)
                .body(data)
        .when()
                .put("/put")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(data));
    }

    @Test
    public void patchRequestTest() {
        String data = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType(ContentType.TEXT)
                .body(data)
        .when()
                .patch("/patch")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(data));
    }

    @Test
    public void deleteRequestTest() {
        String data = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType(ContentType.TEXT)
                .body(data)
        .when()
                .delete("/delete")
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(data));
    }
}