package Renaissance.TestCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TC_9137 {

	public static void main(String[] args) {
		
		WebDriver driverFF = new FirefoxDriver();
		
		String url = args[0].toString();
		String user = args[1].toString();
		String password = args[2].toString();
		String school = args[3].toString();
		String cl = args[4].toString();	
		String student = args[5].toString();
		String book = args[6].toString();
		
		driverFF.manage().window().maximize();
		driverFF.navigate().to(url);

		driverFF.findElement(By.xpath(".//*[@id='btnTeacher']/span[2]")).click();
		driverFF.findElement(By.xpath(".//*[@id='tbUserName']")).sendKeys(user);
		driverFF.findElement(By.xpath(".//*[@id='tbPassword']")).sendKeys(password);
		driverFF.findElement(By.id("btnLogIn")).click();
		
		driverFF.findElement(By.xpath(".//*[@id='app-ar']")).click();
		driverFF.findElement(By.linkText("Record Books and Goals")).click();
		
		//Select the school
		Select selectSchool = new Select(driverFF.findElement(By.xpath(".//*[@id='mSchoolClassListControl_DropDown_Schools']")));
		selectSchool.selectByVisibleText(school);;
		
		//Select the class
		Select selectClass = new Select(driverFF.findElement(By.xpath(".//*[@id='mSchoolClassListControl_DropDown_ClassList']")));
		selectClass.selectByVisibleText(cl);
		
		driverFF.findElement(By.linkText("Reading Practice Quizzes")).click();
		
		Select selectClass2 = new Select(driverFF.findElement(By.xpath(".//*[@id='DropDownList_RecordBookClasses']")));
		selectClass2.selectByVisibleText(cl);
		
		WebElement baseTable = driverFF.findElement(By.className("tableSetting"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		
		if(tableRows.size() == 3)
			System.err.println("There are no students enrolled in this class");
		else{
			driverFF.findElement(By.linkText(student)).click();
			sleep(5);
			
			if(tableRows.size() == 1)
				System.err.println("There is no data available for this student ");
			else{

				List<WebElement> elementsTableQuiz = driverFF.findElements(By.className("columnText")); 
			//	System.out.println(elementsTableQuiz.size());
				
				 for(int i=0; i<elementsTableQuiz.size(); i++)
					 if(elementsTableQuiz.get(i).getText().isEmpty() )
						 elementsTableQuiz.remove(i);
				 
			//	 System.out.println(elementsTableQuiz.size());
				for(int i=0; i<elementsTableQuiz.size(); i++){
					if(elementsTableQuiz.get(i).getText().replace("\n", " ").equalsIgnoreCase(book)){
						System.out.println(elementsTableQuiz.get(i).getText().replace("\n", " "));
						driverFF.findElement(By.xpath(".//*[@id='Repeater_StudentRPQuizzesRB_ctl0" + (i-1) + "_TableRow_StudentRPQuizzesRB']/td[7]/a[1]")).click();
						break;
					}
				}
				screenshot(driverFF, "DeleteQuiz");
				
				
				//JS
				try{
					WebDriverWait wait = new WebDriverWait(driverFF, 10);
					Alert alert = wait.until(ExpectedConditions.alertIsPresent());
					
					alert.accept();
				}catch(Throwable e){
					System.err.println("Error came while waiting for the aler popup. " + e.getMessage());
				}
				driverFF.findElement(By.linkText("Return to Renaissance Place")).click();
			}
		}
		
	}
	
	public static void sleep(int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void screenshot(WebDriver driver, String screenshotName){
		Date date = new Date();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = "C:\\Selenium\\screenshot_" + screenshotName + date.toString().replace(" ", "-").replace("-CST-2016", "").replace(":", "-") + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
