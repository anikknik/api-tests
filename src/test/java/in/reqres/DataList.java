package in.reqres;

public class DataList {

    public static String
            loginUrl = "/api/login",
            listUsers = "api/users?page=2",
            usersUrl = "/api/users",
            users2Url = "/api/users/2",
            createUserBody = "{\"name\": \"morpheus\",     \"job\": \"leader\" }",
            updateUserBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}",
            loginBody = "{ \"email\": \"eve.holt@reqres.in\", " +
                    "\"password\": \"cityslicka\" }",
            missingPasswordBody = "{ \"email\": \"eve.holt@reqres.in\"}",
            error = "Missing password";
}
