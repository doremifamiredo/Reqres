package test;

import data.APIHelper;
import data.DataHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static data.DataHelper.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresTest {

    @Test
    @DisplayName("Should successfully register")
    void shouldSuccessfulRegister() {
        APIHelper.register(DataHelper.getAuthIfoWithTestData());
    }

    @Test
    @DisplayName("Should get status code 400 and error message if missing password")
    void shouldUnsuccessfulRegister() {
        assertEquals("Missing email or username",
                APIHelper.register(DataHelper.getOnlyEmail()));
    }

    @Test
    @DisplayName("Should get list of users and check all email ending is @reqres.in")
    void shouldGetListUsers() {
        ArrayList<User> allUsers = APIHelper.listUsers();
        assertTrue(allUsers.stream().allMatch(user -> user.email.contains("@reqres.in")));
    }

    @Test
    @DisplayName("Delete user")
    void shouldDeleteUser() {
        assertEquals(204, APIHelper.deleteUser(2));
    }

    @Test
    @DisplayName("Update user info")
    void pdateUserInfo() {
        String updatedAt = APIHelper.updateUsers(2, DataHelper.getUpdateInfo()).substring(0, 10);
        String today =
        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(today, updatedAt);
    }
}
