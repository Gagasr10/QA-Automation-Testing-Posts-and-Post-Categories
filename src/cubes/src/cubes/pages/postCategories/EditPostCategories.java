package cubes.pages.postCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPostCategories {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@name='name']")
	WebElement weEditPostCategoriesName;
	
	@FindBy(xpath = "//textarea[@name='description']")
	WebElement weEditDescriptionPostCategories;
	
	@FindBy(xpath = "//div[@class='invalid-feedback']")
	WebElement weEditNameError;
	
	@FindBy (xpath = "//h1")
	WebElement weEditPostCategorisTitle;
	
		
	
	public EditPostCategories (WebDriver driver) {
		
		this.driver = driver;
		this.driver.manage().window().maximize();
		
		PageFactory.initElements(driver, this);
	}

	public boolean isEditPostTitleDisplayed() {
		return weEditPostCategorisTitle.isDisplayed();
	}
	
	public String getEditPostTextTitle() {
		return weEditPostCategorisTitle.getText();
	}
	
	public String getNameErrorText() {
		return weEditNameError.getText();
	}
	
	public boolean isNameErrorDisplayed() {
		return weEditNameError.isDisplayed();
	}
	
}
