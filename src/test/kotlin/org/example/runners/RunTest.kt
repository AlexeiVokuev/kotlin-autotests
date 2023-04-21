package org.example.runners

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["org.example.steps"],
    tags = "@runme",
    dryRun = false,
    snippets = CucumberOptions.SnippetType.UNDERSCORE
)
class RunTest 