package cubes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cubes.main.URLConst;

public class LoginPage {

	private WebDriver driver;
	
	@FindBy(name = "email")
	private WebElement weEmail;
	@FindBy(name = "password")
	private WebElement wePassword;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement weSignIn;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.get(URLConst.LOGIN);
		
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		this.driver.get(URLConst.LOGIN);
	}
	
	public void insertEmail(String email) {
		weEmail.clear();
		weEmail.sendKeys(email);
	}
	
	public void insertPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		weSignIn.click();
	}
	
	public boolean isEmailErrorDisplayed() {
		WebElement weEmailError = driver.findElement(By.xpath("//p[@id='email-error']"));
		return weEmailError.isDisplayed();
	}
	
	public boolean isPasswordErrorDisplayed() {
		WebElement wePasswordError = driver.findElement(By.xpath("//p[@id='password-error']"));
		return wePasswordError.isDisplayed();
	}
	
	public String getEmailErrorText() {
		WebElement weEmailError = driver.findElement(By.xpath("//p[@id='email-error']"));
		return weEmailError.getText();
	}
	
	public String getPasswordErrorText() {
		WebElement wePasswordError = driver.findElement(By.xpath("//p[@id='password-error']"));
		return wePasswordError.getText();
	}
	
	public void login(String email, String password) {
		insertEmail(email);
		
		insertPassword(password);
		
		clickOnSignIn();
	}
	
	public void loginSuccess() {
		login("kursqa@cubes.edu.rs", "cubesqa");
	}
}
