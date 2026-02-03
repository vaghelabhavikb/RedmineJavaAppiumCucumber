package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false, features = "src/test/resources/Features", monochrome = true,
    plugin = { "pretty", "html:Result/cucumber.html" }, glue = { "tests" },
    snippets = SnippetType.CAMELCASE
    , tags = "@IP"
    )
public class JUnitRunner {
	
	

}
