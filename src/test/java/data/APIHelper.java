package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.*;

import static io.restassured.RestAssured.given;
import static data.DataHelper.User;

public class APIHelper {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final ResponseSpecification responseOK = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.BODY)
            .build();
    private static final ResponseSpecification responseDEL = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.BODY)
            .build();

    private APIHelper() {
    }

    public static void register(DataHelper.AuthInfo user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/register")
                .then().spec(responseOK);
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
        ArrayList<User> allUsers = new ArrayList<>();
        int i = 0;
        int totalPages;
        do {
            i++;
            DataHelper.Response response = given()
                    .spec(requestSpec)
                    .when().log().body()
                    .get("/api/users?page=" + i)
                    .then().spec(responseOK)
                    .extract()
                    .body().as(DataHelper.Response.class);
            allUsers.addAll(response.data);
            totalPages = response.total_pages;
        } while (i != totalPages);
        return allUsers;
    }


    public static int deleteUser(int id) {
        return given()
                .spec(requestSpec)
                .when().log().body()
                .delete("/api/users/" + id)
                .then().spec(responseDEL)
                .extract().statusCode();
    }

    public static String updateUsers(int id, DataHelper.UpdateInfo updateInfo) {
        return given()
                .spec(requestSpec)
                .body(updateInfo)
                .when()
                .patch("/api/users/" + id)
                .then().spec(responseOK)
                .extract().path("updatedAt");
    }
}
