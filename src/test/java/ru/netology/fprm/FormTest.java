package ru.netology.fprm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class FormTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, text.trim());


    }

    @Test
    void invalidFieldName() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector(".input__sub")).getText();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldName2() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("button")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".input__sub"));
        String text = elements.get(0).getText();
        String expected = "Укажите точно как в паспорте";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldPhone() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("button")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".input__sub"));
        String text = elements.get(1).getText();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldPhone2() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7(999) 470-21-21");
        driver.findElement(By.cssSelector("button")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".input__sub"));
        String text = elements.get(1).getText();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void checkboxNotChecked() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector(".checkbox__text")).getText();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        Assertions.assertEquals(expected, text);

    }

}
