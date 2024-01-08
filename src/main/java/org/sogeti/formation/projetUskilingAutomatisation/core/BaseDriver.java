package org.sogeti.formation.projetUskilingAutomatisation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;


public class BaseDriver  {
    protected WebDriver driver = new FirefoxDriver();


    public void scrollDown(int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");

    }
}
