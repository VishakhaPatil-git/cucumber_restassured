package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features", 
		glue = "stepdefs", 
		plugin = { "pretty", "html:target/cucumber-reports" }
				,monochrome = true
		)

public class TestRunner extends AbstractTestNGCucumberTests {

}
