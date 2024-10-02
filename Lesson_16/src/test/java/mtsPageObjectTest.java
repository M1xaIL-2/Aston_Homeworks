import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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

    @org.testng.annotations.Test
    @DisplayName("Проверить название указанного блока")
    @Description("Проверить правильность названия блока (Онлайн пополнение без комиссии)")
    public void a_scrollDown() {
        scrollDown();
        blockNameTest();
    }

    @Step("Прокрутить сайт вниз")
    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".pay__wrapper")));
    }

    @Step("Проверить название блока")
    public void blockNameTest() {
        assertEquals("Онлайн пополнение без комиссии", homePage.titleNameCheck());
    }


    @Test
    @DisplayName("Проверить название указанного блока")
    @Description("Проверить правильность названия блока (Онлайн пополнение без комиссии)")
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
    @DisplayName("Заполнить поля, проверить работу кнопки и сравнить полученные данные")
    @Description("Проверить выпадающий список; Заполнить поля,  проверить работу кнопки, а также сравнить полученные данные (телефон, сумма)")
    public void e_FieldsAndButtons() {
        selectService();
        String phoneFormat =  homePage.fillInPhoneNumber("297777777");
        String summerFormat = homePage.fillInSum("150");
        clickButton();
        validateDate(phoneFormat, summerFormat);
    }

    @Step("Проверка выпадающего списка")
    public void selectService() {
        WebElement wait3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select__header")));
        wait3.click();
        List<WebElement> elements = driver.findElements(By.className("select__option"));
        for (WebElement element : elements) {
            if (element.getText().equals("Услуги связи")) {
                element.click();
                break;
            }
        }
    }

    @Step("Заполнить первое поле и отформатировать номер телефона")
    public String fillInField1 () {
        homePage.fillInPhoneNumber("297777777");
        String phoneFormat = homePage.fillInPhoneNumber("297777777").replaceAll("[^0-9]", "");// выдаёт (29)777-77-77. Необходимо убрать скобки
        return phoneFormat;
    }

    @Step("Заполнить второе поле и отформатировать сумму")
    public String fillInField2 () {
        homePage.fillInSum("150");
        String summerFormat = homePage.fillInSum("150").replaceAll("[^0-9.]", "");
        summerFormat = String.format("%.2f", Double.parseDouble(summerFormat)); // Преобразовать в 150,00
        return summerFormat;
    }

    @Step("Нажать на кнопку")
    public void clickButton() {
        homePage.continueButtonClick();
    }

    @Step("Проверка данных")
    public void validateDate(String phoneFormat, String summerFormat) {
        homePage.frameToSwitch();
        assertEquals("375297777777", homePage.phoneNumberCheck());

        System.out.println();

        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.pay));
        assertEquals("150", summerFormat);

        System.out.println();

        assertEquals("150", summerFormat);
    }

    @Test
    public void f_fieldsOfCardTest() {
        //Л14_задание №2
        homePage.fieldsOfCardCheck();
    }

    @Test
    public void g_cardImgTest() {
        homePage.cardImgCheckWithLogos();
    }
}


//links: https://www.youtube.com/watch?v=KeCTFgdCCWU
// https://www.youtube.com/watch?v=0wVpdaBu7Lo&t=631s