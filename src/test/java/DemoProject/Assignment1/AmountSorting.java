package DemoProject.Assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class AmountSorting extends BaseClass {

	LoginPage loginPage;
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = launchbrowser("chrome");
		loginPage = new LoginPage(driver);
	}

	@Test
	public void verifySorting() {
		driver.navigate().to("https://sakshingp.github.io/assignment/login.html");
		loginPage.login("Archana", "archana123");
		driver.findElement(By.id("amount")).click();

		List<WebElement> amounts = driver
				.findElements(By.xpath("//table[@id='transactionsTable']//tbody//td//span[contains(@class,'text')]"));

		List<Double> amountList = new ArrayList<>();
		List<Double> unsortedList = new ArrayList<>();
		for (WebElement amount : amounts) {
			String[] amtDta = amount.getText().split(" ");
			String amt = amtDta[0] + amtDta[1];
			amountList.add(Double.valueOf(amt.replace(",", "")));
		}
		unsortedList.addAll(amountList) ;

		System.out.println("Unsorted List : " + unsortedList);

		Collections.sort(amountList);

		System.out.println("Reverse Sorted List :" + amountList);

		for (int i = 0; i < amountList.size(); i++) {
			System.out.println(unsortedList.get(i));
			System.out.println(amountList.get(i));

			Assert.assertEquals("Amount list is not sorted in asscending order : "+amountList ,
					unsortedList.get(i), amountList.get(i));
		}

	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
