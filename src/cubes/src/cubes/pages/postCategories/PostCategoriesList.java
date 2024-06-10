package cubes.pages.postCategories;

import java.time.Duration;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Cubes.main.Utils;
import cubes.main.URLConst;

public class PostCategoriesList {
	
	private WebDriver driver;
	
	@FindBy(partialLinkText = "Add new Category")
	private WebElement weAddNewCategory;
	
	@FindBy (xpath = "//button[@data-action = 'show-order']")
	private WebElement WeChangeOrder;
	
	@FindBy (xpath = "//button[@data-action='hide-order']")
	private WebElement WeCancelOrder;
	
	@FindBy (xpath = "//button[@class='btn btn-outline-success']")
	private WebElement weSaveOrder;
	
	@FindBy(xpath = "//tbody/tr/td[1]/span/i[@xpath='1']")
	private WebElement postCategoryA;
	
	@FindBy(xpath = "//tbody/tr/td[1]/span/i[@xpath='2']")
	private WebElement postCategoryB;
	
	
	////tbody/tr/td[1]/span/i[@xpath='1']
	
	
		
	public PostCategoriesList (WebDriver driver) {
		
		this.driver = driver;
		this.driver.manage().window().maximize();
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void openPage() {
		this.driver.get(URLConst.POST_CATEGORIES_LIST);;
	}
	
	
	public void clickAddNewCategory() {
		weAddNewCategory.click();
	}
	
	public void clickOnDeleteCategory(String name) {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(name)));
		deleteButton.click();
		
	}
	
	public void deleteCategory(String name) throws InterruptedException {
		WebElement deleteButton = driver.findElement(By.xpath("//button[@data-name='"+name+"']"));
		deleteButton.click();
		
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
			
		WebElement dialogDeleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
		dialogDeleteButton.click();
						
		}
	
	public void clickOnDeleteDialogDelete() {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(null)));
		deleteButton.click();
	}
	
	public void clickOnDeleteDialogCancel() {
		WebElement cancelButton = driver.findElement(By.xpath("//button[text()='Cancel']"));	
		cancelButton.click();
	}
	
	public void clickOnUpdatePostCategory(String name) {
		WebElement updateButton = driver.findElement(By.xpath(getUpdateButtonLocator(name)));
	//	Utils.scrollToElement(driver, updateButton);
		updateButton.click();
	}
	
	public int countPostCategoryWithName(String name) {
		List<WebElement> wePostCategoryList = driver.findElements(By.xpath("//strong[text()='"+ name+"']"));
		
		return wePostCategoryList.size();
					
	}
	
	public int countPostCategoryWithDescription(String name) {
		List<WebElement> wePostCategoryDescriptionList = driver.findElements(By.xpath("//strong[text()='"+ name+"']"));
		
		return wePostCategoryDescriptionList.size();
	}
	
	public boolean isPostCategoryWithNameInList(String name) {
		List<WebElement> wePostCategoryList = driver.findElements(By.xpath("//strong[text()='"+ name+"']"));
	
		return wePostCategoryList.size()>0;
				
	}
	
	private String getDeleteButtonLocator (String name) {
			return "//button[@data-name='"+name+"']";
			//return "//strong[text()='"+name+"']//ancestor::tr//td[6]//button";
			
		}
		
		private String getUpdateButtonLocator(String name) {
			return "//strong[text()='"+name+"']//ancestor::tr//td[6]//a[2]";
			
		
		}
		
		public void changeOrderCancel() {
			WeChangeOrder.click();
			WeCancelOrder.click();
		}

		public void changeOrderSave() {
			WeChangeOrder.click();
			weSaveOrder.click();
		}
		
		public void orderCancel() {
			WeCancelOrder.click();
		}
		
		public void orderSave() {
			weSaveOrder.click();
		}
		
		public void changeOrder() {
			WeChangeOrder.click();
		}
		
		
		public void dragAndDrop()  {
			
			
			driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/i[1]")).click();
			Actions a = new Actions(driver);
			WebElement source = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]"));
			WebElement target = driver.findElement(By.xpath("//tbody/tr[2]/td[1]/span[1]"));
		
			
//			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5)); 
//			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/span[1]/i[1]")));
			
			a.dragAndDrop(source, target).build().perform();
					
		}
}
