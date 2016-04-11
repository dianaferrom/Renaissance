package Renaissance.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import Renaissance.PageFactory.Login;

public class TC_4639 {
	
	static Login login;

	public static void main(String[] args) {
		String browser = args[0].toString();
		String url = args[1].toString();
		String user = args[2].toString();
		String password = args[3].toString();
		
		WebDriver driver;
		
		switch(Integer.parseInt(browser)){
			case 1: //Chrome
				System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver.exe");
				driver = new ChromeDriver();
			break;
			
			case 2://Explorer
				System.setProperty("webdriver.ie.driver","C:\\Selenium\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			break;
			
			default://Firefox
				driver = new FirefoxDriver();
			break;
		}
		
		driver.manage().window().maximize();
		driver.navigate().to(url);
		
		login = new Login(driver);
		login.loginTeacherAdmin(user, password);
		
	}

}
