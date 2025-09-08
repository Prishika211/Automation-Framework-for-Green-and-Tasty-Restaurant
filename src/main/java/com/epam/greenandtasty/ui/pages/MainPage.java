package com.epam.greenandtasty.ui.pages;

import io.qameta.allure.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage{
    private WebDriver webDriver;
    public MainPage(WebDriver webDriver){
        super(webDriver);
    }
    @FindBy(xpath = "//div[@class='p-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6']")
    List<WebElement> staticMenu;

    public List<WebElement> getStaticMenu(){
        return staticMenu;
    }

    public boolean verifyTheMenuCard(){
        WebElement dish = staticMenu.getFirst();
        WebElement img = dish.findElement(By.xpath("//img"));
        WebElement dishName = dish.findElement(By.xpath("//h3"));
        WebElement price = dish.findElement(By.xpath("//div[@class='flex justify-between mt-2 text-sm text-gray-600']//child::span[1]"));
        WebElement weight = dish.findElement(By.xpath("//div[@class='flex justify-between mt-2 text-sm text-gray-600']//child::span[2]"));

        return img.isDisplayed() &&
                dishName.isDisplayed() &&
                price.isDisplayed() &&
                weight.isDisplayed();

    }



}
