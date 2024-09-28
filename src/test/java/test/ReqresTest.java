package test;

import data.APIHelper;
import data.DataHelper;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static data.DataHelper.User;

public class ReqresTest {

    @Test
    @DisplayName("Should successfully register")
    void shouldSuccessfulRegister() {
        var authInfo = DataHelper.getAuthIfoWithTestData();
        APIHelper.register(authInfo);
    }

    @Test
    @DisplayName("Should get status code 400 and error message if missing password")
    void shouldUnsuccessfulRegister() {
        assertEquals("Missing email or username",
                APIHelper.register(DataHelper.getOnlyEmail()));
    }

    @Test
    @DisplayName("Should get list of users and check all email ending is @reqres.in")
    void shouldGetListUsers() throws IOException {
        ArrayList<User> response = APIHelper.listUsers();
        ArrayList<User> notReqresServer = new ArrayList<User>();
        for (User user : response) {
            int cut = user.email.indexOf("@");
            if (!user.email.substring(cut).equals("@reqres.in"))
                notReqresServer.add(user);
        }
        assertEquals(0, notReqresServer.size());
    }

    @Test
    @DisplayName("Delete user")
    void shouldDeleteUser() {
        APIHelper.deleteUser(2);
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
