package ru.netology.fprm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormTest {
   private WebDriver driver;

    ChromeOptions options = new ChromeOptions();


    @BeforeAll
    public static void  setUpAll() {
       WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    public void setUp() {
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        // Your test logic here
    }

}
