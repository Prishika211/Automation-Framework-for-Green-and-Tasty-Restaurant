package com.epam.greenandtasty.ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaiterReservationPage extends BasePage{


    @FindBy(xpath = "//label[.=' Date']//following :: input[@type='date']")
    private WebElement date;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//button[text()='Postpone']")
    private WebElement postponeBtn;

    @FindBy(xpath = "//button[.='+ Create New Reservation']")
    private WebElement createNewReservationBtn;

    @FindBy(xpath = "//button[.='Yes, Cancel']")
    private WebElement confirmCancel;

    @FindBy(xpath = "//button[@value='customer']")
    private WebElement customerRadioBtn;

    @FindBy(xpath = "//button[@value='visitor']")
    private WebElement visitorRadioBtn;

    @FindBy(xpath = "//div[@class='flex items-center gap-2']//child::button[.='+']")
    private WebElement increaseGuestsCount;

    @FindBy(xpath = "//div[@class='flex items-center gap-2']//child::button[.='-']")
    private WebElement decreaseGuestsCount;

    @FindBy(xpath = "//label[.=' From']//following::button[1]")
    private WebElement fromTime;



    @FindBy(xpath = "//label[.='Table']//following::button[1]")
    private WebElement chooseTable;

    @FindBy(xpath = "//button[.='Make a Reservation']")
    private WebElement makeAReservationBtn;

    @FindBy(xpath = "//label[.='Customer Email']//following::input")
    private WebElement emailInput;











    public WaiterReservationPage(WebDriver webwebDriver) {
        super(webwebDriver);
    }

    public WebElement getEmailInput(){
        return waitForElement(emailInput);
    }

    public WebElement getMakeAReservationBtn(){
        return waitForElement(makeAReservationBtn);
    }
    public WebElement getFromTime(){
        return waitForElement(fromTime);
    }
    public void selectTime(String time){
        waitForElement(fromTime).click();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value='"+time+"';" ,fromTime);

    }
    public void selectTable(String tableNumber){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value='"+tableNumber+"';" ,chooseTable);
    }

    public WebElement getChooseTable(){
        return waitForElement(chooseTable);
    }



    public WebElement getIncreaseGuestsCount(){
        return waitForElement(increaseGuestsCount);
    }

    public WebElement getCustomerRadioBtn(){
        return waitForElement(customerRadioBtn);
    }
    public WebElement getVisitorRadioBtn(){
        return waitForElement(visitorRadioBtn);
    }

    public WebElement getCancelBtn(){
        return waitForElement(cancelBtn);
    }

    public WebElement getDate(){
        return waitForElement(date);
    }

    public WebElement getPostponeBtn(){
        return waitForElement(postponeBtn);
    }

    public WebElement getCreateNewReservationBtn(){
        return waitForElement(createNewReservationBtn);
    }
    public WebElement getConfirmCancel(){
        return waitForElement(confirmCancel);
    }







}
