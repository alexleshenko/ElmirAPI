package tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.BasePage;
import pages.data.Goods;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseTest {
    //public static final Logger logger = LogManager.getLogger(BasePage.class);
    public WebDriver driver;
    private static final String BASE_URL = "https://elmir.ua/";

    public void openUrl() {
        driver.get(BASE_URL);
        //logger.debug("Site " + BASE_URL + " was opened");
    }

    public boolean isTextFound() {
        try {
            driver.findElement(By.xpath("//li[text()='Не найдено ни одного товара']")).isDisplayed();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @BeforeMethod
    public void setUp() {
            //Разобраться почему не запускался
//        WireMockServer wireMockServer = new WireMockServer(8089);
//        wireMockServer.start();
//        wireMockServer.stubFor(get(urlEqualTo("/api/goods"))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withBodyFile("goods.json")));

        //logger.debug("____Test started____");
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.chromedriver().driverVersion("120.0.6099.225").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void finishSession() {
        driver.quit();

    }
}
