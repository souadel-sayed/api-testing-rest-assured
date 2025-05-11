import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePostTest {

    @Test
    public void testUpdatePost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"updated title\",\n" +
                "  \"body\": \"updated body\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"))
                .body("userId", equalTo(1));
    }
}
