import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LessonTenth {
    @Test
    public void setup() {


        HashMap<String, Object> jsonObject = new HashMap<>();
        HashMap<String, Object> methodProperties = new HashMap<>();

        methodProperties.put("Page", "1");
        methodProperties.put("Warehouse", "1");
        methodProperties.put("FindByString", "Львів");
        methodProperties.put("Limit", "20");

        jsonObject.put("apiKey", "def7e7bd2304d0aa8c0a788f596985b6");
        jsonObject.put("modelName", "Address");
        jsonObject.put("calledMethod", "getSettlements");
        jsonObject.put("methodProperties", methodProperties);

        given()
                .header("content-type", "application/json")
                .body(jsonObject).log().body()
                .when()
                .get("https://api.novaposhta.ua/v2.0/json/")
                .then().log().all();
    }
}