package ru.yandex.practicum.stellarburger;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected ChromeDriver driver;

    public void setUpBrowser() {
        /*ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "WebDriver\\bin\\yandexdriver.exe");
        options.setBinary("C:\\Users\\Lenovo\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--start-maximized", "--disable-infobars");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);*/
    }

}
