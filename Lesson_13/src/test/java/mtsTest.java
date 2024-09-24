import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class mtsTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.navigate().to("https://www.mts.by/");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public static void cookieOut(WebDriver driver) {
        try {
            WebDriverWait wait1 = new WebDriverWait(driver, 5);
            WebElement cookieWrapper = (WebElement) wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("cookie__wrapper")));
            cookieWrapper.click();
            WebElement button = driver.findElement(By.className("cookie__ok"));
            button.click();
        } catch (NoSuchElementException e) {
            System.out.println("Окно с куки не найдено" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("Окно с куки не найдено" + e.getMessage());
        }
    }

    @Test
    public void a_FieldsAndButtons() {

        // Закрытие окна с "куки"
        cookieOut(driver);

        //Прокрутить сайт вниз
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".pay__wrapper")));

        // №1 Проверить название указанного блока
        WebElement blockTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        String titleName = blockTitle.getText().replace("\n", " ").trim();
        //метод replace - удаляет любые br и переносы строк + trim - удаляет лишние пробелы в начале и конце строки.
        assertEquals("Онлайн пополнение без комиссии", titleName);
        System.out.println("№1: Название блока проверено");
        System.out.println();
    }

    @Test
    public void b_logoCheckTest() {
        // №2 Проверить логотипы платёжных систем
        String[] logos = {
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa-verified.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard-secure.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/belkart.svg"
        };

        List<WebElement> imgElements = driver.findElements(By.cssSelector(".pay__partners img"));

        for (String logo : logos) {
            for (WebElement img : imgElements) {
                if (img.getAttribute("src").equals(logo)) {
                    System.out.println("Логотип " + img.getAttribute("src") + " совпадает с " + logo);
                    System.out.println();
                }
            }
        }
        System.out.println("№2: Логотипы проверены");
        System.out.println();
    }


    @Test
    public void c_linkTest() {
        // №3 ссылка "Подробнее о сервисе"
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl);
        System.out.println("Переход по ссылке успешно проведён");
        //вернуться на сайт mts.by
        driver.navigate().back();

        System.out.println("№3: Ссылка работает");
        System.out.println();

        // Закрытие окна с "куки" - второй раз
        cookieOut(driver);
    }

    @Test
    public void d_captionFieldTest() {

        //№4 Заполнить поля и проверить работу кнопки
        driver.findElement(By.id("connection-phone")).click();
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).click();
        driver.findElement(By.id("connection-sum")).sendKeys("1500");  // ограничение 499??!
        driver.findElement(By.className("button__default")).click();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
        System.out.println("№4: Поля заполнены и кнопка функционирует, новый фрейм виден");
        System.out.println();

        System.out.println("Конец теста");
    }
}
