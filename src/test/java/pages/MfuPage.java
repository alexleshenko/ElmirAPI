package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MfuPage extends BasePage {
    public MfuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//section[@class='vit-info']/a")
    WebElement mfuName;

    @FindBy(xpath = "//span[@class='tovar-price']")
    WebElement mfuPrice;

    public String getMfuName() {
        return mfuName.getText();
    }

    public Object getMfuPrice() {
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='tovar-price']/span/child::*"));
        String currency = driver.findElement(By.xpath("//span[@class='tovar-curr']")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        elements.stream().filter(WebElement::isDisplayed).forEach(element -> stringBuilder.append(element.getText()));
        stringBuilder.append(currency);
        return stringBuilder;
    }
}
