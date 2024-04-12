package org.tsukuba_bunko.ella.api;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class AuthEndpointsTest {
    
    @Test
    public void store001() {
        Map<String, String> token = new HashMap<String,String>();
        token.put("id_token", "dummy_id_token");
        token.put("access_token", "dummy_access_token");
        token.put("refresh_token", "dummy_refresh_token");

        given()
            .body(token)
            .contentType(ContentType.JSON)
        .when().post("/auth/store")
            .then()
                .statusCode(200)
                .cookie("rt", RestAssuredMatchers.detailedCookie()
                    .value(is("dummy_refresh_token"))
                    .httpOnly(true)
                    .secured(true))
                .body("id_token", is("dummy_id_token"))
                .body("access_token", is("dummy_access_token"))
                .body("refresh_token", nullValue());
    }
}
