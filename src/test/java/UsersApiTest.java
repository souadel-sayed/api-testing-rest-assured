import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
public class UsersApiTest {

    @Test
    public void testGetAllUsers() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", is(10))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].username", notNullValue())
                .body("[0].email", notNullValue())
                .body("[0].address", notNullValue());

    }

    @Test
    public void testUserFieldsValidationWithTypes() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<Map<String, Object>> users = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("");

        for (Map<String, Object> user : users) {
            // Type checks
            assertThat(user.get("id"), instanceOf(Integer.class));
            assertThat(user.get("name"), instanceOf(String.class));
            assertThat(user.get("username"), instanceOf(String.class));
            assertThat(user.get("email"), instanceOf(String.class));
            assertThat(user.get("address"), instanceOf(Map.class));

            // Email format
            String email = (String) user.get("email");
            assertThat("Invalid email: " + email, email, matchesPattern(".+@.+\\..+"));

            // Phone format
            String phone = (String) user.get("phone");
            assertThat("Invalid phone: " + phone, phone, matchesPattern("[\\d\\s().\\-xext]+"));

            // Zipcode format inside address
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            String zipcode = (String) address.get("zipcode");
            assertThat("Invalid zipcode: " + zipcode, zipcode, matchesPattern("^\\d{5}(-\\d{4})?$"));
            assertThat("Username is empty", user.get("username").toString(), not(isEmptyOrNullString()));

            Integer id = (Integer) user.get("id");
            assertThat("User ID should be positive", id, greaterThan(0));

            String website = user.get("website").toString();
            assertThat("Website is not valid: " + website, website, matchesPattern(".*\\.(com|org|net|info|biz|io)$"));

            Map<String, Object> company = (Map<String, Object>) user.get("company");
            assertThat("Company name is empty", company.get("name").toString(), not(isEmptyOrNullString()));
            Map<String, Object> geo = (Map<String, Object>) address.get("geo");
            String lat = geo.get("lat").toString();
            String lng = geo.get("lng").toString();
            assertThat("Latitude is invalid", lat, matchesPattern("-?\\d+(\\.\\d+)?"));
            assertThat("Longitude is invalid", lng, matchesPattern("-?\\d+(\\.\\d+)?"));

        }
    }

}