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
        String currentLocation = driver.findElement(By.cssSelector(".region-list__name")).getText();

        Assert.assertEquals(currentLocation, "Москва");
    }

    @Test
    public void gotoSberbankPageAndChangeLocationToNizhniyNovgorodRegion() {
        driver.get("https://sberbank.ru/");
        driver.findElement(By.cssSelector(".region-list__arrow")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Нижегородская область')]")).click();
        String currentLocation = driver.findElement(By.cssSelector(".region-list__name")).getText();

        Assert.assertNotEquals(currentLocation,"Москва");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToVK() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_vk")).click();
        String linkToVK = driver.getCurrentUrl();

        Assert.assertEquals(linkToVK, "https://vk.com/sberbank");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToFacebook() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_fb")).click();
        String linkToFacebook = driver.getCurrentUrl();

        Assert.assertEquals(linkToFacebook, "https://www.facebook.com/sberbank");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToTwitter() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_tw")).click();
        String linkToTwitter = driver.getCurrentUrl();

        Assert.assertEquals(linkToTwitter, "https://twitter.com/sberbank/");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToYouTube() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_yt")).click();
        String linkToYouTube = driver.getCurrentUrl();

        Assert.assertEquals(linkToYouTube, "https://www.youtube.com/user/Sberbank");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToInstagram() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_ins")).click();
        String linkToInstagram = driver.getCurrentUrl();

        Assert.assertEquals(linkToInstagram, "https://www.instagram.com/sberbank/");
    }

    @Test
    public void gotoSocialGroupsInFooterCheckLinkToOK() {
        driver.get("https://sberbank.ru/");
        WebElement element = driver.findElement(By.cssSelector(".social__list"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.cssSelector(".social__icon_type_ok")).click();
        String linkToOK = driver.getCurrentUrl();

        Assert.assertEquals(linkToOK, "https://ok.ru/sberbank");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
