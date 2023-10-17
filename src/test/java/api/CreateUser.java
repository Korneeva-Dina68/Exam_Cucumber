package api;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import properties.ConfigProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateUser extends ConfigProperties {

    public static String name;
    public static String job;

    @Step("Создать запрос для создания юзера")
    @Когда("Пользователь создает запрос")
    public void createUser() throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/api.json"))));
        body.put("name", "Tomato");
        body.put("job", "Eat maket");

        Response gettingCreateUser = given()
                .body(body.toString())
                .when()
                .post(getProperty("urlCreateUser"))
                .then()
                .statusCode(201)
                .extract().response();
        name = new JSONObject(gettingCreateUser.getBody().asString()).get("name").toString();
        job = new JSONObject(gettingCreateUser.getBody().asString()).get("job").toString();
    }

    @Step("Проверка валидности данных в ответе на запрос")
    @Тогда("Пользователь проверяет валидность данных в ответе на запрос")
    public void checkingTheResponseToTheRequest() {
        Assertions.assertEquals(name, "Tomato");
        Assertions.assertEquals(job, "Eat maket");
    }
}

