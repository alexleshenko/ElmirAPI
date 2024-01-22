package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShtativPage extends BasePage {
    public ShtativPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//section[@class='vit-info']/a")
    WebElement shtativ;

    public String getShtativName() {
        return shtativ.getText();
    }
}
