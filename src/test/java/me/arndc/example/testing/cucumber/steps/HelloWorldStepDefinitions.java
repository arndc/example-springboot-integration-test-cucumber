package me.arndc.example.testing.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private String greeterName;
    private ResponseEntity<String> helloResponse;
    private boolean isForbidden;

    @Given("^the name of the greeter is (.*)$")
    public void greetersName(final String name) {
        greeterName = name;
    }

    @When("^greeting the world$")
    public void greetTheWorld() {
        final UriBuilder uriBuilder = UriComponentsBuilder.fromUriString("/hello");
        if (greeterName != null) uriBuilder.queryParam("greeter", greeterName);
        if (isForbidden) uriBuilder.queryParam("allowed", false);

        helloResponse = restTemplate.getForEntity(uriBuilder.build(), String.class);
    }

    @Then("^the status code is (\\d+)$")
    public void verifyStatusCode(int statusCode) {
        assertThat(helloResponse.getStatusCodeValue()).isEqualTo(statusCode);
    }

    @And("^the world is greeted by (.*)$")
    public void verifyTheWorldIsGreeted(final String name) {
        assertThat(helloResponse.getBody()).isEqualTo("Hello World! My name is " + name + ".");
    }

    @But("^the user is not allowed$")
    public void userIsForbidden() {
        isForbidden = true;
    }
}
