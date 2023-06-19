package pe.edu.upc.testingapirest.jsonserver.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pe.edu.upc.testingapirest.jsonserver.schema.PostPostRequest;
import pe.edu.upc.testingapirest.jsonserver.schema.PostPostResponse;

/*@CucumberContextConfiguration
@SpringBootTest*/
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostStepdefs {
  @Test
  void contextLoads() {
  }

  @Autowired
  private TestRestTemplate testRestTemplate;
  private String endpointPath;
  private ResponseEntity<String> responseEntity;

  @Given("The Endpoint {string} is available")
  public void theEndpointIsAvailable(String endpointPath) {
    this.endpointPath = endpointPath;
  }

  @When("A Post Request is sent with values {string}, {string}")
  public void aPostRequestIsSentWithValues(String title, String author) {
    PostPostRequest postRequest = new PostPostRequest().withTitle(title).withAuthor(author);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<PostPostRequest> request = new HttpEntity<>(postRequest, headers);
    responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
  }

  @Then("A Response is received with Status {int}")
  public void aResponseIsReceivedWithStatus(int expectedStatusCode) {
    int actualStatusCode = responseEntity.getStatusCode().value();
    Assertions.assertEquals(expectedStatusCode, actualStatusCode);
  }

  @And("An Post Response is included in Response Body, with values id, {string}, {string}")
  public void anPostResponseIsIncludedInResponseBodyWithValuesId(String title, String author) {
    PostPostResponse expectedPostResponse = new PostPostResponse()
        .withTitle(title).withAuthor(author);
    String value = responseEntity.getBody();
    PostPostResponse actualPostResponse;
    ObjectMapper mapper = new ObjectMapper();
    try {
      actualPostResponse = mapper.readValue(value, PostPostResponse.class);

    } catch (JsonProcessingException | NullPointerException e) {
      actualPostResponse = new PostPostResponse();
    }
    expectedPostResponse.setId(actualPostResponse.getId());
    Assertions.assertEquals(expectedPostResponse.getId(), actualPostResponse.getId());
    Assertions.assertEquals(expectedPostResponse.getTitle(), actualPostResponse.getTitle());
    Assertions.assertEquals(expectedPostResponse.getAuthor(), actualPostResponse.getAuthor());
  }
}
