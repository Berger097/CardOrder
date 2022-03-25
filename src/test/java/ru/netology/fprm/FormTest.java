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
        driver = new ChromeDriver(options);
        driver.get("http://0.0.0.0:7777/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success'].paragraph")).getText();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, text.trim());


    }

    @Test
    void invalidFieldName() {
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldName2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("andrei");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void invalidFieldPhone2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7(999) 470-21-21");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expected, text);

    }
    @Test
    void checkboxNotChecked() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79994702121");
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        Assertions.assertEquals(expected, text);

    }

}
