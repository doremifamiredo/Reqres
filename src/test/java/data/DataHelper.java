package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    public static String generateEmail() {
        String[] email = new String[]{"george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in",
                "eve.holt@reqres.in", "charles.morris@reqres.in", "tracey.ramos@reqres.in"};
        return email[new Random().nextInt(email.length)];
    }

    public static AuthInfo getAuthIfoWithTestData() {
        return new AuthInfo(generateEmail(), faker.internet().password());
    }

    @Value
    public static class AuthInfo {
        public String email;
        public String password;
    }

    public static OnlyEmail getOnlyEmail() {
        return new OnlyEmail(generateEmail());
    }

    @Value
    public static class OnlyEmail {
        public String mail;
    }

    @Data
    public static class User {
        public String id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }

    @Data
    public static class Response {
        public int page;
        public int per_page;
        public int total;
        public int total_pages;
        public List<User> data;
        @JsonIgnore
        public String support;
    }

    @Value
    public static class UpdateInfo {
        public String name;
        public String job;
    }

    public static UpdateInfo getUpdateInfo() {
        return new UpdateInfo(faker.name().firstName(), faker.job().field());
    }
}
