import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsApiTest {

        @Test
        public void testGetAllPosts() {
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

            given()
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .body("size()",greaterThan(0))
                    .body("[0].id", notNullValue())
                    .body("[0].userId", notNullValue())
                    .body("[0].title", notNullValue())
                    .body("[0].body", notNullValue());

        }

    @Test
    public void testPostsFieldsValidationWithTypes() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> posts = given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        assertThat(posts.size(), greaterThan(0));

        for (Map<String, Object> post : posts) {
            assertThat((Integer)post.get("id"), greaterThan(0));
            assertThat((Integer)post.get("userId"), greaterThan(0));
            assertThat(post.get("title"), notNullValue());
            assertThat(post.get("title").toString(), not(isEmptyOrNullString()));
            assertThat(post.get("body"), notNullValue());
            assertThat(post.get("body").toString(), not(isEmptyOrNullString()));
            assertThat(post.get("title"), instanceOf(String.class));
            assertThat(post.get("body"), instanceOf(String.class));
            assertThat(post.get("id"), instanceOf(Integer.class));
            assertThat(post.get("userId"), instanceOf(Integer.class));

        }

    }
    }
