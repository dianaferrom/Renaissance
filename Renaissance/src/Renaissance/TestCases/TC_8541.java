package Renaissance.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class TC_8541 {
 
	 public static void main(String[] args) throws InterruptedException { 
	  
		 // create object of webdriver
		 WebDriver driver = new FirefoxDriver();
	  
		 driver.manage().window().maximize();
		 //open url
		 driver.get("https://prestage3.renlearn.com/ps30"); 
		 //click on admin option
		 driver.findElement(By.xpath("//*[@id='btnTeacher']/span[2]")).click();
		 //login as admin
		 driver.findElement(By.xpath("//*[@id='tbUserName']")).sendKeys("admin");
		 driver.findElement(By.id("tbPassword")).sendKeys("admin1");
		 driver.findElement(By.id("btnLogIn")).click();
	  
		 driver.findElement(By.xpath("//*[@id='ctl00_cp_Content_ctl00_ctl00_ctl03_divName']/span")).click();
		 driver.findElement(By.linkText("Growth Expectation Extract")).click();
	  
		 driver.findElement(By.id("ctl00_cp_Content_cblApps_0")).click();
		 driver.findElement(By.id("ctl00_cp_Content_cblApps_1")).click();
		 driver.findElement(By.id("ctl00_cp_Content_cblApps_2")).click();
	  
		 driver.findElement(By.className("btnGenExtract")).click();
	 
		 try{
			 while(driver.findElement(By.xpath("//*[@id='ctl00_cp_Content_exHistoryList_ctl00_spExtract']")).isDisplayed())
				 driver.findElement(By.id("ctl00_cp_Content_btnRefresh")).click();
		 }
		 catch(Exception e){
			 System.out.println(e.getMessage());
		 }

		 driver.findElement(By.xpath("//*[@id='ctl00_cp_Content_exHistoryList_ctl00_lbExtract']")).click();
		 
	 }
}