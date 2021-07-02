package com.sber.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainPage {
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@class=\"_3EnouTm1X_faHV_DfZvGA2 kinopoisk-header-logo__img\"]")
    WebElement logo;

    @FindBy(xpath = "//a[text()=\"Скоро в кино\"]")
    WebElement premiereButton;

    public void checkOfLogo() {
        Assert.assertTrue(logo.isDisplayed());
    }

    public void openPremiere() {
        premiereButton.click();
    }
}
