package cubes.test.postCategories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import cubes.driver.MyWebDriver;
import cubes.main.URLConst;
import cubes.main.Utils;
import cubes.pages.LoginPage;
import cubes.pages.postCategories.AddPostCategory;
import cubes.pages.postCategories.EditPostCategories;
import cubes.pages.postCategories.PostCategoriesList;

class PostCategoriesTest {
	
	private static WebDriver driver;
	private static String newPostCategoryName;
	private static String newPostCategoryDescription;
	private static String updatedPostCategoryName;
	private static String updatedPostCategoryDescription;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	driver = MyWebDriver.getInstance().getDriver("chrome");
		
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	newPostCategoryName = Utils.getRandomPostCategoryName();
	//newPostCategoryDescription = Utils.getRandomPostCategoryDescription(50);
	
	updatedPostCategoryName = Utils.getRandomPostCategoryName();
	updatedPostCategoryDescription = Utils.getRandomPostCategoryDescription(50);
	
	
	LoginPage loginPage = new LoginPage(driver);
	loginPage.loginSuccess();
	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		 driver.close();
	}

	@BeforeEach
	void setUp() throws Exception {
	   driver.get(URLConst.POST_CATEGORIES_LIST);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void tc01() {
		
		AddPostCategory addPostCategory = new AddPostCategory(driver, true);
		
		addPostCategory.insertPostCategoryName("");
		
		addPostCategory.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije dobar");
		
		
	}
	
	@Test
	void tc02 () {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName("");
		
		addPostCategory.clickOnSave();
		
		assertEquals(addPostCategory.isErrorNameDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostCategory.getNameErrorText(), "This field is required.", "Proveriti tekst poruke");
		assertEquals(addPostCategory.getDescriptionNameErrorText(),"This field is required.");
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST_CATEGORIES, "Url nije dobar");
		
	}
	
	@Test 
	void tc03() throws InterruptedException {
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName(newPostCategoryName);
				
		addPostCategory.clickOnCancel();
				
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije ispravan");
		
		
	}
	
	
	
	@Test
	void tc04 () {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName(newPostCategoryName);
		
		addPostCategory.clickOnSave();
		
		assertEquals(addPostCategory.isErrorDescriptionDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostCategory.getDescriptionNameErrorText(), "This field is required.", "Proveriti tekst poruke" );
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST_CATEGORIES, "URL nije ispravan");
	}
	
	@Test
	void tc05()  {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName(newPostCategoryName);
				
		newPostCategoryDescription = Utils.getRandomPostCategoryDescription(10);
		addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
		
		addPostCategory.clickOnSave();
		
		assertEquals(addPostCategory.isErrorDescriptionLengthDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostCategory.getDescriptionLengthError(), "The description must be at least 50 characters.", "Proveriti tekst poruke");
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST_CATEGORIES, "URL nije ispravan");
				
	}
	
	@Test  // boundary testing -> uneto 49. karaktera 
	void tc06() {
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName(newPostCategoryName);
				
		newPostCategoryDescription = Utils.getRandomPostCategoryDescription(49);
		addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
					
		addPostCategory.clickOnSave();
		
		assertEquals(addPostCategory.isErrorDescriptionLengthDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostCategory.getDescriptionLengthError(), "The description must be at least 50 characters.", "Proveriti tekst poruke");
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST_CATEGORIES, "URL nije ispravan");
		
	}
	
	@Test
	void tc07()  {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		
		addPostCategory.insertPostCategoryName("");
				
		newPostCategoryDescription = Utils.getRandomPostCategoryDescription(50);
		addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
					
		addPostCategory.clickOnSave();
				
		assertEquals(addPostCategory.isErrorNameDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostCategory.getNameErrorText(), "This field is required.", "Proveriti tekst poruke");
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST_CATEGORIES, "URL nije ispravan");
				
	}
	
	@Test
	void tc08 () {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		addPostCategory.insertPostCategoryName(newPostCategoryName);
		
		newPostCategoryDescription = Utils.getRandomPostCategoryDescription(50);
		addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
					
		addPostCategory.clickOnSave();
				
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije ispravan");
		assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
				
	}
	
	@Test
	void tc09 ()  {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		postCategoriesList.clickAddNewCategory();
		addPostCategory.insertPostCategoryName(updatedPostCategoryName);
		
		updatedPostCategoryDescription = Utils.getRandomPostCategoryDescription(300);
		addPostCategory.insertPostCategoryDescription(updatedPostCategoryDescription);
				
		addPostCategory.clickOnSave();
			
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije ispravan");
		assertEquals(postCategoriesList.countPostCategoryWithName(updatedPostCategoryName), 1, "Post Category sa imenom " + updatedPostCategoryName + " se ne nalazi na listi" );
		
				
	}
	
	@Test
	void tc10 ()   {
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
				
		postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
		
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		addPostCategory.insertPostCategoryName("");
				
		addPostCategory.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije ispravan");
		assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
		
		
	}
	
	@Test
	void tc11 () {
		
		PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
		
		postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
		
		AddPostCategory addPostCategory = new AddPostCategory(driver, false);
		
		addPostCategory.insertPostCategoryName("test");
				
		addPostCategory.insertPostCategoryDescription(updatedPostCategoryDescription);
		
		addPostCategory.clickOnCancel();
				
		assertEquals(driver.getCurrentUrl(), URLConst.POST_CATEGORIES_LIST, "URL nije ispravan");
		assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
		
		
		}
	
		@Test
		void tc12 () {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
			
			postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
			addPostCategory.insertPostCategoryName("");
			addPostCategory.clickOnSave();
			
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(addPostCategory.isErrorNameDisplayed(), true, "Poruka nije prikazana");
			assertEquals(addPostCategory.getNameErrorText(), "This field is required.", "Proveriti tekst poruke");
			assertEquals(editPostCategories.isEditPostTitleDisplayed(), true, "Proveriti , ne nalazimo se na Edit Post Categories");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
			
			}
		
		@Test 
		void tc13 () {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
			
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(editPostCategories.isEditPostTitleDisplayed(), true, "Proveriti , ne nalazimo se na Edit Post Categories");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
		}
			
		@Test
		void tc14() {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
						
			postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
			addPostCategory.insertPostCategoryDescription("");
			
			addPostCategory.clickOnSave();
			
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(addPostCategory.isErrorDescriptionDisplayed(),true,"Poruka nije prikazana");
			assertEquals(addPostCategory.getDescriptionNameErrorText(),"This field is required.", "Tekst poruke nije ispravan");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
				
		}
		
		@Test
		void tc15() {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
						
			postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
			newPostCategoryDescription = Utils.getRandomPostCategoryDescription(10);
			addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
			
			addPostCategory.clickOnSave();
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(addPostCategory.isErrorDescriptionLengthDisplayed(), true, "Poruka nije prikazana");
			assertEquals(addPostCategory.getDescriptionLengthError(), "The description must be at least 50 characters.", "Proveriti tekst poruke");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
									
		}
		
		@Test  // try boundary testing with 49characters when we do update
		void tc16() {
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
						
			postCategoriesList.clickOnUpdatePostCategory(updatedPostCategoryName);
			newPostCategoryDescription = Utils.getRandomPostCategoryDescription(49);
			addPostCategory.insertPostCategoryDescription(newPostCategoryDescription);
			
			addPostCategory.clickOnSave();
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(addPostCategory.isErrorDescriptionLengthDisplayed(), true, "Poruka nije prikazana");
			assertEquals(addPostCategory.getDescriptionLengthError(), "The description must be at least 50 characters.", "Proveriti tekst poruke");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
						
		}
			
		@Test
		void tc17() {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
						
			postCategoriesList.clickOnUpdatePostCategory(newPostCategoryName);
			addPostCategory.insertPostCategoryName(updatedPostCategoryName);
			
			addPostCategory.clickOnSave();
			EditPostCategories editPostCategories = new EditPostCategories(driver);
			
			assertEquals(editPostCategories.isNameErrorDisplayed(),true, "Poruka nije prikazana");
			assertEquals(editPostCategories.getNameErrorText(),"The name has already been taken.","Proveriti tekst poruke");
			assertEquals(editPostCategories.getEditPostTextTitle(), "Edit Post Categories", "Nije dobar naslov");
									
		}
		
		@Test
		void tc18() {
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
						
			postCategoriesList.clickOnDeleteCategory(newPostCategoryName);
			postCategoriesList.clickOnDeleteDialogCancel();
						
			assertEquals(driver.getCurrentUrl(),URLConst.POST_CATEGORIES_LIST, "URL nije dobar");
			assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 2, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
				
		}
		
		@Test 
		void tc19() {
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
						
			postCategoriesList.changeOrderCancel();
			
			assertEquals(driver.getCurrentUrl(),URLConst.POST_CATEGORIES_LIST, "Url nije ispravan");
									
			assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
			assertEquals(postCategoriesList.countPostCategoryWithName(updatedPostCategoryName), 1, "Post Category sa imenom " + updatedPostCategoryName + " se ne nalazi na listi" );
		}
		
		@Test
		void tc20() {
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			AddPostCategory addPostCategory = new AddPostCategory(driver, false);
			
			postCategoriesList.changeOrderSave();
							
			assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
			assertEquals(postCategoriesList.countPostCategoryWithName(updatedPostCategoryName), 1, "Post Category sa imenom " + updatedPostCategoryName + " se ne nalazi na listi" );
			assertEquals(driver.getCurrentUrl(),URLConst.POST_CATEGORIES_LIST,"URL nije ispravan");
									
		}
		
		@Test // ovde sam pokusao da uradim drag & drop ali nisam uspeo da prevucem jedno preko drugog!
		void tc21()  {
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
			
			postCategoriesList.changeOrder();
						
			postCategoriesList.dragAndDrop();
								
			postCategoriesList.orderSave();
			
			assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 1, "Post Category sa imenom " + newPostCategoryName + " se ne nalazi na listi" );
			assertEquals(postCategoriesList.countPostCategoryWithName(updatedPostCategoryName), 1, "Post Category sa imenom " + updatedPostCategoryName + " se ne nalazi na listi" );
			assertEquals(driver.getCurrentUrl(),URLConst.POST_CATEGORIES_LIST,"URL nije ispravan");
						
		}
				
		@Test
		void tc22() throws InterruptedException {
			
			PostCategoriesList postCategoriesList = new PostCategoriesList(driver);
						
			postCategoriesList.deleteCategory(newPostCategoryName);
			postCategoriesList.deleteCategory(updatedPostCategoryName);
			
			assertEquals(driver.getCurrentUrl(),URLConst.POST_CATEGORIES_LIST,"URL nije ispravan");
			assertEquals(postCategoriesList.countPostCategoryWithName(newPostCategoryName), 0, "Post Category sa imenom " + newPostCategoryName + " nije obrisan" );
			assertEquals(postCategoriesList.countPostCategoryWithName(updatedPostCategoryName), 0, "Post Category sa imenom " + updatedPostCategoryName + " nije obrisan");
						
								 
		}

		
}
