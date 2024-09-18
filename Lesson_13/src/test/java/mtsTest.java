import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class mtsTest {
    public void cookieOut(WebDriver driver) {
        try {
            WebDriverWait wait1 = new WebDriverWait(driver, 5);
            WebElement cookieWrapper = (WebElement) wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("cookie__wrapper")));
            cookieWrapper.click();
            WebElement button = driver.findElement(By.className("cookie__ok"));
            button.click();
        } catch (NoSuchElementException e) {
            System.out.println("Окно с куки не найдено" + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Окно с куки не найдено" + e.getMessage());
        }
    }

    @Test
    public void FieldsAndButtons() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.mts.by/");

        String[] logos = {
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa-verified.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard-secure.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/belkart.svg"
        };

        // Закрытие окна с "куки"
        cookieOut(driver);

        //Прокрутить сайт вниз
        JavascriptExecutor down = (JavascriptExecutor) driver;
        down.executeScript("window.scrollBy(0,1700);");

        // №1 Проверить название указанного блока
        WebElement blockTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        String titleName = blockTitle.getText().replace("\n", " ").trim();
        //метод replace - удаляет любые br и переносы строк + trim - удаляет лишние пробелы в начале и конце строки.
        assertEquals("Онлайн пополнение без комиссии", titleName);

        // №2 Проверить логотипы платёжных систем
        List<WebElement> imgElements = driver.findElements(By.className("imgElements_src"));

        for (String logo : logos) {
            for (WebElement img : imgElements) {
                if (img.getAttribute("src").equals(logo)) {
                    assertEquals(logo, img.getAttribute("src"), "Логотип не совпадает" + logo);
                    assertTrue(img.isDisplayed(), "Логотип отображается");
                    break;
                }
                System.out.println("Логотип " + logo + " не соответствует" + img.getAttribute("src"));
            }
        }

        System.out.println();


        // №3 ссылка "Подробнее о сервисе"
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();
        WebDriverWait wait2 = new WebDriverWait(driver, 3);

        System.out.println("Переход по ссылке успешно проведён");
        //вернуться на сайт mts.by
        driver.navigate().back();

        System.out.println();

        // Закрытие окна с "куки" - второй раз
        cookieOut(driver);

        //№4 Заполнить поля и проверить работу кнопки
        driver.findElement(By.id("connection-phone")).click();
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).click();
        driver.findElement(By.id("connection-sum")).sendKeys("1500");  // ограничение 499??!
        driver.findElement(By.className("button__default")).click();

        System.out.println("Конец теста");
        driver.quit();
    }
}
