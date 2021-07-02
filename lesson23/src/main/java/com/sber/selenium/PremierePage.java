package com.sber.selenium;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class PremierePage {

    public void checkOfText() {
        open("https://www.kinopoisk.ru/premiere/");
        $(byText("График кинопремьер")).shouldBe(Condition.visible);
    }

    public void checkOfGoUpButton() {
        open("https://www.kinopoisk.ru/premiere/");
        //$(By.id("GoUpButton")).shouldHave(Condition.exactText("opacity: 0"));
        $(By.id("GoUpButton")).shouldHave(Condition.cssValue("opacity", "0"));
    }

    public void clickNext() {
        open("https://www.kinopoisk.ru/premiere/");
        $(By.xpath("//a[@data-handler=\"next\"]")).click();
    }
}
