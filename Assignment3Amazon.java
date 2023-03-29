package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Assignment3Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
		ChromeDriver Driver =new ChromeDriver(options);
		Driver.get("https://www.amazon.in/");
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		
		Driver.findElement(By.xpath("//div[text()='oneplus 9 pro']")).click();
		
		String price =  Driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		
		System.out.println("Price of the first displayed product is "+ price);
		
		System.out.println("Ratings of the first displayed product are "+ Driver.findElement(By.xpath("//span[@class='a-size-base']")).getText());
	
		Driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		
		Set<String> webpage = Driver.getWindowHandles();
		
		List<String> secondary = new ArrayList<String>(webpage);
		
		Driver.switchTo().window(secondary.get(1));
		
		WebElement img = Driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		
		File source = img.getScreenshotAs(OutputType.FILE);
		
		File detination = new File("./snap/amazon.png");
		
		FileUtils.copyFile(source, detination);
		
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		Thread.sleep(2000);
	
		String subtotal = Driver.findElement(By.xpath("//div[@class='a-column a-span11 a-text-left a-spacing-top-large']/span[2]")).getText();
		
		System.out.println("subttotal is : "+ subtotal);
		
		if(subtotal.contains(price))
		{
			System.out.println("Yes, both Total price and subtotal are same");
		}
		else
		{
			System.out.println("No, Total price and subtotal are  not same");
		
		}
		
		
		Thread.sleep(2000);
		Driver.quit();
		
	
	}

}

