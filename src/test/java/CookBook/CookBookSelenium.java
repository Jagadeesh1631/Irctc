package CookBook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import Irctc.IrctcTrain;

public class CookBookSelenium {

		
		int count;
		String windowtitle;
		Set<String> id;
		static List<String> countwindow;
		public static WebDriver driver;
		
		public void configureCar(WebDriver driver)
		{
			WebElement car=driver.findElement(By.name("make"));
			Select s=new Select(car);
			s.selectByIndex(0);
			//s.selectByVisibleText(value);
		}
		
		//selecting the fuel type from Diesel, Petrol
		public void fuelType(WebDriver driver)
		{
			WebElement fuel=driver.findElement(By.cssSelector("input[value='Diesel']"));
			fuel.click();
		}
		
		//selecting the optionalFeature from airbag,parking sensor,ABS
		public void optionalFeature(WebDriver driver)
		{
			WebElement feature=driver.findElement(By.xpath("//input[@name='parksensor' and @type='checkbox']"));
			feature.click();
		}
		
		//selecting color of carType
		public void selectColor(WebDriver driver)
		{
			WebElement color=driver.findElement(By.xpath("//option[@value='wt']"));
			color.click();
		}
		
		public void helpButtonClick(WebDriver driver)
		{
			WebElement helpBtn=driver.findElement(By.xpath("//button[@id='helpbutton']"));
			helpBtn.click();
		}
		
		public void onlineChatSupportButtonClick(WebDriver driver)
		{
			WebElement chatBtn=driver.findElement(By.xpath("//button[@id='chatbutton']"));
			chatBtn.click();
		}
		
		public void visitButtonClick(WebDriver driver)
		{
			WebElement visitBtn=driver.findElement(By.xpath("//button[@id='visitbutton']"));
			visitBtn.click();
		}
		
		public int windowsCount(WebDriver driver)
		{
			id=driver.getWindowHandles();
			countwindow=new ArrayList<String>(id);
			count=countwindow.size();
			return count;
		}
		
		//Collection of title from all the windows.
		public void collectionOfWindowTitle(WebDriver driver)
		{
			List<String>title=new ArrayList<String>(id);
			//System.out.println(title);
			System.out.println("Title of Window");
			for(String s:title)
			{
				driver.switchTo().window(s);
				System.out.println(driver.getTitle());
			}
		}
    	
		public void getWindowTitle(WebDriver driver,String browserTitle)
		{
			List<String>wt=new ArrayList<String>(id);
			for(String s1:wt) {
			driver.switchTo().window(s1);
			if(driver.getTitle().equals(browserTitle)) {
			System.out.println("The browser with the title "+driver.getTitle()+" found");
			//driver.close();
			}
			}
			
		}
	
	public static void main(String[] args) {

		Scanner ss=new Scanner(System.in);
		CookBookSelenium cbs=new CookBookSelenium();
		System.out.println("Enter the browserName:");
		String browser=ss.nextLine();
		String baseUrl = "https://cookbook.seleniumacademy.com/Config.html";
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseUrl);
		driver.manage().window().maximize();
		cbs.configureCar(driver);
		cbs.fuelType(driver);
		cbs.optionalFeature(driver);
		cbs.selectColor(driver);
		cbs.helpButtonClick(driver);
		cbs.onlineChatSupportButtonClick(driver);
		cbs.visitButtonClick(driver);
		System.out.println(cbs.windowsCount(driver));
		cbs.collectionOfWindowTitle(driver);
		cbs.getWindowTitle(driver,"Visit Us");
		driver.switchTo().window(countwindow.get(0));
		//driver.close();
		
	}

}
