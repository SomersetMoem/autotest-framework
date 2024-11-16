package com.teamsity.api;

import com.teamcity.api.models.User;
import com.teamcity.api.spec.Specifications;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {

    @Test
    public void buildConfigurationTest() {
        User user = User.builder()
                .username("admin")
                .password("admin")
                .build();

        String token = RestAssured.given()
                .spec(Specifications.getSpec().authSpec(user))
                .get("/autentificationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
