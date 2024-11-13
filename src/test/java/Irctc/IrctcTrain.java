package Irctc;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class IrctcTrain{
	 public static WebDriver driver;
	 public static WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(10));
  	
		@BeforeClass		
		   public void launch() { 
			System.out.println("Enter the browser name:");
			Scanner ss=new Scanner(System.in);
			String browser=ss.nextLine();
			if(browser.equalsIgnoreCase("chrome")) {
				
			ChromeOptions option=new ChromeOptions();
			//option.addArguments("--disable-notifications");
			//option.addArguments("--disable-popup-blocking");
			driver=new ChromeDriver(option);

			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
				EdgeOptions option1=new EdgeOptions();
				//option1.addArguments("--disable-notifications");
				//option1.addArguments("--disable-popup-blocking");
				driver=new EdgeDriver(option1);
			}
			else {
				return;
			}
			driver.get("https://www.irctc.co.in/"); 
			  driver.manage().window().maximize(); 
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	// @Test(priority=2)
	//Login to the website
	// public void logInMethod() throws InterruptedException
	//{
	//	driver.findElement(By.xpath("//div[@class='h_menu_drop_button hidden-xs']//i[@class='fa fa-align-justify']")).click();
	//	WebElement loginButt=driver.findElement(By.cssSelector("button[class='search_btn']"));
	//	loginButt.click();
	//	Thread.sleep(3000);
	  //  WebElement username=driver.findElement(By.xpath("//input[@class='form-control input-box ng-pristine ng-valid ng-touched']"));
	//    username.click();
	 //   username.sendKeys("jaggu1631");
	//    WebElement password=driver.findElement(By.xpath("//input[@class='form-control input-box ng-untouched ng-pristine ng-valid']"));
	//    password.click();
	//    password.sendKeys("Jaggu1631@#");
	//    Thread.sleep(10000);
	//    driver.findElement(By.xpath("//button[normalize-space()='SIGN IN']")).click();
	//}
	
	 
	 
	 
      @Test(priority=3)

	 //validate the correct page opened or not of website
	 public void validatePage()
	{
		String expectedTitle = "IRCTC Next Generation eTicketing System";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Correct page opened:" + actualTitle);
		}
		else {
			System.out.println("Incorrect page opened:" + actualTitle);
		}
	}
	//handle the alert if present
     
	public void handleAlert()
	{
		try {
			Alert myalert = driver.switchTo().alert();
			myalert.accept();
			System.out.println("Alert handled");
		} 
		catch (NoAlertPresentException e) {
			System.out.println("No Alert Present");
		}
	}
		 
	//send the value of from place using apache poi and send the value of to place using apache poi
    @Test(priority=4)

	public void fromValue() throws IOException
	{
		String path=System.getProperty("user.dir")+"//Data//Book 2.xlsx";
		UtilityFiles ut=new UtilityFiles();
		String fromDeparture=ut.getCellValue(path,0,1,0);
		String toDestination=ut.getCellValue(path,0,1,1);
		 WebElement from=driver.findElement(By.xpath("//input[@class='ng-tns-c57-8 ui-inputtext ui-widget ui-state-default ui-corner-all ui-autocomplete-input ng-star-inserted']")); 
		  from.click();  
		  from.sendKeys(fromDeparture); 
		  WebElement fromName=driver.findElement(By.xpath("//span[@class='ng-star-inserted' and  text()=' HYDERABAD DECAN - HYB ']"));
		  fromName.click();
	
		WebElement to=driver.findElement(By.xpath("//span[@class=\"ng-tns-c57-9 ui-autocomplete ui-widget\"]/input"));
		to.click(); //Thread.sleep(5000); 
		to.sendKeys(toDestination); 
		WebElement toName=driver.findElement(By.xpath("//span[@class='ng-star-inserted'][text()=' PUNE JN - PUNE ']"));
		toName.click();
	}
    @Test(priority=5)
	//choose the date from today to after 4 days
	public void chooseDate()
	{
		driver.findElement(By.xpath("//input[@class='ng-tns-c58-10 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']")).click();
	    LocalDate futureDate = LocalDate.now().plusDays(4);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String formattedDate = futureDate.format(formatter);
	    String day = String.valueOf(futureDate.getDayOfMonth());
	    String month = String.valueOf(futureDate.getMonthValue()); // Months are 0-indexed in date picker
	    int month1=Integer.parseInt(month);
	    String year = String.valueOf(futureDate.getYear());
	   String monthname= Month.of(month1).name();
	   while(true)
		{
			String currentMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month ng-tns-c58-10 ng-star-inserted']")).getText();//actutal month
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year ng-tns-c58-10 ng-star-inserted']")).getText();//actual year
					
			if(currentMonth.equalsIgnoreCase(monthname) && currentYear.equalsIgnoreCase(year))
				{
						break;
				}
					
			driver.findElement(By.xpath("//span[@class='ui-datepicker-next-icon pi pi-chevron-right ng-tns-c58-10']")).click();// Next
		}
		
		List<WebElement> allDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar ng-tns-c58-10']//tbody//tr//td//a"));
		
		for(WebElement dt:allDates)
			{
				if(dt.getText().equals(day))
				{
					dt.click();
					break;
				}
			}
	}
    @Test(priority=6)

	//selecting the classes from the train(Sleeper)
	public void selectClasses() throws InterruptedException
	{
		WebElement allClasses=driver.findElement(By.id("journeyClass"));
		allClasses.click();
		WebElement coachClass=driver.findElement(By.xpath("//span[@class='ng-star-inserted'][text()='Sleeper (SL)']"));
		coachClass.click();
		//Thread.sleep(3000);
	}
	
    @Test(priority=7)

	//clicking the checkbox with person with disability concession
	public void selectCheckBox() throws InterruptedException
	{
		Thread.sleep(3000);
		 WebElement checkBox=driver.findElement(By.xpath("//label[normalize-space()='Person With Disability Concession']"));
		 checkBox.click(); 
		 //Thread.sleep(2000);
		 WebElement okButton=driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable']"));
		 okButton.click();
	    //Thread.sleep(3000);
	}
	
    @Test(priority=8)

	//click on search button to find trains
	public void findSearchButton() throws InterruptedException
	{ 
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();	}
    
    @Test(priority=9)
	//list of trains names and count of trains
	public void listOfTrainNamesCount()
	{
		 List<WebElement> trainsList=driver.findElements(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']"));
		 System.out.println("No of Trains:"+trainsList.size());
		 System.out.println("Names of the Trains:");
		 for(WebElement list:trainsList)
		 {
		   String train = list.findElement(By.tagName("Strong")).getText();
		   System.out.println(train); 
		 }
	}
	
	
	//screenshot of the trains
	public void screenShot() throws IOException
	{
		 String timestamp=new SimpleDateFormat("yyyymmddHHmmss").format(new Date()); String
		 filename=timestamp+"_";
		 TakesScreenshot ts=(TakesScreenshot) driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 File destination=new File("Screenshots\\"+filename+".png");
		 Files.copy(source, destination);
		 	}

}
