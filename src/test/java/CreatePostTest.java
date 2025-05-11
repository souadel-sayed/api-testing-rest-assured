import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatePostTest {

    @Test
    public void testCreatePost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\n" +
                "  \"title\": \"new post\",\n" +
                "  \"body\": \"now add new post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)  // نتوقع استجابة 201 لأن المنشور تم إنشاؤه بنجاح
                .body("title", equalTo("new post"))
                .body("body", equalTo("now add new post"))
                .body("userId", equalTo(1));
    }
}
