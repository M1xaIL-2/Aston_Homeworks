import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mtsPageObjectTest {

    @Test

    public void FieldsAndButtons() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.navigate().to("https://www.mts.by/");
        HomePage homePage = new HomePage(driver);

        // Закрытие окна с "куки"
        homePage.cookieOut();

        String[] logos = {
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/visa-verified.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/mastercard-secure.svg",
                "https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/belkart.svg"
        };

        String[] options = {
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        };

        //Прокрутить сайт вниз
        JavascriptExecutor down = (JavascriptExecutor) driver;
        down.executeScript("window.scrollBy(0,1700);");

        // №1 Проверить название указанного блока
        assertEquals("Онлайн пополнение без комиссии", homePage.titleName());


        // №2 Проверить логотипы платёжных систем
        List<WebElement> imgElements = driver.findElements(By.cssSelector(".pay__partners img"));

        for (String logo : logos) {
            for (WebElement img : imgElements) {
                if (img.getAttribute("src").equals(logo)) {
                    System.out.println("Логотип " + img.getAttribute("src") + " совпадает с " + logo);
                    System.out.println();
                    //assertEquals(logo, img.getAttribute("src"), "Логотип не совпадает" + logo);
                    assertTrue(img.isDisplayed(), "Логотип отображается");
                }
            }
        }

        // №3 ссылка "Подробнее о сервисе"
        homePage.serviceLink();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl);
        System.out.println("Переход по ссылке успешно проведён");
        //вернуться на сайт mts.by
        driver.navigate().back();

        System.out.println();

        // Закрытие окна с "куки" - второй раз
        homePage.cookieOut();

        System.out.println();

        //№4 Проверить надписи в полях

       driver.findElement(By.cssSelector(".select__header")).click();

         for (String option : options){
                List<WebElement> elements = driver.findElements(By.className("select__option"));
             WebElement wait2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select__header")));
                for (WebElement element : elements) {
                    if (element.getText().equals(option)) {
                        element.click();
                        homePage.checkPlaceholder(option);
                        break;
                    }
                }

            try {
                driver.findElement(By.cssSelector(".select__header")).click();
            } catch (Exception e) {
                // Игнорируем, если кнопка закрытия не найдена
            }
        }

        System.out.println();

        //№5 Заполнить поля и проверить работу кнопки
        WebElement wait3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select__header")));
        wait3.click();
        List<WebElement> elements = driver.findElements(By.className("select__option"));
        for (WebElement element : elements) {
            if (element.getText().equals("Услуги связи")) {
                element.click();
                break;
            }
        }
        homePage.fillInField1();
        homePage.fillInField2();
        homePage.buttonClick();

        WebElement wait5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-wrapper")));
// WebElement wait4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-wrapper__content-container app-wrapper__content-container_full")));
        String txt1 = driver.findElement(By.className("connection-phone")).getText();
        String txt2 = driver.findElement(By.className("pay-description__text")).getText();
        System.out.println(txt1);
        System.out.println(txt2);

        assertEquals("1500", driver.findElement(By.className("pay-description__cost")), "Не совпдает"); //вверху
        assertEquals("1500", driver.findElement(By.className("colored")), "Не совпдает"); //кнопка
        assertEquals("1500", driver.findElement(By.className("colored")), "Не совпдает");


        //  Оплата: Услуги связи Номер:375297777777

              /*  <div _ngcontent-bbt-c62="" class="pay-description__text"><span _ngcontent-bbt-c62="">Оплата: Услуги связи
        Номер:375297777777</span><!----><!----></div>*/

        System.out.println("Конец теста");

    }
}

