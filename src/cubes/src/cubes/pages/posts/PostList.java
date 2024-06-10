package cubes.pages.posts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.main.URLConst;
import cubes.main.Utils;

public class PostList {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//div[1]/div[1]/a[1]")
	private WebElement weAddNewPost;
	
	@FindBy(xpath = "//div/input[1]")
	private WebElement weSearchByTitle;
	
	@FindBy(xpath = "//span[@title='--Choose Author --']")
	private WebElement weChooseAuthor;
	
	@FindBy(xpath = "//span[@title='--Choose Category --']")
	private WebElement weChooseCategory;
		
	@FindBy (xpath = "//select[@name='important']")
	private WebElement weChooseImportant;
	
	@FindBy(xpath = "//option[contains(text(),'yes')]")
	private WebElement weImportantYes;
	
	@FindBy(xpath = "//option[contains(text(),'no')]")
	private WebElement weImportantNo;
	
	@FindBy(xpath = "//select[@name='status']")
	private WebElement weChooseStatus;
	
	@FindBy (xpath = "//select[@name='status']")
	private WebElement weStatusEnabled;
	
	@FindBy(xpath = "//option[contains(text(),'disabled')]")
	private WebElement weStatusDisabled;
	
	@FindBy(xpath = "//select[@name='tag_ids']")
	private WebElement weWithTag;
	//select[name='tag_ids']
	
	@FindBy(xpath = "//div[1]/label[1]/input[1]")
	private WebElement weSearch;
	
