package com.epam.greenandtasty.hooks;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.exceptions.WebDriverNotFoundException;
import com.epam.greenandtasty.ui.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.epam.greenandtasty.constants.Constant.BASE_URL_UI;
import static com.epam.greenandtasty.utils.ScreenshotsUtil.captureViewPortScreenShot;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final UIContext uiContext;

    public Hooks() throws WebDriverNotFoundException, IOException {
        uiContext = new UIContext();
    }

    @Before("@ui")
    public void setupForUi() {
        uiContext.getWebDriver().get(BASE_URL_UI);
        uiContext.getWebDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDownForUi(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario Failed ", scenario.getName());
            captureViewPortScreenShot(scenario.getName());
        }
        WebDriverManager.quitWebDriver();
        uiContext.setWebDriver(null);
    }

}
