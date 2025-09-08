package com.epam.greenandtasty.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.epam.greenandtasty.constants.Constant.SCREEN_SHOT_PATH;
import static com.epam.greenandtasty.ui.factory.WebDriverFactory.threadLocalWebDriver;

public class ScreenshotsUtil {
    public static void captureViewPortScreenShot(String testMethod) {
        Date date = new Date();
        long time = date.getTime();
        File screenshotFile = ((TakesScreenshot) threadLocalWebDriver.get()).getScreenshotAs(OutputType.FILE);
        String screenshotName = SCREEN_SHOT_PATH + time + " - " + testMethod + ".png";
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void captureFullPageScreenShot(String testMethod) {
        Date date = new Date();
        long time = date.getTime();

        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(threadLocalWebDriver.get());

        String screenshotName = SCREEN_SHOT_PATH + testMethod + time + ".png";
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
