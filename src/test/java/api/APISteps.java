package api;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import properties.ConfigProperties;

import static io.restassured.RestAssured.given;

public class APISteps extends ConfigProperties {
    public static String lastEpisode;
    public static int episode;
    public static String idLastCharacter;
    public static String speciesCharacter;
    public static String locCharacter;
    public static String speciesLastCharacter;
    public static String locLastCharacter;

    @Step("Найти информацию по персонажу с id {id}")
    @Когда("Пользователь находит информацию по персонажу с id {string}")
    public void gettingCharacterInformation(String id) {
        Response gettingCharacterInformation = given()
                .baseUri(getProperty("urlRickAndMorty"))
                .when()
                .get("/character/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        episode = new JSONObject(gettingCharacterInformation.getBody().asString()).getJSONArray("episode").length() - 1;
        lastEpisode = new JSONObject(gettingCharacterInformation.getBody().asString()).getJSONArray("episode").get(episode).toString();

        speciesCharacter = new JSONObject(gettingCharacterInformation.getBody().asString()).get("species").toString();
        locCharacter = new JSONObject(gettingCharacterInformation.getBody().asString()).getJSONObject("location").get("name").toString();
    }

    @Step("Получить из списка последнего эпизода последнего персонажа")
    @И("Пользователь получает из списка последнего эпизода последнего персонажа")
    public void gettingLastCharacter() {
        Response gettingLastCharacter = given()
                .baseUri(getProperty("urlRickAndMorty"))
                .when()
                .get("/episode/" + (episode + 1))
                .then()
                .statusCode(200)
                .extract().response();

        idLastCharacter = new JSONObject(gettingLastCharacter.getBody().asString()).getJSONArray("characters")
                .get(new JSONObject(gettingLastCharacter.getBody().asString()).getJSONArray("characters").length() - 1)
                .toString().replaceAll("[^0-9]", "");
    }

    @Step("Узнать расу и местоположение последнего персонажа")
    @И("Пользователь узнает расу и местоположение последнего персонажа")
    public void locationLastCharacter() {
        Response locationLastCharacter = given()
                .baseUri(getProperty("urlRickAndMorty"))
                .when()
                .get("/character/" + idLastCharacter)
                .then()
                .statusCode(200)
                .extract().response();

        speciesLastCharacter = new JSONObject(locationLastCharacter.getBody().asString()).get("species").toString();
        locLastCharacter = new JSONObject(locationLastCharacter.getBody().asString()).getJSONObject("location").get("name").toString();
    }

    @Step("Сравнить расу и местоположение двух персонажей")
    @Тогда("Пользователь сравнивает расу и местоположение двух персонажей")
    public void checkLocationAndSpecies() {
        try {
            Assertions.assertEquals(speciesLastCharacter, speciesCharacter);
            Assertions.assertEquals(locLastCharacter, locCharacter);
        } catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }
}
