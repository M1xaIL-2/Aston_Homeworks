import org.junit.Test;
import org.openqa.selenium.*;
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
        WebDriverWait wait = new WebDriverWait(driver, 20);
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

        for (String option : options) {
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
        String phone = homePage.fillInField1(); // выдаёт (29)777-77-77. Необходимо убрать скобки
        String phoneFormat = phone.replaceAll("[^0-9]","");

        homePage.fillInField2();
        String summer = homePage.fillInField2();
        String summerFormat = summer.replaceAll("[^0-9.]","");
        summerFormat = String.format("%.2f",Double.parseDouble(summerFormat)); // Преобразовать в 150,00

        homePage.buttonClick();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
        WebElement payment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__text"))); // ищу Оплата: Услуги связи Номер:375297777777
        String phoneNumber = payment.getText().replaceAll("[^0-9]","");
        if (phoneNumber.contains(phoneFormat)) {
            System.out.println("Номер телефона совпадает: " + "375" + phoneFormat);
        }
        else { System.out.println("Номер телефона несовпадает: " + phoneNumber + " а получили " + phoneFormat);}

        System.out.println();

        WebElement cost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
        String textCost = cost.getText().trim().replaceAll("[^0-9.]","");
    //    String textFormat = textCost.replaceAll("[^0-9.]","");
        textCost = String.format("%.2f",Double.parseDouble(textCost));
        if (textCost.equals(summerFormat)) {
            System.out.println("Сумма совпадает: " + textCost);
        }
        else { System.out.println("Сумма кнопки несовпадает: "+ summerFormat + " а получили " + textCost);}

        System.out.println();

        WebElement payButton = driver.findElement(By.cssSelector(".colored"));
        String textButton = payButton.getText().trim().replaceAll("[^0-9.]","");
        textButton = String.format("%.2f",Double.parseDouble(textButton));
        if (textButton.equals(summerFormat)) {
            System.out.println("Сумма кнопки совпадает: " + summerFormat);
        }
        else {  System.out.println("Сумма кнопки несовпадает: "+ summerFormat + " а получили " + textButton);}

        System.out.println("Конец теста");
    }
}

/*links:https://mkyong.com/java/how-to-format-a-double-in-java/
https://kreisfahrer.gitbooks.io/selenium-webdriver/content/webdriver_intro/tipi_lokatorov.html
https://www.codecademy.com/resources/docs/java/strings/replaceAll
https://csharpcoderr.com/4783/ */



