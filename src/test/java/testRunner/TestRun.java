package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
     //   features=".//Features//Login.feature",
        features=".//Features//Customers.feature",
     //     features=".//Features/",
    //      features={".//Features//Login.feature",".//Features//Customers.feature"},
        glue="stepDefinitions",
 //      tags={"@Sanity"},  //To execute Sanity scenarios
  //     tags={"@regression"},  //To execute regression scenarios
  //     tags={"@Sanity,@regression"},  //To execute both Sanity regression scenarios
 //   		   tags={"@Sanity,@regression"},  //To execute scenthose have both Sanity and Regression tags those are rarely used
        dryRun=false,
        monochrome=true,
        plugin= {"pretty","html:Reports"}
)
public class TestRun {
}
