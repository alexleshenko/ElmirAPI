package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='subscribe-close']")
    WebElement notification;

    @FindBy(xpath = "//li[@class='p1 submenu']/a[@class='ml-a pa']")
    WebElement aboutUsBtn;

    @FindBy(xpath = "//ul[@class='submenu']/li/a[@href='/vacansii_web.html']")
    WebElement vacanciesBtn;

    @FindBy(xpath = "//input[@id='q']")
    WebElement searchField;

    @FindBy(xpath = "//span[@id='find']")
    WebElement findBtn;

    public VacanciesPage goToVacanciesPage() {
        return new VacanciesPage(driver);
    }

    public HomePage closeNotification() {
        waitForVisibility(notification);
        notification.click();
        return this;
    }

    public HomePage moveToAboutUsAndVacanciesClick() {
        waitForVisibility(aboutUsBtn);
        moveTo(aboutUsBtn);
        waitForVisibility(vacanciesBtn);
        moveTo(vacanciesBtn);
        vacanciesBtn.click();
        return this;
    }

    public HomePage inputTextAndSearch(String text) {
        waitForVisibility(searchField);
        searchField.clear();
        searchField.sendKeys(text);
        findBtn.click();
        return this;
    }

    public VideocardsPage goToVideocardsPage(WebDriver driver) {
        return new VideocardsPage(driver);
    }

    public MfuPage goToMfuPage(WebDriver driver) {
        return new MfuPage(driver);
    }

    public ShtativPage goToShtativPage(WebDriver driver) {
        return new ShtativPage(driver);
    }

    public GoodsPage goToGoodsPage(WebDriver driver) {
        return new GoodsPage(driver);
    }

}
