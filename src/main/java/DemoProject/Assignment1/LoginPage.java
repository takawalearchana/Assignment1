package DemoProject.Assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BaseClass {

	WebDriver driver;
	By username = By.cssSelector("input#username");
	By password = By.cssSelector("input#password");
	By loginButton = By.cssSelector("button#log-in");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String username, String password) {
		driver.navigate().refresh();
		driver.findElement(this.username).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);
		driver.findElement(this.loginButton).click();
	}

	public void validmesg(boolean valid, String errorMsg) {
		try {
			if (!valid && driver.findElement(By.xpath("//div[@class='alert alert-warning']")).isDisplayed()) {
				String actualErrorMsg = driver.findElement(By.xpath("//div[@class='alert alert-warning']")).getText();
				Assert.assertEquals(actualErrorMsg, errorMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
