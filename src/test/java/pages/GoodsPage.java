package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoodsPage extends BasePage {

    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//section[@class='vit-info']/a")
    WebElement nameOfItem;

    @FindBy(xpath = "//span[@class='ti-t']")
    WebElement availabilityOfItem;

    @FindBy(xpath = "//span[@class='ti-v copy']")
    WebElement codeOfItem;

    public String getNameOfItem() {
        return nameOfItem.getText();
    }

    public Object getPriceOfItem() {
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='tovar-price']/span/child::*"));
        String currency = driver.findElement(By.xpath("//span[@class='tovar-curr']")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        elements.stream().filter(WebElement::isDisplayed).forEach(element -> stringBuilder.append(element.getText()));
        stringBuilder.append(currency);
        return stringBuilder;
    }

    public String getAvailabilityOfItem() {
        return availabilityOfItem.getText();
    }

    public String getCodeOfItem() {
        return codeOfItem.getText();
    }
}
