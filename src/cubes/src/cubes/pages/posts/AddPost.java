package cubes.pages.posts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.main.URLConst;
import cubes.main.Utils;

public class AddPost {
	private WebDriver driver;
	
	
	@FindBy (xpath = "//select[@name='post_category_id']")
	private WebElement chooseCategory;
	
	@FindBy (xpath = "//input[@name='title']")
	private WebElement wePostTitle;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement wePostDescription;
	
	@FindBy (xpath = "//label[contains(text(),'No')]")
	private WebElement importantNo ;
	
	@FindBy (xpath = "//label[contains(text(),'Yes')]")
	private WebElement importantYes ;
	
	@FindBy (xpath = "//input[@name='photo']")
	private WebElement chooseNewPhoto;
	
	@FindBy (xpath = "//body//p")
	private WebElement content;
	
	@FindBy(xpath = "//span[@id='title-error']")
	private WebElement weTitleError;
	
	@FindBy (xpath = "//span[@id='description-error']")
	private WebElement weDescriptionError;
	
	@FindBy(xpath = "//span[@id='tag_id[]-error']")
	private WebElement weTagError;
	
	@FindBy(xpath = "//div[contains(text(),'The content field is required.')]")
	private WebElement contentError;
	
	@FindBy(xpath = "//div[@class='card-footer']/button")  
	private WebElement weSave;
	
		
	@FindBy(xpath = "//div[1]/select[1]")
	private WebElement weChooseCategory;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement tag;
	
	
	
	//driver.findElements(By.xpath("//strong[text()='"+name+"']"));
	
	
	
	public AddPost(WebDriver driver, boolean openPage) {
		this.driver = driver;
		this.driver.manage().window().maximize();
						
		if(openPage) {
			this.driver.get(URLConst.ADD_POST);
		}
		
		PageFactory.initElements(driver, this);
		
	}
	// these is for postList
	
	
		public void insertContent (String text) {
					
			moveDown();

			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[1]")));
			driver.findElement(By.cssSelector("body")).sendKeys(text);
			driver.switchTo().parentFrame();
		  			
		}
		
		public void insertPostTitle(String postName ) {
			wePostTitle.clear();
			wePostTitle.sendKeys(postName);
		}
		
		
		
		public void insertPostDescription (String postDescription) {
			wePostDescription.clear();
			wePostDescription.sendKeys(postDescription);
		}
		
		public void checkboxImportantYes () {
			importantYes.click();
			
		}
		
		public void checkboxImportantNo () {
			importantNo.click();
		}
	
		public void addPhoto () {
			WebElement addNewPhoto = driver.findElement(By.xpath("//input[@name='photo']"));
			String filePath = "C:\\Users\\Admin\\OneDrive\\Pictures\\Saved Pictures\\you got this.jpg";
			addNewPhoto.sendKeys(filePath);
			
		}
		
		public void addPhotoPNEG () {
			WebElement addNewPhoto = driver.findElement(By.xpath("//input[@name='photo']"));
			String filePath = "C:\\Users\\Admin\\OneDrive\\Pictures\\Saved Pictures\\—Pngtree—hand painted hello summer sun_5348898.png";
			addNewPhoto.sendKeys(filePath);
		}
		
		public void clickOnSave () {
					
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(weSave));
			moveDown();
		
			weSave.submit();
		}
		
		public void clickOnCancel() throws InterruptedException {
			
			 WebElement target = driver.findElement(By.xpath("//a[contains(text(),'Cancel')]"));
			 						
			 Actions actions = new Actions(driver);
			 actions.scrollToElement(target).perform();;
					
			 WebElement cancelButton = driver.findElement(By.xpath("//a[contains(text(),'Cancel')]"));
																	
			 cancelButton.click();
						
		}
		
		public boolean isTitleErrorDisplayed() {
			return weTitleError.isDisplayed();
		}
		
		public String getTitleErrorText(){
			return weTitleError.getText();			
			
		}
		
		public boolean isDescriptionErrorDisplayed() {
			return weDescriptionError.isDisplayed();
		}
		
		public String getDescriptionErrorText()
		{
			return weDescriptionError.getText();			
			
		}
		
		public boolean isTagErrorDisplayed() {
			return weTagError.isDisplayed();
		}
		
		public String getTagErrorText() {
			
			return weTagError.getText();
		}
		
		public boolean isContentErrorDisplayed() {
			return contentError.isDisplayed();
		}
		
		public String getContentErrorText() {
			return content.getText();
		}
		
		public void moveDown() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			js.executeScript("window.scrollBy(0,500)");
		
		}
		
		public void chooseCategory(int index) {
			
			WebElement staticDropdown = weChooseCategory;
			
			Select chooseCategory = new Select(staticDropdown);
			chooseCategory.selectByIndex(index);
									
		}
		
		public void selectTag() throws InterruptedException  {
			
				
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,450)");
			
			Thread.sleep(1000);
					
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		}
		
		public void selectAllTags() throws InterruptedException {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,450)");
			
			Thread.sleep(1000);
								
			List<WebElement> checkbox=driver.findElements(By.cssSelector("input[type='checkbox']"));

				int size = checkbox.size();
					for (int i=0; i<size; i++) {

					checkbox.get(i).click();
				}
		}

		public boolean selectTagIsEnabled() {
			return tag.isEnabled();
			
		}
		
		public boolean seletTagIsSelected() {
			return	tag.isSelected();
		}
		
		

}
