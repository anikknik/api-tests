package com.tricentis.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import owner.RemoteConfigOwner;
import owner.LinksConfigOwner;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        LinksConfigOwner links = ConfigFactory
                .create(LinksConfigOwner.class);
        Configuration.baseUrl = links.baseUrl();
        RestAssured.baseURI = links.apiRequestUrl();

        RemoteConfigOwner remoteConfig = ConfigFactory
                .create(RemoteConfigOwner.class);
        String remoteLogin = remoteConfig.selenoidLogin();
        String remotePassword = remoteConfig.selenoidPassword();
        String remoteUrl = remoteConfig.remoteUrl();
        Configuration.remote = "https://" + remoteLogin + ":" + remotePassword + "@" + remoteUrl;

        String remoteBrowser = System.getProperty
                ("browser", remoteConfig.remoteBrowser());
        String remoteBrowserVersion = System.getProperty
                ("browserVersion", remoteConfig.remoteBrowserVersion());
        String remoteBrowserSize = System.getProperty
                ("browserSize", remoteConfig.remoteBrowserSize());
        Configuration.browser = remoteBrowser;
        Configuration.browserVersion = remoteBrowserVersion;
        Configuration.browserSize = remoteBrowserSize;


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
