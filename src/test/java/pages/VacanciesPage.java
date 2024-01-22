package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VacanciesPage extends BasePage {

    public VacanciesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@id='page-title']")
    WebElement vacanciesTitle;

    public boolean isVacanciesTitleDisplayed() {
        return vacanciesTitle.isDisplayed();
    }

    public boolean isUrlcontains(String url) {
        return driver.getCurrentUrl().contains(url);
    }
}
