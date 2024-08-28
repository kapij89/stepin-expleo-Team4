package stepdefinitions;

import com.fasterxml.jackson.core.*;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import io.cucumber.java.en.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestApiStepDefinitions {

    private final OkHttpClient httpClient = new OkHttpClient();

    private String apiUrl;
    private Response response;
    public int id;
    @Given("the API base URL is {string}")
    public void theApiBaseUrlIs(String baseUrl) {
        apiUrl = baseUrl;
    }

    @Then("verifies response status code as {int}")
    public void verifiesResponseStatusCodeAs(int statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    public void postItems(String apiUrl,String endpoint)throws IOException{
        String filePathPayload = "src/test/resources/payload_new.json";
        String filePathHeader = "src/test/resources/headers_1.properties";
        Map<String, String> headers = loadHeadersFromFile(filePathHeader);
        String fileContent;
        try (FileReader reader = new FileReader(filePathPayload)) {
            JSONParser jsonparser = new JSONParser();
            fileContent = jsonparser.parse(reader).toString();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String fullUrl = apiUrl+endpoint;
        response = given()
                .baseUri(apiUrl)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(fileContent)
                .post(endpoint);

        int statusCode = response.statusCode();
        System.out.println("Status Code POST ITEMS: " + statusCode);

        String responseBody = response.print();
        System.out.println("Response Body POST ITEMS: " + responseBody);
        id = response.jsonPath().getInt("id");
        System.out.println("Id: " + id);

    }

    @When("user sends POST request for the endpoint {string}")
    public void userSendsPOSTRequestForTheEndpoint(String endpoint) throws IOException {
        postItems(apiUrl,endpoint);

    }
    private static Map<String, String> loadHeadersFromFile(String filePath) {
        Map<String, String> headers = new HashMap<>();
        try (FileReader reader = new FileReader(new File(filePath))) {
            Properties properties = new Properties();
            properties.load(reader);

            for (String key : properties.stringPropertyNames()) {
                headers.put(key, properties.getProperty(key).trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }

    @When("user sends GET request for the item {string}")
    public void userSendsGETRequestForTheEndpoint(String item) throws IOException {
        //String itemId = String.valueOf(id);
        getItems(apiUrl,item);

    }

    public void getItems(String apiUrl,String item)throws IOException{
        RestAssured.baseURI = apiUrl;
        response = given()
                .pathParam("id", item)
                .get("/items/{id}");

        int statusCode = response.statusCode();
        System.out.println("Status Code GET Items: " + statusCode);
        System.out.println("Status Code Get Items Body: " + response.getBody().asString());

    }


}
