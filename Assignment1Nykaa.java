package week4.day2;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Assignment1Nykaa {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
		ChromeDriver Driver =new ChromeDriver(options);
		Driver.get("https://www.nykaa.com/");
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//mouse hover on brands
		WebElement brands = Driver.findElement(By.xpath("//a[text()='brands']"));
		Actions control = new Actions(Driver);
		control.moveToElement(brands).perform();
		
		//select loreal
		Driver.findElement(By.xpath("//img[contains(@src,'https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png')]")).click();
		
		//Tilte check
		String title = Driver.getTitle();
		
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println("Yes, current page title is correct");
		}
		else
		{
			System.out.println("No, current page title is not  correct");
		}
		
		//sorting by customer rate
		Driver.findElement(By.xpath("//span[@class='sort-name']/following-sibling::*[name()='svg']")).click();
		Driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']/div[2]")).click();
		Thread.sleep(2000);
		//sorting by category
		Driver.findElement(By.xpath("(//*[name()='svg' and @class='arrow-icon'])[2]")).click();
		Driver.findElement(By.xpath("(//span[@class='side-count'])[1]//*[name()='svg' and @class='arrow-icon']")).click();
		Driver.findElement(By.xpath("//span[@class='side-count']//*[name()='svg' and @class='arrow-icon']")).click();
		Driver.findElement(By.xpath("//label[@for='checkbox_Shampoo_316']//div[2]")).click();
		
		//selecting concern
		Driver.findElement(By.xpath("//span[text()='Concern']/parent::div//div//*[name()='svg']")).click();
		//Driver.findElement(By.xpath("//input[@type='text' and @placeholder='Search']")).sendKeys("color");
		Thread.sleep(3000);
		//Driver.findElement(By.xpath("//span[text()='Color Protection']/parent::div/following-sibling::div")).click();
		//Driver.findElement(By.xpath("//label[@for='checkbox_Color Protection_10764']//div[2]")).click();
		Driver.findElement(By.xpath("//input[@id='checkbox_Color Protection_10764']/following-sibling::label//span[1]")).click();
		
		//checking for filter shampoo
		Thread.sleep(2000);
		
		List<WebElement> filters = Driver.findElements(By.xpath("//span[@class='filter-value']"));
		
		List<String> lists = new ArrayList<String>();
		
		for(WebElement we:filters)
		{
			lists.add(we.getText());
		}		
		System.out.println("Current Filter list: " + lists);
		if(lists.contains("Shampoo"))
		{
			System.out.println("Yes, Shampoo is filtered");
		}
		else
		{
			System.out.println("No , shampoo is not filtered");
		}
		
		//step 9- switching secondary window
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[contains(@class,'css-xrzmfa')]")).click();
		Set<String> windows = Driver.getWindowHandles();
		List<String> listofwindows = new ArrayList<String>(windows);
		Driver.switchTo().window(listofwindows.get(1));
		
		Driver.findElement(By.xpath("//span[text()='180ml']")).click();
		System.out.println("MRP of the product "+ Driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText());
		Driver.findElement(By.xpath("//span[text()='Add to Bag']/parent::button")).click();
		Driver.findElement(By.xpath("(//button[@type='button'])[1]/*[name()='svg']")).click();
		
		//switching to frame to get grand total
		
		Driver.switchTo().frame(0);
		String grandPrice = Driver.findElement(By.xpath("(//span[@color='#001325'])[2]")).getText();
		System.out.println("Grand Total of the product is " +  grandPrice);
		Driver.findElement(By.xpath("//div[contains(@class,'css-ltzjhp e25lf6d7')]/button")).click();
		Driver.findElement(By.xpath("//button[contains(text(),'Continue as guest')]")).click();
		Driver.findElement(By.xpath("(//img[contains(@class,'css-16z7tzi ek8d9s80')])[2]")).click();
		String finPrice = Driver.findElement(By.xpath("//p[contains(@class,'css-1e59vjt eka6zu20')]")).getText();
		System.out.println("Final Price :" + finPrice );
		
		if(grandPrice.contentEquals(finPrice))
		{
			System.out.println("Both Grand and Total Price are equal");
		}
		else
		{
			System.out.println("Both Grand and Total Price are not equal");
		}
		
		Driver.quit();
	}

}
/*
 * System.out.println("Grant Total(includes product price + shipping) :" + tot);
 * d.findElement(By.xpath("//div[contains(@class,'css-ltzjhp e25lf6d7')]/button"
 * )).click(); d.findElement(By.
 * xpath("(//div[contains(@class,'css-1edwnq3 e9pts8a1')])[3]/button")).click();
 * d.findElement(By.xpath("(//img[contains(@class,'css-16z7tzi ek8d9s80')])[2]")
 * ).click(); Thread.sleep(2000); String finalPrice = d.findElement(By.
 * xpath("(//div[contains(@class,'css-46b9vi efuv1qx0')])[3]/following-sibling::p"
 * )).getText(); //System.out.println(finalPrice); if (tot.contains(finalPrice))
 * {
 */