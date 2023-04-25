import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LessonTenth {

    private static final String BASE_URL = "https://api.novaposhta.ua/v2.0/json/";
    private static final String API_KEY = "def7e7bd2304d0aa8c0a788f596985b6";

    @Test
    public void testCitiesSearch() {
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("AreaRef", "00000000-0000-0000-0000-000000000000");
        methodProperties.put("Ref", "00000000-0000-0000-0000-000000000000");
        methodProperties.put("RegionRef", "00000000-0000-0000-0000-000000000000");
        methodProperties.put("Page", "1");
        methodProperties.put("Warehouse", "1");
        methodProperties.put("FindByString", "Київ");
        methodProperties.put("Limit", "20");


        given()
                .param("apiKey", API_KEY)
                .param("modelName", "Address")
                .param("calledMethod", "getSettlements")
                .params(methodProperties)
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .body("success", equalTo(true)) //в мене чомусь він фейлиться і не знаходить, я вже всю документацію там прочитав, але щось не можу знайти рішення((
                .body("data[0].MainDescription", equalTo("Київ"));
    }
}