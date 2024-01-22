package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GoodsPage;
import pages.HomePage;
import pages.MfuPage;
import pages.ShtativPage;
import pages.data.Goods;

//@Listeners(AllureTestNg.class)
@Epic("Тестирование веб-сайта Elmir")
@Feature("Поиск товаров")
public class ElmirApiTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(ElmirApiTest.class);
    Goods goods;

    @BeforeTest
    public void readGoodsFile() {
        logger.info("Sending request to API");
        RequestSpecification spec = RestAssured.given();
        String apiURL = "http://localhost:8080/api/goods";
        goods = spec
                .get(apiURL)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .extract().as(Goods.class);
        logger.info("Received response from API for goods");
    }

    @DataProvider(name = "CheckGoods")
    public Object[][] dataPrMethod() {
        return new Object[][]{
                {"Видеокарта Inno3D PCI-E GeForce RTX3090 24GB DDR6X iChill X4", 0, "videocard"},
                {"МФУ Kyocera Ecosys M2040dn", 1, "mfu"},
                {"Автомобильный Bluetooth-приемник Baseus Qiyin AUX Car Bluetooth Receiver", 2, "bluetooth receiver"},
                {"Штатив VELBON EX-323 Mini", 3, "shtativ"}};
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Открытие главной страницы и поиск товара")
    @Test(dataProvider = "CheckGoods", description = "Проверка наличия и соответствия данных товаров на сайте Elmir")
    public void checkGoodsInElmirForAvailability(String itemName, int index, String nameOfMatch) {
        logger.info("Starting test for item: " + itemName);
        openUrl();
        HomePage homePage = new HomePage(driver);
        homePage.closeNotification();

        SoftAssert softAssert = new SoftAssert();

        homePage.inputTextAndSearch(itemName);

        if (isTextFound()) {
            logger.error("Item not found: " + itemName);
            Assert.fail("Item not found");
        } else {
            logger.info("Item found: " + itemName);
            GoodsPage goodsPage = homePage.goToGoodsPage(driver);


            String expectedPrice = goods.getGoods().get(index).getPrice().replaceAll(" ", "").trim();
            String actualPrice = goodsPage.getPriceOfItem().toString().trim();
            System.out.println("Expected length: " + expectedPrice.length());
            System.out.println("Actual length: " + actualPrice.length());
            System.out.println("Expected type: " + expectedPrice.getClass().getName());
            System.out.println("Actual type: " + actualPrice.getClass().getName());

            softAssert.assertEquals(actualPrice, expectedPrice, "Price of " + nameOfMatch + " is not match");
            softAssert.assertEquals(goodsPage.getAvailabilityOfItem(), goods.getGoods().get(index).getAvailability(), "Availability of " + nameOfMatch + " is not match");
            softAssert.assertEquals(goodsPage.getCodeOfItem(), goods.getGoods().get(index).getCode().replaceAll(" ", "").trim().substring(4), "Code of " + nameOfMatch + " is not match");

        }
        softAssert.assertAll();
    }

//            softAssert.assertEquals(goodsPage.getNameOfItem(), goods.getGoods().get(index).getName());
//            softAssert.assertEquals(goodsPage.getPriceOfItem(), goods.getGoods().get(index).getPrice().replaceAll(" ", ""), "Price of " + nameOfMatch + " is not match");
//            softAssert.assertEquals(goodsPage.getAvailabilityOfItem(), goods.getGoods().get(index).getAvailability(), "Availability of " + nameOfMatch + " is not match");
//            softAssert.assertEquals(goodsPage.getCodeOfItem(), goods.getGoods().get(index).getCode().replaceAll(" ", "").trim().substring(4), "Code of " + nameOfMatch + " is not match");
//        }
//        softAssert.assertAll();
//    }
}
