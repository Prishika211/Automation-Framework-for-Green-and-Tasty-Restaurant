package com.epam.greenandtasty.runners.ui;

import com.epam.greenandtasty.listeners.UITestExecutionListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(features = "src/test/resources/features/ui",
        glue = {"com.epam.greenandtasty.stepdefinitions.ui", "com.epam" +
                ".greenandtasty.hooks",
                "com.epam" +
                        ".greenandtasty.context.ui"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@menuBrowsing"
)
@Listeners(UITestExecutionListener.class)
public class UITestRunner extends AbstractTestNGCucumberTests {
}
