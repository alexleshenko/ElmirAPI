package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VideocardsPage extends BasePage {
    public VideocardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/video_cards/']")
    WebElement videocardsBtn;

    @FindBy(xpath = "//h1[@id='page-title' and text()= 'Видеокарты для компьютера ']")
    WebElement videocards;

    public VideocardsPage clickToVideocards() {
        waitForVisibility(videocardsBtn);
        videocardsBtn.click();
        return this;
    }

    public boolean isUrlContains(String url) {
        return driver.getCurrentUrl().contains(url);
    }

    public List<WebElement> getListOfVideocards() {
        return driver.findElements(By.xpath("//a[@class='vit-name'][@href]"));
    }

    public boolean isVideocardsDisplayed() {
        waitForVisibility(videocards);
        return videocards.isDisplayed();
    }
}
