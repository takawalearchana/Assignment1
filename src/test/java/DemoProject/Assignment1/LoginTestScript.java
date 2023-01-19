package DemoProject.Assignment1;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestScript extends BaseClass {
	WebDriver driver;
	LoginPage loginPage;

	@BeforeClass
	public void setup() {
		driver = launchbrowser("chrome");
		loginPage = new LoginPage(driver);
	}

	@Test (dataProvider = "loginData" )
	public void verifyLogin(String username, String password, Boolean valid, String errorMsg) {
		driver.navigate().to("https://sakshingp.github.io/assignment/login.html");
		loginPage.login(username, password);
		loginPage.validmesg(valid,errorMsg );
		driver.navigate().back();
	}

	@DataProvider
	public Object[][] loginData() {
		return new Object[][] { 
			{ "Archana", "archana123", true, "" }, 
			{ "A...", "...a..123", true, "" 	}, 
			{ "*--*", "*--*" , true, ""			},
			{ "@$%abcd", "1234!@#", true , ""	}, 
			{ "", "abcdef",false, "Username must be present"},
			{ "qbdg12", "",false, "Password must be present"},
			{ "", "", false, "Both Username and Password must be present"}
			};
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
