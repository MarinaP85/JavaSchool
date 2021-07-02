import org.testng.annotations.Test;

public class KinopoiskTest extends BaseTest {
    @Test
    public void testLogo() {
        driver.get("https://www.kinopoisk.ru/");
        mainPage.checkOfLogo();
    }

    @Test
    public void testOpenPremiere() {
        driver.get("https://www.kinopoisk.ru/");
        mainPage.openPremiere();
    }

    @Test
    public void testPremiereText() {
        premierePage.checkOfText();
    }

    @Test
    public void testPremiereGoUpButton() {
        premierePage.checkOfGoUpButton();
    }

    @Test
    public void testPremiereNext() {
        premierePage.clickNext();
    }

}
