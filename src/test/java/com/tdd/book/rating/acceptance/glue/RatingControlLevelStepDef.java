package com.tdd.book.rating.acceptance.glue;

import com.tdd.book.rating.RatingControlServiceApplication;
import com.tdd.book.rating.acceptance.fixtures.BookServiceFixture;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RatingControlServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureWireMock(port = 9999)
public class RatingControlLevelStepDef implements En {

    private String customerSetControlLevel;
    private ResponseEntity<Boolean> responseEntity;

    @Autowired
    private TestRestTemplate restTemplate;

    public RatingControlLevelStepDef() {
        Given("^I am a customer with a set rating control level of (.*)$", (String customerSetControlLevel) -> {
            this.customerSetControlLevel = customerSetControlLevel;
        });

        When("^I request to read an equal level book (.*)$", (String bookId) -> {
            BookServiceFixture.stubBookServiceResponseForBook_B1234_Rating12(bookId);
            HttpEntity httpEntity = new HttpEntity(generateHeader());
            responseEntity = restTemplate.exchange("/rcl/book/v1/read/eligibility/" + this.customerSetControlLevel + "/" + bookId,
                    HttpMethod.GET, httpEntity, Boolean.class);
        });

        Then("^I should get decision to read the book$", () -> {
            assertThat("responseEntity status code is not 200", responseEntity.getStatusCode(), is(HttpStatus.OK));
            assertThat("responseEntity body is not true", responseEntity.getBody(), is(Boolean.TRUE));
        });

        When("I request to read higher level book (.*)$", (String bookId) -> {
            BookServiceFixture.stubBookServiceResponseForBook_BH1234_Rating15(bookId);
            HttpEntity httpEntity = new HttpEntity(generateHeader());
            responseEntity = restTemplate.exchange("/rcl/book/v1/read/eligibility/" + this.customerSetControlLevel + "/" + bookId,
                    HttpMethod.GET, httpEntity, Boolean.class);
        });

        Then("I get decision not to read the book", () -> {
            assertThat("responseEntity status code is not 200", responseEntity.getStatusCodeValue(), is(200));
            assertThat("Book Read Eligibility is true", responseEntity.getBody(), is(Boolean.FALSE));
        });
    }

    private MultiValueMap<String, String> generateHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        return headers;
    }
}
