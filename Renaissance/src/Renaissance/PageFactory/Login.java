package Renaissance.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;
	
	@FindBy(xpath=".//*[@id='btnTeacher']/span[2]")
	WebElement btnTeacherAdmin; 
	
	@FindBy(xpath=".//*[@id='btnStudent']/span[2]")
	WebElement btnStudent;
	
	@FindBy(xpath=".//*[@id='ctl00_cp_Content_btnParent']/span[2]")
	WebElement btnParent;
	
	@FindBy(xpath=".//*[@id='tbUserName']")
	WebElement lblUserName;
	
	@FindBy(xpath=".//*[@id='tbPassword']")
	WebElement lblPassword;
	
	@FindBy(id="btnLogIn")
	WebElement btnLogin;
	
	public Login(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String userName){
		lblUserName.sendKeys(userName);
	}
	
	public void setPassword(String password){
		lblPassword.sendKeys(password);
	}
	
	public void loginTeacherAdmin(String user, String pass){
		btnTeacherAdmin.click();
		this.setUserName(user);
		this.setPassword(pass);
		btnLogin.click();
	}
	
	public void loginStudent(String user, String pass){
		btnStudent.click();
		this.setUserName(user);
		this.setPassword(pass);
		btnLogin.click();
	}
	
	public void loginParent(String user, String pass){
		btnParent.click();
		this.setUserName(user);
		this.setPassword(pass);
		btnLogin.click();
	}
	
}
