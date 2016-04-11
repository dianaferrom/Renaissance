package Renaissance.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_8780 {

	public static void main(String[] args) {
		TC_8780 d = new TC_8780();
		
		WebDriver driverFF = new FirefoxDriver();
		String url = "http://prestage3.renlearn.com/ps30/";
		String user = "admin" , password = "admin1";
		String school = "Renaissance Place Texas School 2", teacher = "Bill Tang", classGroup = "Jims group", student = "Zeke Dog";
		boolean studentType = true;
		
		driverFF.manage().window().maximize();
		driverFF.navigate().to(url);

		driverFF.findElement(By.xpath(".//*[@id='btnTeacher']/span[2]")).click();
		driverFF.findElement(By.xpath(".//*[@id='tbUserName']")).sendKeys(user);
		driverFF.findElement(By.xpath(".//*[@id='tbPassword']")).sendKeys(password);
		driverFF.findElement(By.id("btnLogIn")).click();
		
		driverFF.findElement(By.xpath(".//*[@id='app-reading-dashboard']")).click();
		ImplicitWait(10, driverFF);
		driverFF.findElement(By.xpath(".//*[@id='DASHV_CLASSOVERVIEW']/div[1]/div[1]")).click();
		
		ImplicitWait(10, driverFF);
		driverFF.findElement(By.xpath(".//*[@id='subHeader']/div[2]/ng-include/div[1]/rl-sentence/div[1]/span[2]/ul/li/a/span[1]")).click();
		
		d.sleep(5);
		d.selectElement(driverFF, ".//*[@id='0']/div[2]/ul/li", school);
		d.sleep(5);
		d.selectElement(driverFF, ".//*[@id='1']/div[2]/ul/li", teacher);
		d.sleep(5);
		d.selectElement(driverFF, ".//*[@id='2']/div[2]/ul/li", classGroup);
		d.sleep(5);
		if(studentType) d.selectElement(driverFF, ".//*[@id='3']/div[2]/ul/li", student);
		else d.selectElement(driverFF, ".//*[@id='3']/div[2]/ul/div[3]/div/div", student);
		
		driverFF.findElement(By.xpath(".//*[@id='subHeader']/div[2]/ng-include/div[1]/rl-learning-unit-picker/div/div[2]/div/div[2]/button[1]")).click();
		d.sleep(5);
		
		File scrFile = ((TakesScreenshot)driverFF).getScreenshotAs(OutputType.FILE);
		String path = "C:\\Selenium\\screenshot_DE8780-" + d.date().replace(" ", "-").replace("-CST-2016", "").replace(":", "-") + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectElement(WebDriver driver, String xpath, String type){
		WebElement element;
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		
		for(int i=1; i<=list.size(); i++){
			element = driver.findElement(By.xpath(xpath + "[" + i + "]"));
			
			if(element.getText().equalsIgnoreCase(type)){
			//	ExplicitWait(driver, 30, xpath + "[" + i + "]");
				driver.findElement(By.xpath(xpath + "[" + i + "]")).click();
				break;
			}
		}
	}
	
	public static void ImplicitWait(int seconds, WebDriver driver){
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public static void ExplicitWait(WebDriver driver, int seconds, String xpath){
		(new WebDriverWait(driver, seconds)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String date(){
		Date date = new Date();
		return date.toString();
	}

}
