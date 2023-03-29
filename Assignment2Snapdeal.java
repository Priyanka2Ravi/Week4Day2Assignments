package week4.day2;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Assignment2Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
		ChromeDriver Driver =new ChromeDriver(options);
		Driver.get("https://www.snapdeal.com/");
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		//navigate to mens shoes
		WebElement we = Driver.findElement(By.xpath("//span[@class='catText']"));
		Actions act = new Actions(Driver);
		act.moveToElement(we).perform();
		
		Driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String count = Driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		String s1 = count.replaceAll("[()]","");

		System.out.println("Number of items displayed under sports shoes are:  " + s1);
		Driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		Driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		Driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		
		//checking the sort
		Thread.sleep(2000);
		List<WebElement>  pricelist = Driver.findElements(By.xpath("//span[contains(@class,'lfloat product-price')]"));
		String[] price = new String[pricelist.size()];
		int i=0;
		for(WebElement e:pricelist)
		{
			price[i]=e.getText();
			i++;
			
		}
		int[] num = new int[price.length];
		String dum =null;
		for(int j=0; j<price.length;j++)
		{
				dum= price[j].replaceAll("Rs. ","");
				num[j]= Integer.parseInt(dum);
				System.out.println(num[j]);
		}
		if(num[0]<num[1])
		{
			System.out.println("Shoes are sorted in low to high prices");
		}
		else
		{
			System.out.println("Shoes are not sorted in low to high prices");
		}
		////////***********************//////////////////
		
		Driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		Driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		Driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		Driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		Driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn btn-line btn-theme-secondary')]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		
		///filters check
		Thread.sleep(2000);
		List<WebElement> filter =Driver.findElements(By.xpath("//div[@class='filters-top-selected']//div/a"));
		List<String> filters = new ArrayList<String>();
		for (WebElement e:filter)
		{
			filters.add(e.getText());
		}
		System.out.println(filters);
		
		//mouse hover
		WebElement mouse = Driver.findElement(By.xpath("//img[contains(@class,'product-image wooble')]"));
		Actions mou = new Actions(Driver);
		mou.moveToElement(mouse).perform();
		Driver.findElement(By.xpath("(//div[@supc='SDL901001171'])[2]")).click();
		
		//step 13-16
		Thread.sleep(3000);
		String discPrice = Driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String discount = Driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discounted price is : "+ discPrice+ " Discount rate is : " + discount);
		
		File source = Driver.getScreenshotAs(OutputType.FILE);
		File Dest = new File("./snaps/snapdeal.png");
		
		FileUtils.copyFile(source, Dest);
		
		Driver.findElement(By.xpath("//div[contains(@class,'close close1 marR10')]")).click();
		
		Driver.close();
	}

}