	@FindBy(xpath = "//li[@id='entities-list-table_next']")
	private WebElement next;
	
	
	
public PostList (WebDriver driver) {
		
		this.driver = driver;
		this.driver.manage().window().maximize();
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void openPage() {
		this.driver.get(URLConst.POST_LIST);;
	}
	
	public void clickAddNewPost() {
		weAddNewPost.click();
	}
	
	public void chooseAuthor(int index) {
		
		WebElement staticDropdown = weChooseAuthor;
					
		Select chooseAuthor = new Select(staticDropdown);
				
		chooseAuthor.selectByIndex(index);
								
	}
	
	
	public void chooseCategory(int index) {
		weChooseCategory.click();
		WebElement staticDropdown = weChooseCategory;
		
		Select chooseCategory = new Select(staticDropdown);
		chooseCategory.selectByIndex(index);
								
	}
	
	public void chooseImportant(String text) {
		weChooseImportant.click();
		
		WebElement staticDropdown = driver.findElement(By.xpath("//select[@name='important']"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText(text);
				
	}
	
	
	public void chooseStatus(String text) {
		 Actions actions = new Actions(driver);
		
		actions.scrollToElement(weChooseStatus).perform();;
				
		weChooseStatus.click();
				
		WebElement staticDropdown = weChooseStatus;
		Select chooseStatus =  new Select(staticDropdown);
		chooseStatus.selectByVisibleText(text);
	
	}
	
	public void withTag(int index) {
	 driver.findElement(By.xpath("//form[1]/div[1]/div[6]/span[1]/span[1]/span[1]/ul[1]")).click();
		
		WebElement staticDropdown = weWithTag;
		
		Select withTag = new Select(staticDropdown);
		withTag.selectByIndex(index);
				
	}
	 public void withAllTag() {
		 driver.findElement(By.xpath("//form[1]/div[1]/div[6]/span[1]/span[1]/span[1]/ul[1]")).click();
		 
		 List<WebElement> tags =driver.findElements(By.xpath("//select[@name='tag_ids']/option"));

			int size = tags.size();
				for (int i=0; i<size; i++) {

				tags.get(i).click();
				
				//option[contains(text(),'Tag')]"
			}
	}
		
		 
	 
	
	public void clickOnDeletePost(String name) {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(name)));
		
		deleteButton.click();
	}
	
	public void clickDisablePost(String name) {
		WebElement disablePost = driver.findElement(By.xpath(getDisablePostLocator(name)));
		WebElement target = driver.findElement(By.xpath("//td[text()='"+name+"']//ancestor::tr//td[12]/div[2]/button[1]/i[1]"));
		
		Actions actions = new Actions(driver);
		
		actions.scrollToElement(target).perform();;
		disablePost.click();
		
	}
	
	public void confirmDisablePost() throws InterruptedException {
		WebElement confirmDisable = driver.findElement(By.xpath("//div[1]/form[2]/div[1]/div[1]/div[3]/button[1]"));
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
	//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Disable')]")));;
		confirmDisable.click();
	}
	
	
	public void clickOnCancelDisablePost() throws InterruptedException {
				
		WebElement cancelDisable = driver.findElement(By.xpath("//form[2]/div[1]/div[1]/div[3]/button[1]"));
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[2]/div[1]/div[1]/div[3]/button[1]")));
//							
		cancelDisable.click();
	}
	
		
	public void clickOnImportantPost(String name) throws InterruptedException {
		WebElement unImportantPost = driver.findElement(By.xpath(getUnimportantPostLocator(name)));
		WebElement target = driver.findElement(By.xpath("//td[text()='"+name+"']//ancestor::tr//td[12]/div[2]/button[2]"));
	
		Actions actions = new Actions(driver);
		
		actions.scrollToElement(target).perform();;
		
		unImportantPost.click();
		
	}
	
	public void confirmOnUnimportantPost() {
		WebElement confirmUnImportantPost = driver.findElement(By.xpath("//form[4]/div[1]/div[1]/div[3]/button[2]"));
		confirmUnImportantPost.click();
	}
	
	public void cancelImportantPost() {
		WebElement cancelUnimportant = driver.findElement(By.xpath("//form[4]/div[1]/div[1]/div[3]/button[1]"));
		cancelUnimportant.click();
	}
	
		
	public void clickOnDeleteDialogDelete() {
		WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
		deleteButton.click();
	}
	
	public void clickOnDeleteDialogCancel() {
		WebElement cancelButton = driver.findElement(By.xpath("//button[text()='Cancel']"));
		cancelButton.click();
	}
	
	public void clickOnUpdatePost(String name) throws InterruptedException  {
				
		WebElement updateButton = driver.findElement(By.xpath(getUpdateButtonLocator(name)));
						
		updateButton.click();
	}
	
	public void deletePost(String name) {
		
		clickOnDeletePost(name);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
		
		clickOnDeleteDialogDelete();
		
	}

	public int countPostWithName(String name) {
		List<WebElement> wePostList = driver.findElements(By.xpath("//td[text()='"+name+"']"));
			
		return wePostList.size();
	}
	
	public boolean isPostWithNameInList(String name) {
		List<WebElement> wePostList = driver.findElements(By.xpath("//td[text()='"+name+"']"));

		return wePostList.size()>0;
	}
	
	public List<WebElement> getPostWithNameList(String name){
		return driver.findElements(By.xpath("//td[text()='"+name+"']"));
	}
			
	public void moveDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,500)");
	
	}
	public void zoomOutPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
			
		js.executeScript("document.body.style.zoom = '0.8'");
			
	
	}
	
	 public static void scrollToLastTH(WebDriver driver) {
	        // Execute JavaScript to scroll to the last th element 
	        ((JavascriptExecutor) driver).executeScript("document.querySelector('th:last-child').scrollIntoView(true);");
	    }
	
	
	private String getDeleteButtonLocator(String name) {
	
		return "//td[text()='"+name+"']//ancestor::tr//td[12]//button";
	}
	
	private String getUpdateButtonLocator(String name) {
		
		return "//td[text()='"+name+"']//ancestor::tr//td[12]//div[1]//a[2]";
	}
	
	
	private String getDisablePostLocator(String name) {
		
		return "//td[text()='"+name+"']//ancestor::tr//td[12]/div[2]/button[1]/i[1]";
	}
	
	private String getUnimportantPostLocator(String name) {
		
		return "//td[text()='"+name+"']//ancestor::tr//td[12]/div[2]/button[2]";
		
	}
	
	public void searchByTitle(String text) {
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search by title']"));
		search.sendKeys(text);
		search.sendKeys(Keys.ENTER);
			
	}
	
	public void searchByAuthor (String text) {
		weChooseAuthor.click();
		
		WebElement staticDropdown = driver.findElement(By.xpath("//select[@name='user_id']"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText(text);
		}
		
			
	public void searchByCategory (int num) {
		weChooseCategory.click();
	
		WebElement staticDropdown = driver.findElement(By.xpath("//select[@name='post_category_id']"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(num); 
	}
	
	public void searchText(String text) {
		
		weSearch.sendKeys(text);
		
	}
	
	 		 
	 public void actions(WebDriver driver,String name)  {
		 WebElement target = driver.findElement(By.xpath("//td[text()='"+name+"']//ancestor::tr//td[12]//button"));
	
		 Actions actions = new Actions(driver);

		 actions.scrollToElement(target).perform();
	
	 }
	
	}



