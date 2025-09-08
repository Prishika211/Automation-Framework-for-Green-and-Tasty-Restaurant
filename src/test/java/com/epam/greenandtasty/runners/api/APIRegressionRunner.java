package com.epam.greenandtasty.runners.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features="src/test/resources/features/api",
        glue={"com.epam.greenandtasty.step_definitions.api", "com/epam/greenandtasty/hooks"},
        plugin={"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true,
        tags="@regression"
)
public final class APIRegressionRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel=true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
