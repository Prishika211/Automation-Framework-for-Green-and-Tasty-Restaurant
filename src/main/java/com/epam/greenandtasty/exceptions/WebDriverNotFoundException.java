package com.epam.greenandtasty.exceptions;

public class WebDriverNotFoundException extends IllegalStateException {
  public WebDriverNotFoundException(){
    super("WebDriver not found.");
  }

  public WebDriverNotFoundException(String message) {
    super(message);
  }
}
