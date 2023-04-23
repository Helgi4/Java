import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class LessonNinth {

    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://github.com/");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("username", "password");
        IssuesPage issuesPage = homePage.clickIssuesTab();
        NewIssuePage newIssuePage = issuesPage.clickNewIssueButton();
        newIssuePage.setIssueTitle("New Issue");
        newIssuePage.setIssueDescription("This is a new issue");
        assertTrue(issuesPage.isIssueDisplayed("New Issue"));
        driver.quit();
    }

    public static class LoginPage {
        private WebDriver driver;
        private By usernameField = By.id("login_field");
        private By passwordField = By.id("password");
        private By loginButton = By.xpath("//input[@value='Sign in']");

        public LoginPage(WebDriver driver) {
            this.driver = driver;
        }

        public HomePage login(String username, String password) {
            driver.findElement(usernameField).sendKeys(username);
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(loginButton).click();
            return new HomePage(driver);
        }
    }

    public static class HomePage {
        private WebDriver driver;
        private By issuesTab = By.xpath("//a[contains(@href, '/issues')]");

        public HomePage(WebDriver driver) {
            this.driver = driver;
        }

        public IssuesPage clickIssuesTab() {
            driver.findElement(issuesTab).click();
            return new IssuesPage(driver);
        }
    }

    public static class IssuesPage {
        private WebDriver driver;
        private By newIssueButton = By.xpath("//a[contains(@href, '/issues/new')]");

        public IssuesPage(WebDriver driver) {
            this.driver = driver;
        }

        public NewIssuePage clickNewIssueButton() {
            driver.findElement(newIssueButton).click();
            return new NewIssuePage(driver);
        }

        public boolean isIssueDisplayed(String issueTitle) {
            By issueLink = By.linkText(issueTitle);
            WebElement issueElement = driver.findElement(issueLink);
            return issueElement.isDisplayed();
        }
    }

    public static class NewIssuePage {
        private WebDriver driver;
        private By issueTitleField = By.id("issue_title");
        private By issueDescriptionField = By.id("issue_body");
        private By submitButton = By.xpath("//button[contains(text(), 'Submit')]");

        public NewIssuePage(WebDriver driver) {
            this.driver = driver;
        }

        public void setIssueTitle(String issueTitle) {
            driver.findElement(issueTitleField).sendKeys(issueTitle);
        }

        public void setIssueDescription(String issueDescription) {
            driver.findElement(issueDescriptionField).sendKeys(issueDescription);
        }
    }
}
