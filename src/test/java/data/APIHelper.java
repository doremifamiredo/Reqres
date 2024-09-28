package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;

import java.util.*;

import static io.restassured.RestAssured.given;
import static data.DataHelper.User;

public class APIHelper {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private APIHelper() {
    }

    public static void register(DataHelper.AuthInfo user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when().log().body()
                .post("/api/register")
                .then().log().body()
                .statusCode(200);
    }

    public static String register(DataHelper.OnlyEmail email) {
        return given()
                .spec(requestSpec)
                .body(email)
                .when()
                .post("/api/register")
                .then().log().body()
                .statusCode(400)
                .extract().path("error");
    }

    public static ArrayList<User> listUsers() {
        ArrayList<User> allUsers = new ArrayList<User>();
        //for (int i = 1; i < 3; i++) {
        int i = 0;
        int totalPages;
        do {
            i++;
            DataHelper.Response response = given()
                    .spec(requestSpec)
                    .when().log().body()
                    .get("/api/users?page=" + i)
                    .then().log().body()
                    .statusCode(200)
                    .extract()
                    .body().as(DataHelper.Response.class);
            allUsers.addAll(response.data);
            totalPages = response.total_pages;
        } while (i != totalPages);
        return allUsers;
    }


public static void deleteUser(int id) {
    given()
            .spec(requestSpec)
            .when().log().body()
            .delete("/api/users/" + id)
            .then().log().body()
            .statusCode(204);
}

 public static String updateUsers(int id, DataHelper.UpdateInfo updateInfo) {
     return given()
             .spec(requestSpec)
             .body(updateInfo)
             .when()
             .patch("/api/users/" + id)
             .then().log().body()
             .statusCode(200)
             .extract().path("updatedAt");
 }
}
