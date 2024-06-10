package cubes.pages.postCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cubes.main.URLConst;
import cubes.main.Utils;

public class AddPostCategory {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@name='name']")
	private WebElement wePostCategoryName;
	
	@FindBy (xpath = "//textarea[@name='description']")
	private WebElement weDescriptionPostCategory ;
	
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement weSavePostCategory;
	
	@FindBy (xpath = "//div[@class='card-footer']/a")
	private WebElement weCancelPostCategory;
	
	@FindBy (id = "name-error")
	private WebElement weNameError;
	
	@FindBy (id = "description-error")
	private WebElement weDescriptionError;
	
	@FindBy (xpath = "//div[@class='invalid-feedback']")
	private WebElement weDescriptionLengthError;
	
	public AddPostCategory (WebDriver driver, boolean openPage) {
		this.driver = driver;
		this.driver.manage().window().maximize();
						
		if(openPage) {
			this.driver.get(URLConst.ADD_POST_CATEGORIES);
		}
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void insertPostCategoryName(String postCategoryName ) {
		wePostCategoryName.clear();
		wePostCategoryName.sendKeys(postCategoryName);
	}
	
	public void insertPostCategoryDescription (String description) {
		weDescriptionPostCategory.clear();
		weDescriptionPostCategory.sendKeys(description);
	}
	
	public void clickOnSave () {
		weSavePostCategory.click();
	}
	
	public void clickOnCancel() {
		weCancelPostCategory.click();
	}
	
	public boolean isErrorNameDisplayed() {
		return weNameError.isDisplayed();
	}

	public boolean isErrorDescriptionDisplayed() {
		return weDescriptionError.isDisplayed();
	}
	
	public String getNameErrorText() {
		return weNameError.getText();
	}
	
	
	public String getDescriptionNameErrorText() {
		return weDescriptionError.getText();
	}
	
	public String getPostCategoryNameText() {
		return wePostCategoryName.getAttribute("value");
	}
	
	public String getPostCategoryDescriptionText() {
		return weDescriptionPostCategory.getAttribute("value");
	}
	
	public boolean isErrorDescriptionLengthDisplayed() {
		return weDescriptionLengthError.isDisplayed();
		
	}
	
	public String getDescriptionLengthError() {
		return weDescriptionLengthError.getText();
	}
	
	
	public void addNewPostCategory (String name) {
		insertPostCategoryName(name);
		clickOnSave();
		
	}
	
	public String addNewPostCategory () {
		String tempPostCategoryName = Utils.getRandomPostCategoryName();
		return tempPostCategoryName;
	}
	
	
	
}
