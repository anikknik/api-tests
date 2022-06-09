package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static io.restassured.http.ContentType.JSON;

import static in.reqres.DataList.*;

public class ApiTests extends TestBase {

    @Test
    void getUsers() {
        given()
                .when()
                .get(listUsers)
                .then()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2));
    }

    @Test
    void createUser() {
        given()
                .body(createUserBody)
                .contentType(JSON)
                .when()
                .post(usersUrl)
                .then()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUser() {
        given()
                .body(updateUserBody)
                .contentType(JSON)
                .when()
                .put(users2Url)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void loginTest() {
        given()
                .log().uri()
                .log().body()
                .body(loginBody)
                .contentType(JSON)
                .when()
                .post(loginUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is(token));
    }

    @Test
    void missingPasswordLoginTest() {
        given()
                .log().uri()
                .log().body()
                .body(missingPasswordBody)
                .contentType(JSON)
                .when()
                .post(loginUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is(error));
    }
}

