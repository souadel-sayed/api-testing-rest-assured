import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class FeatureTest {

    @Test
    public void testComments() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> comments = given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        assertThat("Number of comments should be 500", comments.size(), is(500));

        for (Map<String, Object> comment : comments) {
            assertThat(comment.get("postId"), notNullValue());
            assertThat(comment.get("id"), notNullValue());
            assertThat(comment.get("name"), notNullValue());
            assertThat(comment.get("email"), notNullValue());
            assertThat(comment.get("body"), notNullValue());
            assertThat(comment.get("body").toString(), not(isEmptyOrNullString()));
        }
    }

    @Test
    public void testAlbums() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> albums = given()
                .when()
                .get("/albums")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        assertThat("Number of albums should be 100", albums.size(), is(100));

        for (Map<String, Object> album : albums) {
            assertThat(album.get("userId"), notNullValue());
            assertThat(album.get("id"), notNullValue());
            assertThat(album.get("title"), notNullValue());
            assertThat(album.get("title").toString(), not(isEmptyOrNullString()));
        }
    }

    @Test
    public void testPhotos() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> photos = given()
                .when()
                .get("/photos")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        assertThat("Number of photos should be 5000", photos.size(), is(5000));

        for (Map<String, Object> photo : photos) {
            assertThat(photo.get("albumId"), notNullValue());
            assertThat(photo.get("id"), notNullValue());
            assertThat(photo.get("title"), notNullValue());
            assertThat(photo.get("url"), notNullValue());
            assertThat(photo.get("thumbnailUrl"), notNullValue());
        }
    }

    @Test
    public void testTodos() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> todos = given()
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        assertThat("Number of todos should be 200", todos.size(), is(200));

        for (Map<String, Object> todo : todos) {
            assertThat(todo.get("userId"), notNullValue());
            assertThat(todo.get("id"), notNullValue());
            assertThat(todo.get("title"), notNullValue());
            assertThat(todo.get("completed"), notNullValue());
        }
    }
}
