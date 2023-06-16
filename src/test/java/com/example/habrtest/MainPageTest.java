package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void changeLogTest() {

        WebElement userIcon = driver.findElement(By.cssSelector("svg[data-test-id='menu-toggle-guest']"));
        userIcon.click();

        WebElement rulesLink = driver.findElement(By.xpath("//*[contains(text(),'Правила сайта')]"));
        rulesLink.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Changelog')]")).isDisplayed(), "Changelog не найден");
    }

}
