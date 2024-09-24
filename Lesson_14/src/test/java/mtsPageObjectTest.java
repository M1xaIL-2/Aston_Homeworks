import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class mtsPageObjectTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static HomePage homePage;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.navigate().to("https://www.mts.by/");
        homePage = new HomePage(driver);
        homePage.cookieOut();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void a_blockNameTest() throws InterruptedException {

        //Прокрутить сайт вниз
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".pay__wrapper")));

        // №1 Проверить название указанного блока
        assertEquals("Онлайн пополнение без комиссии", homePage.titleName());
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
    }

    @Test
    public void c_linkTest() {
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
    }

    @Test
    public void d_captionFieldTest() {
        //№4 Заполнить поля и проверить работу кнопки
        String[] options = {
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        };

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
    }

    @Test
    public void e_FieldsAndButtons() {
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
        String phoneFormat = phone.replaceAll("[^0-9]", "");

        homePage.fillInField2();
        String summer = homePage.fillInField2();
        String summerFormat = summer.replaceAll("[^0-9.]", "");
        summerFormat = String.format("%.2f", Double.parseDouble(summerFormat)); // Преобразовать в 150,00

        homePage.buttonClick();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
        WebElement payment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__text"))); // ищу Оплата: Услуги связи Номер:375297777777
        String phoneNumber = payment.getText().replaceAll("[^0-9]", "");
        if (phoneNumber.contains(phoneFormat)) {
            System.out.println("Номер телефона совпадает: " + "375" + phoneFormat);
        } else {
            System.out.println("Номер телефона несовпадает: " + phoneNumber + " а получили " + phoneFormat);
        }

        System.out.println();

        WebElement cost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
        String textCost = cost.getText().trim().replaceAll("[^0-9.]", "");
        textCost = String.format("%.2f", Double.parseDouble(textCost));
        if (textCost.equals(summerFormat)) {
            System.out.println("Сумма совпадает: " + textCost);
        } else {
            System.out.println("Сумма кнопки несовпадает: " + summerFormat + " а получили " + textCost);
        }

        System.out.println();

        WebElement payButton = driver.findElement(By.cssSelector(".colored"));
        String textButton = payButton.getText().trim().replaceAll("[^0-9.]", "");
        textButton = String.format("%.2f", Double.parseDouble(textButton));
        if (textButton.equals(summerFormat)) {
            System.out.println("Сумма кнопки совпадает: " + summerFormat);
        } else {
            System.out.println("Сумма кнопки несовпадает: " + summerFormat + " а получили " + textButton);
        }
    }

    @Test
    public void f_fieldsOfCardTest() {
        //Л14_задание №2
        String[] labels = {
                "Номер карты", "Срок действия", "CVC",
                "Имя держателя (как на карте)"
        };

        String[] classNames = {
                "label.ng-tns-c46-1",
                "label.ng-tns-c46-4",
                "label.ng-tns-c46-5",
                "label.ng-tns-c46-3"
        };

        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < classNames.length; j++) {
                String cardPlaceholder = driver.findElement(By.cssSelector(classNames[j])).getText();
                if (cardPlaceholder.equals(labels[i])) {
                    System.out.println("Надпись в поле " + cardPlaceholder + " совпала с " + labels[i]);
                    System.out.println();
                }
            }
        }
    }

    @Test
    public void g_cardImgTest() {

        String[] cardLogos = {
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/visa-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/mastercard-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/belkart-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/maestro-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/mir-system-ru.svg"
        };

        String[] cardClassNames = {
                "ng-tns-c61-0 ng-star-inserted",
                "ng-tns-c61-0 ng-trigger ng-trigger-randomCardState ng-star-inserted ng-animating",
        };

        List<WebElement> cardImgElements = driver.findElements(By.cssSelector(".cards-brands img"));

        for (String cardlogo : cardLogos) {
            for (WebElement cardImg : cardImgElements) {
                if (cardImg.getAttribute("src").equals(cardlogo)) {
                    System.out.println("Логотип " + cardImg.getAttribute("src") + " совпадает с " + cardlogo);
                    System.out.println();
                }
            }
        }
    }
}

/*links:
https://kreisfahrer.gitbooks.io/selenium-webdriver/content/webdriver_intro/tipi_lokatorov.html - типы локаторов
https://www.codecademy.com/resources/docs/java/strings/replaceAll - как преобразовать номер телефона.
https://csharpcoderr.com/4783/ + https://mkyong.com/java/how-to-format-a-double-in-java/- форматнуть строки для 150,00
https://www.testim.io/blog/selenium-scroll-to-element/ - скроллвниз к элементу.
https://divancoder.ru/2017/07/junit-fixmethodorder/ - выполнение тестов по порядку.
https://ru.stackoverflow.com/questions/1194019/%D0%A0%D0%B0%D0%B7%D0%BD%D0%B8%D1%86%D0%B0-%D0%BC%D0%B5%D0%B6%D0%B4%D1%83-before-%D0%B8-beforeclass-%D0%B2-junit-4
 */





