package practice_smart_buy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class opensooq {

	public WebDriver driver;

	SoftAssert softassertProcess = new SoftAssert();

	@BeforeTest()

	public void this_is_before_test() throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://jo.opensooq.com/en/cars/cars-for-sale");

		driver.findElement(By.xpath("//*[@id=\"Brand\"]/div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"Brand\"]/div[2]/p/input")).sendKeys("BMW");

		driver.findElement(By.xpath("//*[@id=\"Brand\"]/div[2]/div/ul/li[6]/label")).click();
		driver.findElement(By.xpath("//*[@id=\"landingPostDynamicField\"]/div/button")).click();

	}

	@Test()
	public void check_the_items() {

		List<WebElement> items = driver.findElements(By.className("postSpanTitle"));
		String new_items = items.get(11).getText();
		String updated_new_items = new_items.substring(0, 7);

		driver.findElement(By.xpath("//*[@id=\"searchBox\"]")).sendKeys(updated_new_items);
		driver.findElement(By.xpath("//*[@id=\"landingPostDynamicField\"]/div/button")).click();

		List<WebElement> new_updated_items = driver.findElements(By.className("postSpanTitle"));

		for (int i = 0; i < new_updated_items.size(); i++) {
			String last_new = new_updated_items.get(i).getText();
			String lastt_new_new = last_new.substring(0, 7);
			if (updated_new_items.contains(lastt_new_new)) {

				System.out.println("this is correct items  " + lastt_new_new);
			} else {
				System.out.println("this is WRONG items  " + lastt_new_new);
			}
		}
	}

}
