package fr.lidonis.toudou.acceptance.server

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    glue = ["fr.lidonis.toudou.acceptance.server"],
    features = ["src/test/resources"]
)
class RunServerCucumberTest