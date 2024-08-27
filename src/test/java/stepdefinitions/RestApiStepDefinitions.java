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

    @Given("the API base URL is {string}")
    public void theApiBaseUrlIs(String baseUrl) {
        apiUrl = baseUrl;
    }

  /*  @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) throws IOException {
        String fullUrl = apiUrl + endpoint;
        Request request = new Request.Builder()
                .url(fullUrl)
                .build();

        response = httpClient.newCall(request).execute();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.code());
    }

    @Then("the response should contain {string}")
    public void theResponseShouldContain(String expectedText) throws IOException {
        String responseBody = response.body().string();
        assertTrue(responseBody.contains(expectedText));
    }

    @After
    public void closeResponse() throws IOException {
        if (response != null) {
            response.close();
        }
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() throws IOException {
       *//* String payload = "";
        Request request = new Request.Builder()
                .url("https://x.com/i/api/1.1/jot/client_event.json")
                .header("authorization","Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA").build();


        String baseUrl = "https://x.com/i/api/1.1/jot";
        String endpoint = "/client_event.json";
        String requestBody = "{\"description\":\"rweb:urt:notifications:fetch_Top:success\",\"product\":\"rweb\",\"duration_ms\":310},{\"description\":\"rweb:urt:notifications:fetch_Top:format:success\",\"product\":\"rweb\",\"duration_ms\":310}";
        Request.Builder requestBuilder = new Request.Builder()
                .url(baseUrl + endpoint)
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody));

        Request request = requestBuilder.build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        int statusCode = response.code();
        System.out.println("Status Code: " + response.body());

        String responseBody = response.body().string();
        System.out.println("Response Body: " + responseBody);


    }

    @Given("api BASE url IS {string}")
    public void apiBASEUrlIS(String baseUrl) {
        apiUrl= baseUrl;
    }

    @When("user sends POST request to {string}")
    public void userSendsPOSTRequestTo(String endpoint) throws IOException {
        String fullUrl = apiUrl + endpoint;
        Request request = new Request.Builder()
                .url(fullUrl)
                .build();
        response = httpClient.newCall(request).execute();
    }*/

    @Then("verifies response status code as {int}")
    public void verifiesResponseStatusCodeAs(int statusCode) {
        System.out.println("Response Body: " + response.getBody());
        System.out.println("Response Status Code: " + response.statusCode());
        assertEquals(statusCode, response.statusCode());
    }

    /*@When("user sends PUT request to {string}")
    public void userSendsPUTRequestTo(String endpoint) throws IOException {
        String fullUrl = apiUrl + endpoint;
        Request request = new Request.Builder()
                .url(fullUrl)
                .build();
        response = httpClient.newCall(request).execute();
    }*//*
*/
    @When("user sends POST request for the endpoint {string}")
    public void userSendsPOSTRequestForTheEndpoint(String endpoint) throws IOException {

        String filePathPayload = "src/test/resources/payload_new.json";
        String filePathHeader = "src/test/resources/headers.properties";
        Map<String, String> headers = loadHeadersFromFile(filePathHeader);
        String fileContent;
        try (FileReader reader = new FileReader(filePathPayload)) {
            JSONParser jsonparser = new JSONParser();
            fileContent = jsonparser.parse(reader).toString();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String fullUrl = apiUrl+endpoint;
        response = RestAssured.given()
                .baseUri(apiUrl)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(fileContent)
                .post(endpoint);

        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);

        String responseBody = response.print();
        System.out.println("Response Body: " + responseBody);

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
}
