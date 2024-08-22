package stepdefinitions;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.junit.After;

import io.cucumber.java.en.*;

//import cucumber.api.java.After;

import static org.junit.Assert.*;

public class RestApiStepDefinitions {

    private final OkHttpClient httpClient = new OkHttpClient();

    private String apiUrl;
    private Response response;

    @Given("the API base URL is {string}")
    public void theApiBaseUrlIs(String baseUrl) {
        apiUrl = baseUrl;
    }

    @When("I send a GET request to {string}")
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
}
