package ru.yandex.practicum.stellarburger.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import ru.yandex.practicum.stellarburger.BaseTest;
import ru.yandex.practicum.stellarburger.api.User;
import ru.yandex.practicum.stellarburger.api.UserClient;
import ru.yandex.practicum.stellarburger.api.UserResponse;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static ru.yandex.practicum.stellarburger.pages.LoginPage.LOGIN_PAGE_PATH;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;
import static ru.yandex.practicum.stellarburger.pages.ProfilePage.PROFILE_PAGE_PATH;

@DisplayName("Navigation tests when user is logged in")
public class NavigationLoggedUserTest extends BaseTest {
    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        setUpBrowser();
        user = User.getRandomUser();
        userClient = new UserClient();
        UserResponse createUserResponse = userClient.createUser(user).as(UserResponse.class);
        accessToken = createUserResponse.getAccessToken();
    }

    @After
    public void clear() {
        if(driver != null) {
            driver.quit();
        }
            userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check navigation to user account when user already logged in")
    public void navigateToAccountUserLoggedInTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        localStorage().setItem("accessToken", accessToken);

        mainPage.header.clickUserAccountLink();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL + PROFILE_PAGE_PATH));
    }

    @Test
    @DisplayName("Check navigation from user account to main page by clicking Constructor link")
    public void navigateMainPageConstructorLinkTest() {
        LoginPage loginPage = open(MAIN_PAGE_URL + LOGIN_PAGE_PATH, LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        loginPage.header.clickConstructorLink();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Check navigation from user account to main page by clicking StellarBurgers logo")
    public void navigateMainPageStellarBurgersLogoTest() {
        LoginPage loginPage = open(MAIN_PAGE_URL + LOGIN_PAGE_PATH, LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        loginPage.header.clickStellarBurgersLogo();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL));
    }
}
