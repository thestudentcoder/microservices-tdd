package com.tdd.book.rating.acceptance.glue;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/features/RatingControlLevel.feature",
        glue = "com.tdd.book.rating.acceptance.glue"
)
public class AcceptanceCriteriaTest {
}
