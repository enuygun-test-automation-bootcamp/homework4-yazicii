import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class UnirestTest {

    String id = "4";
    String petId = "2";
    String quantity = "3";

    String body = "{\r\n  \"id\":"+ id +",\r\n  \"petId\":" + petId + ",\r\n  \"quantity\":" + quantity + ",\r\n  \"shipDate\": \"2022-07-30T22:30:06.212Z\",\r\n  \"status\": \"placed\",\r\n  \"complete\": true\r\n}";

    @Test
    @Order(1)
    public void getInventoryTest() throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://petstore.swagger.io/v2/store/inventory")
                .asString();

        Assertions.assertEquals(200,response.getStatus());
    }

    @Test
    @Order(2)
    public void PostOrderTest()throws UnirestException {
        String baseURI = "https://petstore.swagger.io/v2/store/order";
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post(baseURI)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asString();

        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    @Order(3)
    public void getOrderByIdTest() throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://petstore.swagger.io/v2/store/order/" + id)
                .asString();

        Assertions.assertEquals(200,response.getStatus());
    }

    @Test
    @Order(4)
    public void delOrderByIdTest() throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.delete("https://petstore.swagger.io/v2/store/order/" + id)
                .asString();

        Assertions.assertEquals(200,response.getStatus());
    }

}
