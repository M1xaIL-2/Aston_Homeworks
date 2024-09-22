import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class HomePage {
    By button = By.className("button__default");
    By blockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    By phone = By.id("connection-phone");
    By sum = By.id("connection-sum");


    private String[] checks = {
            "Номер телефона", "Сумма", "E-mail для отправки чека",
            "Номер абонента", "Сумма", "E-mail для отправки чека",
            "Номер счета на 44", "Сумма", "E-mail для отправки чека",
            "Номер счета на 2073", "Сумма", "E-mail для отправки чека"
    };

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void cookieOut() {
        try {
            WebElement cookieWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cookie__wrapper")));
            cookieWrapper.click();
            WebElement button = driver.findElement(By.className("cookie__ok"));
            button.click();
        } catch (NoSuchElementException e) {
            System.out.println("Окно с куки не найдено" + e.getMessage());
        } catch (WebDriverException e) {         // замена TimeOutException - почему то здесь не работает
            System.out.println("Окно с куки не найдено" + e.getMessage());
        }
    }

    public String titleName() {
        WebElement title = driver.findElement(blockTitle);
        return title.getText().replace("\n", " ").trim();
    }

    public void serviceLink() {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();
    }

    public void checkPlaceholder(String option) {
        if (option.equals("Услуги связи")) {
            checkField("connection-phone", checks[0]);
            checkField("connection-sum", checks[1]);
            checkField("connection-email", checks[2]);
        } else if (option.equals("Домашний интернет")) {
            checkField("internet-phone", checks[3]);
            checkField("internet-sum", checks[4]);
            checkField("internet-email", checks[5]);
        } else if (option.equals("Рассрочка")) {
            checkField("score-instalment", checks[6]);
            checkField("instalment-sum", checks[7]);
            checkField("instalment-email", checks[8]);
        } else if (option.equals("Задолженность")) {
            checkField("score-arrears", checks[9]);
            checkField("arrears-sum", checks[10]);
            checkField("arrears-email", checks[11]);
        }
    }

    private void checkField(String fieldId, String expectedPlaceholder) {
        String placeholder = driver.findElement(By.id(fieldId)).getAttribute("placeholder");
        if (placeholder.equals(expectedPlaceholder)) {
            System.out.println("Надпись в поле " + placeholder + " совпала с " + expectedPlaceholder);
        } else {
            System.out.println("Внимание! Надпись в поле " + placeholder + " не совпала с " + expectedPlaceholder);
        }
    }

    public String fillInField1() {
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys("297777777");
        String txt1 = driver.findElement(phone).getAttribute("value");
        return txt1;
    }

    public String fillInField2() {
        driver.findElement(sum).click();
        driver.findElement(sum).sendKeys("1500");
        String txt2 = driver.findElement(sum).getAttribute("value");
        return txt2;
    }

    public void buttonClick() {
        driver.findElement(button).click();
    }
}
