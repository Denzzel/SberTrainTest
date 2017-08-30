package ru.sberbank;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SberbankTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void gotoSberbankPageAndChangeLocationToMoscow() {
        driver.get("https://sberbank.ru/");
        driver.findElement(By.cssSelector(".region-list__name")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Москва')]")).click();
        try {
            String currentLocation = driver.findElement(By.cssSelector(".region-list__name")).getText();
            Assert.assertEquals(currentLocation, "Москва");
        } catch (Exception e) {
            Assert.fail("gotoSberbankPageAndChangeLocationToMoscow: Cant load element on page",e);
        }

    }

    @Test
    public void gotoSberbankPageAndChangeLocationToNizhniyNovgorodRegion() {
        driver.get("https://sberbank.ru/");
        driver.findElement(By.cssSelector(".region-list__arrow")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Нижегородская область')]")).click();
        try {
            String currentLocation = driver.findElement(By.cssSelector(".region-list__name")).getText();
            Assert.assertNotEquals(currentLocation, "Москва");
        } catch (Exception e) {
            Assert.fail("gotoSberbankPageAndChangeLocationToNizhniyNovgorodRegion: Cant load element on page",e);
        }
    }

    @Test
    public void gotoSocialGroupsInFooterCheckSocialLinks() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        String[] linksToSocialGroups = {
                "http://vk.com/sberbank",
                "https://www.facebook.com/bankdruzey",
                "http://twitter.com/sberbank/",
                "http://www.youtube.com/sberbank",
                "http://instagram.com/sberbank",
                "https://ok.ru/sberbank"};

        for(int i = 0; i < linksToSocialGroups.length; i++) {
            try {
                driver.findElement(By.xpath(".//a[@href = \"" + linksToSocialGroups[i] + "\"]"));
            } catch (Exception e) {
                Assert.fail("Link to " + linksToSocialGroups[i] + "is absent");
            }
        }
        Assert.assertEquals(true,true);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
