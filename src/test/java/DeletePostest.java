import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePostest {

    @Test
    public void testDeletePost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}
