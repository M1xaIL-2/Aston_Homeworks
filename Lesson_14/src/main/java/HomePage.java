import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {
    By button = By.className("button__default");
    By blockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    By phone = By.id("connection-phone");
    By sum = By.id("connection-sum");
    By pay = By.className("pay-description__cost");
    By buttonPay = By.cssSelector(".colored");
    By title = By.className("pay-description__text");

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

    public String titleNameCheck() {
        WebElement title = driver.findElement(blockTitle);
        return title.getText().replace("\n", " ").trim();
    }

    public String phoneNumberCheck() {
        WebElement payment = wait.until(ExpectedConditions.visibilityOfElementLocated(title)); // ищу Оплата: Услуги связи Номер:375297777777
        return payment.getText().replaceAll("[^0-9]", "");
    }

    public String payCheck() {
        WebElement cost = driver.findElement(pay);
        String textCost = cost.getText().trim().replaceAll("[^0-9.]", "");
        return String.format("%.2f", Double.parseDouble(textCost));
    }

    public String buttonPayCheck() {
        WebElement payButton = driver.findElement(buttonPay);
        String textButton = payButton.getText().trim().replaceAll("[^0-9.]", "");
        return String.format("%.2f", Double.parseDouble(textButton));
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

    public String fillInPhoneNumber(String value) {
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys(value);
        String txt1 = driver.findElement(phone).getAttribute("value");
        return txt1;
    }

    public String fillInSum(String value) {
        driver.findElement(sum).click();
        driver.findElement(sum).sendKeys(value);
        String txt2 = driver.findElement(sum).getAttribute("value");
        return txt2;
    }

    public void continueButtonClick() {
        driver.findElement(button).click();
    }

    public void frameToSwitch() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
    }

    public void fieldsOfCardCheck() {
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

    public void cardImgCheckWithLogos() {
        String[] cardLogos = {
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/visa-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/mastercard-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/belkart-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/maestro-system.svg",
                "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/mir-system-ru.svg"
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
