package test.java.Lesson_8;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestMethodsTest {

    private RequestSpecification baseRequestSpec;
    private RequestSpecification textRequestSpec;
    private ResponseSpecification textResponseSpec;
    private final String textData = "This is expected to be sent back as part of response body.";

    @BeforeEach
    public void setUp() {
        baseRequestSpec = given()
                .baseUri("https://postman-echo.com")
                .log().all();

        textRequestSpec = given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .contentType(ContentType.TEXT);

        textResponseSpec = expect()
                .statusCode(HttpStatus.SC_OK)
                .body("data", equalTo(textData));
    }

    @Test
    public void getRequestTest() {
        baseRequestSpec
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

        textRequestSpec
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
        baseRequestSpec
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
        textRequestSpec
                .body(textData)
                .expect()
                .spec(textResponseSpec)
        .when()
                .put("/put")
        .then()
                .log().body();
    }

    @Test
    public void patchRequestTest() {
        textRequestSpec
                .body(textData)
                .expect()
                .spec(textResponseSpec)
        .when()
                .patch("/patch")
        .then()
                .log().body();
    }

    @Test
    public void deleteRequestTest() {
        textRequestSpec
                .body(textData)
                .expect()
                .spec(textResponseSpec)
        .when()
                .delete("/delete")
        .then()
                .log().body();
    }
}