package ru.sberbank;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Parameters({ "PathToSocialLinksFile" })
    @Test
    public void gotoSocialGroupsInFooterCheckSocialLinks(String socialLinksFilePath) {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        List<String> linksToSocialGroups = null;
        try {
            linksToSocialGroups = Files.readAllLines(Paths.get(socialLinksFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("File with social links is absent");
        }

        for(int i = 0; i < linksToSocialGroups.size(); i++) {
            try {
                driver.findElement(By.xpath(".//a[@href = \"" + linksToSocialGroups.get(i) + "\"]"));
            } catch (Exception e) {
                Assert.fail("Link to " + linksToSocialGroups.get(i) + "is absent");
            }
        }
        Assert.assertEquals(true,true);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
