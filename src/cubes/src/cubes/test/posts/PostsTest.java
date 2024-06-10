package cubes.test.posts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.AddHasFullPageScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.driver.MyWebDriver;
import cubes.main.URLConst;
import cubes.main.Utils;
import cubes.pages.LoginPage;
import cubes.pages.posts.AddPost;
import cubes.pages.posts.PostList;

class PostsTest {
	
	private static WebDriver driver;
	private static String newPost;
	private static String newPostDescription;
	private static String updatedPost;
	private static String updatedPostDescription;
	private static String PostPhoto;
	
	private static String errorMessage = "This field is required.";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	driver = MyWebDriver.getInstance().getDriver("chrome");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		newPost = "Prvi post kreriran za testiranje";
		newPostDescription = Utils.getRandomPostCategoryDescription(50);
		
		updatedPost = Utils.getRandomPostName(20);
		updatedPostDescription = Utils.getRandomPostCategoryDescription(50);
		
		PostPhoto = "Demo post with picture!";
			
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginSuccess();
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		 driver.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get(URLConst.POST_LIST);
		
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void tc01() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		addPost.insertPostTitle("");
		
		addPost.moveDown();
		Thread.sleep(1000);
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.isTitleErrorDisplayed(), true, "Title Error nije prisutan");
		assertEquals(addPost.getTitleErrorText(),errorMessage, "Title error poruka nije prisutna/ispravna");
		assertEquals(addPost.getDescriptionErrorText(), errorMessage, "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
		
		
	}

	@Test 
	void tc02 () throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		addPost.insertPostTitle("");
										
		addPost.clickOnCancel();
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST,"Url nije validan");
				
	}
	
	@Test
	void tc03() {
				
		AddPost addPost = new AddPost(driver, true);
		
		addPost.chooseCategory(1);
		
		addPost.insertPostTitle("");
		addPost.checkboxImportantNo();
	
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.isTitleErrorDisplayed(), true, "Title Error nije prisutan");
		assertEquals(addPost.getTitleErrorText(),errorMessage, "Title error poruka nije prisutna/ispravna");
		assertEquals(addPost.getDescriptionErrorText(), errorMessage, "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
	}
	
	@Test 
	void tc04() {
		AddPost addPost = new AddPost(driver, true);
		
		addPost.chooseCategory(1);
		addPost.insertPostTitle("a");
		addPost.checkboxImportantNo();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.isTitleErrorDisplayed(), true, "Title Error nije prisutan");
		assertEquals(addPost.getTitleErrorText(),"Please enter at least 20 characters.", "Title error poruka nije prisutna/ispravna");
		assertEquals(addPost.getDescriptionErrorText(), errorMessage, "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
				
	}
	
	@Test
	void tc05() throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.clickAddNewPost();
		
		AddPost addPost = new AddPost(driver, false);
		
		addPost.chooseCategory(1);
		
		String postName = Utils.getRandomPostName(19);
		
		addPost.insertPostTitle(postName);
		addPost.checkboxImportantNo();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.isTitleErrorDisplayed(), true, "Title Error nije prisutan");
		assertEquals(addPost.getTitleErrorText(),"Please enter at least 20 characters.", "Title error poruka nije prisutna/ispravna");
		assertEquals(addPost.getDescriptionErrorText(), errorMessage, "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
				
	}
	
	@Test
	void tc06() {
		PostList postList = new PostList(driver);
		postList.clickAddNewPost();
		
		AddPost addPost = new AddPost(driver, false);
		
		addPost.chooseCategory(1);
		
		addPost.insertPostTitle(newPost);
				
		addPost.checkboxImportantNo();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.getDescriptionErrorText(), errorMessage, "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
		
	}
	
	@Test
	void tc07 () {
		
		PostList postList = new PostList(driver);
		postList.clickAddNewPost();
		
		AddPost addPost = new AddPost(driver, false);
		
		addPost.chooseCategory(1);
		String postName = Utils.getRandomPostName(20);
		
		addPost.insertPostTitle(postName);
		addPost.insertPostDescription("PROBA");
		addPost.checkboxImportantNo();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.getDescriptionErrorText(), "Please enter at least 50 characters.", "Error poruka za description nije pristuna/ispravna");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
		assertEquals(addPost.isDescriptionErrorDisplayed(), true, "Description error poruka nije prisutna");
		
		
	}
	
	@Test
	void tc08 () {
		PostList postList = new PostList(driver);
		postList.clickAddNewPost();
		
		AddPost addPost = new AddPost(driver, false);
		
		addPost.chooseCategory(1);
		String postName = Utils.getRandomPostName(20);		
		addPost.insertPostTitle(postName);
		
		String postDescriiption = Utils.getRandomPostDescription(50);
		addPost.insertPostDescription(postDescriiption);
		addPost.checkboxImportantNo();
				
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(), true, "Tag error poruka nije prisutna");
				
		
	}
	
	@Test 
	void tc09 () throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.clickAddNewPost();
		
		AddPost addPost = new AddPost(driver, false);
		
		String postName = Utils.getRandomPostName(20);		
		addPost.insertPostTitle(postName);
		
		String postDescriiption = Utils.getRandomPostDescription(50);
		addPost.insertPostDescription(postDescriiption);
		addPost.checkboxImportantNo();
		
		addPost.selectTag();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.selectTagIsEnabled(),true, "Tag is not enabled");
		assertEquals(addPost.seletTagIsSelected(), true, "Tag is not selected");
		
	}
	

	
	@Test
	void tc11() {
		AddPost addPost = new AddPost(driver,true);
		
		addPost.chooseCategory(1);
		
		String postName = Utils.getRandomPostName(20);		
		addPost.insertPostTitle(postName);
		
		String postDescriiption = Utils.getRandomPostDescription(50);
		addPost.insertPostDescription(postDescriiption);
		addPost.checkboxImportantNo();
		addPost.insertContent("proba");
				
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "URL nije ispravan");
		assertEquals(addPost.getTagErrorText(), errorMessage, "Tag error poruka  nije prisutna/ispravna");
		assertEquals(addPost.isTagErrorDisplayed(),true, "Tag error is not displayed");
		
		
		
	}
	
	@Test
	void tc12() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		PostList postList = new PostList(driver);
		
		addPost.chooseCategory(1);
		
		addPost.insertPostTitle(newPost);
				
		addPost.insertPostDescription(newPostDescription);
		addPost.checkboxImportantYes();
									
		addPost.selectTag();
		
		addPost.insertContent("prvi kreiran post , bez slike");
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST,"Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1,"Post sa nazivom " + newPost + " se nije pojavio u listi.");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Post sa nazivom " + newPost + " se nije pojavio u listi.");
				
	}
	
	@Test
	void tc13() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		PostList postList = new PostList(driver);
		
		addPost.chooseCategory(2);
		
		addPost.insertPostTitle(updatedPost);
				
		addPost.insertPostDescription(updatedPostDescription);
		addPost.checkboxImportantYes();
									
		addPost.selectAllTags();
		addPost.addPhoto();
		
		addPost.insertContent("kreiran post broj 2 ");
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST,"Url nije validan");
		assertEquals(postList.isPostWithNameInList(updatedPost), true, "Post sa nazivom " +updatedPost+ " se ne nalazi na listi");
		assertEquals(postList.countPostWithName(updatedPost), 1,"Post sa nazivom " + updatedPost + " se nije pojavio u listi.");
				
	}
	
	@Test
	void tc14() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
					
		addPost.chooseCategory(1);
						
		addPost.insertPostTitle(PostPhoto);
		
		String postDescriiption = Utils.getRandomPostDescription(50);
		addPost.insertPostDescription(postDescriiption);
		addPost.checkboxImportantYes();
						
		addPost.moveDown();
				
		addPost.addPhoto();
		
		addPost.insertContent("Post sa sa slikom");
			
		addPost.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "URL nije ispravan");
		
	}
	
	@Test
	void tc15() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		
		addPost.chooseCategory(3);
			
		addPost.insertPostTitle(PostPhoto);
		
		String postDescriiption = Utils.getRandomPostDescription(50);
		addPost.insertPostDescription(postDescriiption);
		addPost.checkboxImportantYes();
						
		addPost.moveDown();
		addPost.selectTag();
		
		addPost.addPhoto();
		
		addPost.insertContent("Post kreiran sa slikom 2");
			
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "URL nije ispravan");
		
	}
	
	@Test
	void tc16() throws InterruptedException {
		AddPost addPost = new AddPost(driver, true);
		
		addPost.chooseCategory(4);
					
		addPost.insertPostTitle(PostPhoto);;
		
		addPost.insertPostDescription(newPostDescription);
		addPost.checkboxImportantYes();
		addPost.selectTag();
		
		addPost.moveDown();
				
		addPost.insertContent("proba");
			
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.ADD_POST, "Invalid URL"); 
			
	}
	
//	@Test
//	void tc17() throws InterruptedException {
//		PostList postList = new PostList(driver);
//		AddPost addPost = new AddPost(driver, false);
//		
//		postList.actions(driver, newPost);
//		
//		postList.clickOnUpdatePost(newPost);
//		addPost.insertPostTitle(updatedPost);
//		
//		addPost.clickOnSave();
//		
//		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST,"URL nije validan");
//		assertEquals(postList.countPostWithName(updatedPost), 1,"Ne bi trebalo biti updatovan post sa vec koriscenim imenom");
		// ovaj test sam iz izkomentarisao jer mi napravi dva posta istog naziva ...			
//	}
	
	@Test 
	void tc18() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		
		postList.searchByTitle("proba");
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList("proba"), false, "Na post listi nadjen je post");
		assertEquals(postList.countPostWithName("proba"), 0,"Na post listi nadjen je post sa kucanim nazivom");
		
	}
	
	@Test
	void tc19() throws InterruptedException {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(PostPhoto);
		
		postList.moveDown();
		
		Thread.sleep(1500);
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 2, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
	}
	 
	@Test
	void tc20() throws InterruptedException {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Vladan Dzulovic");
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
				
	}
	
	@Test
	void tc21() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Marko Dragonjic");
		postList.moveDown();
		
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test
	void tc22() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Bla");
		postList.moveDown();
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test
	void tc23() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Resset Pass User - NE BRISATI");
		postList.moveDown();
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test
	void tc24() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("MihailQa");
		postList.moveDown();
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test
	void tc25() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Polaznik Kursa");
		postList.moveDown();
				
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
				
	}
	
	@Test
	void tc26() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Polaznik Kursa");
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
					
	}
	
	@Test
	void tc27()   {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(2);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
		
	}
	
	@Test
	void tc28() {
			PostList postList = new PostList(driver);
			
			postList.searchByTitle(newPost);
			postList.searchByAuthor("Polaznik Kursa");
				
			postList.searchByCategory(3);
			postList.moveDown();
			
			assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
			assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
			assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
			
			
		}

	@Test
	void tc29() {
	
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost);
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
					
		
	}
	
	@Test 
	void tc30() {
		
		PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc31() {
	
			
			PostList postList = new PostList(driver);
			
			postList.searchByAuthor("Polaznik Kursa");
				
			postList.searchByCategory(1);
			postList.moveDown();
			
			assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
			assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
			assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
			
		
	}
	
	@Test
	void tc32() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle("Prvi"); // kucam jednu rec od naziva posta (prvog kreiranog)
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
	}
	
	void tc33() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle("Drugi"); // kucam jednu rec koje nema u nazivu posta (prvog kreiranog)
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test 
	void tc34() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost); 
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(3);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
		
	}
	
	@Test 
	void tc35() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost); 
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(3);
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
		
	}
	@Test
	void tc36() {
		
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost); 
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.chooseImportant("no");
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
		
	}
	
	@Test
	void tc37() {
		
		PostList postList = new PostList(driver);
		
		postList.searchByTitle(newPost); 
		postList.searchByAuthor("Polaznik Kursa");
			
		postList.searchByCategory(1);
		postList.chooseImportant("yes");
		postList.moveDown();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
	
	
	}
	
	@Test
	void tc38() {
		
		PostList postList = new PostList(driver);
		
		postList.chooseImportant("no");
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + newPost);
						
	}
	
	@Test
	void tc39() {
		
		PostList postList = new PostList(driver);
		
		postList.chooseImportant("yes");
		postList.moveDown();
				
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc40() {
		PostList postList = new PostList(driver);
		
		postList.searchByCategory(1);
		
		postList.moveDown();
				
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
						
	}
	
	@Test
	void tc41() {
		PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
		
		postList.moveDown();
				
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + PostPhoto);
		assertEquals(postList.countPostWithName(PostPhoto), 2, "Na post listi nije nadjen post sa nazivom " + PostPhoto);
		
	}
	
	@Test
	void tc42() throws InterruptedException {
		
		
		PostList postList = new PostList(driver);
		
		postList.chooseStatus("disabled");
		postList.moveDown();
		
		Thread.sleep(1000); // ako ne stavim thread sleep pokazuje da imam nesto na post listi sto nije slucaj
										
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + PostPhoto);
		
		
	}
	
	@Test
	void tc43() {
		
		PostList postList = new PostList(driver);
		
		postList.chooseStatus("enabled");
		postList.moveDown();
		
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + PostPhoto);
		assertEquals(postList.countPostWithName(PostPhoto), 1, "Na post listi nije nadjen post sa nazivom " + PostPhoto);
				
	}
	
	@Test
	void tc44() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseImportant("yes");
		postList.chooseStatus("disabled");
		
		postList.moveDown();
		Thread.sleep(1000);
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi je nadjen post sa nazivom" + PostPhoto);
							
	}
	
	@Test
	void tc45() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseImportant("yes");
		postList.chooseStatus("enabled");
				
		postList.moveDown();
				
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}		
	
	@Test
	void tc46() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Polaznik Kursa");
		
		postList.chooseStatus("disabled");
				
		postList.moveDown();
		
		Thread.sleep(1000);
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		
				
	}
	
	@Test
	void tc47() {
		
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Marko Dragonjic");
		
		postList.chooseStatus("enabled");
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		
		
	}
	
	@Test
	void tc48() {
		
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Polaznik Kursa");
		
		postList.chooseStatus("enabled");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
				
	}
	
	@Test
	void tc49() throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseStatus("disabled");
		
		Thread.sleep(1000);
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
				
	}
	
	@Test
	void tc50() {
		PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseStatus("enabled");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
			
	}
	
	@Test
	void tc51() {
	
		PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
	
		postList.chooseStatus("enabled");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc52() throws InterruptedException {
		
		PostList postList = new PostList(driver);
				
		postList.withTag(3);
		postList.moveDown();
		Thread.sleep(1000);
				
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc53() throws InterruptedException {
		
		PostList postList = new PostList(driver);
				
		postList.withTag(3);
		postList.moveDown();
		
		assertEquals(postList.countPostWithName(PostPhoto), 2, "Na post listi nije nadjen post sa nazivom " + updatedPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + updatedPost);
	
	}
	
	@Test 
	void tc54() {
		PostList postList = new PostList(driver);
		
		postList.withTag(0);
		postList.withTag(2);
		
		postList.moveDown();
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc55() {
		
	PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseStatus("enabled");
		postList.chooseImportant("yes");
		postList.withTag(3);
		
		postList.moveDown();
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc56() {
						
		PostList postList = new PostList(driver);
				
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseStatus("enabled");
		postList.chooseImportant("yes");
		postList.withTag(0);
				
		postList.moveDown();
				
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
				
	}
	
	@Test
	void tc57() throws InterruptedException {
		PostList postList = new PostList(driver);
		
		postList.searchByAuthor("Polaznik Kursa");
		postList.searchByCategory(1);
		postList.chooseStatus("enabled");
		postList.chooseImportant("no");
		postList.withTag(0);
				
		postList.moveDown();
		Thread.sleep(1000);
		
		assertEquals(postList.countPostWithName(newPost), 0, "Na post listi je nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), false, "Na post listi je nadjen post sa nazivom " + newPost);
		
		
		
	}
		
	@Test
	void tc58()  {
		PostList postList = new PostList(driver);
		
		postList.withAllTag();
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
				
				
	}
	
	@Test
	void tc59() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.searchText("prvi");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
	}
	
	@Test
	void tc60() throws InterruptedException {
		AddPost addPost = new AddPost(driver, false);
		PostList postList = new PostList(driver);
			
		postList.actions(driver, newPost);
		
		Thread.sleep(100);
						
		postList.clickOnUpdatePost(newPost);
		
		addPost.insertPostTitle("Menjamo naziv prvog posta zbog testiranja");
		
		addPost.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		
		postList.searchByTitle("Prvi");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom ");
				
	}
	
	@Test
	void tc61() throws InterruptedException   {
		AddPost addPost = new AddPost(driver, false);

		PostList postList = new PostList(driver);
		
		postList.actions(driver, newPost);
		
		postList.clickDisablePost(newPost);
				
		postList.clickOnCancelDisablePost();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		
		postList.searchByTitle("Prvi");
		Thread.sleep(1000);
				
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom ");
	
	}
	
	@Test
	void tc62() throws InterruptedException {
			
		PostList postList = new PostList(driver);
		postList.actions(driver, PostPhoto);
		
		postList.clickDisablePost(newPost);
						
		postList.confirmDisablePost();
						
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom ");
						
	}
	
	@Test // ovde sam radio assert koji se tice prethodnog tc
	void tc63() {
		
		PostList postList = new PostList(driver);
				
		postList.chooseStatus("disabled");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
				
	}
		
	@Test
	void tc64() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.actions(driver, PostPhoto);
		
		postList.clickOnImportantPost(PostPhoto);
		postList.cancelImportantPost();
		
		assertEquals(driver.getCurrentUrl(),URLConst.POST_LIST, "URL nije validan");
		
		
	}
	
	@Test
	void tc65() {
		
		PostList postList = new PostList(driver);
		
		postList.chooseImportant("yes");
		
		assertEquals(postList.countPostWithName(PostPhoto), 2, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + newPost);
				
	}
		
	@Test
	void tc66() throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.actions(driver, PostPhoto);
		
		postList.clickOnImportantPost(PostPhoto);
		postList.confirmOnUnimportantPost();
		
	}
	
	@Test
	void tc67() {
	PostList postList = new PostList(driver);
		
		postList.chooseImportant("no");
		
		assertEquals(postList.countPostWithName(PostPhoto), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	
	@Test
	void tc68() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.actions(driver, PostPhoto);
		
		postList.clickOnDeletePost(PostPhoto);
		postList.clickOnDeleteDialogCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(PostPhoto), 2, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc69() throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.actions(driver, PostPhoto);
		
		postList.clickOnDeletePost(PostPhoto);
		postList.clickOnDeleteDialogDelete();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(PostPhoto), 0, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(PostPhoto), false, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
	}
	
	@Test 
	void tc70() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, updatedPost);
		
		postList.clickOnUpdatePost(updatedPost);
		
		addPost.insertPostTitle("Ovo je drugi demo Post 1000");
		
		addPost.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		
				
	}
		
	@Test
	void tc71() throws InterruptedException {
		
			
			PostList postList = new PostList(driver);
			AddPost addPost = new AddPost(driver, false);
			
			postList.actions(driver, updatedPost);
			
			postList.clickOnUpdatePost(updatedPost);
			
			addPost.insertPostTitle("Ovo je drugi demo Post 1000");
			
			addPost.clickOnSave();
			
			assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
				
	}
	
	@Test
	void tc72() {
		PostList postList = new PostList(driver);
		
		postList.searchByTitle("Ovo je drugi"); 
		postList.moveDown();
		
		assertEquals(postList.countPostWithName("Ovo je drugi demo Post 1000"), 1, "Na post listi nije nadjen post sa nazivom " + "Ovo je drugi demo Post 1000");
		assertEquals(postList.isPostWithNameInList("Ovo je drugi demo Post 1000"), true, "Na post listi nije nadjen post sa nazivom " + "Ovo je drugi demo Post 1000");
		
	}
	
		
	@Test
	void tc73() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.insertPostDescription(newPostDescription);
		
		addPost.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
	}
	
	@Test
	void tc74() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.insertPostDescription("Ovo je novi description za nov post koji smo izmenili. Ovo je sve u svrsi testiranja -> demo testing");
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
				
	}
	
	@Test
	void tc75() throws InterruptedException {
		PostList postList = new PostList(driver);
		postList.actions(driver, newPost);
	
		Thread.sleep(1000);
		postList.clickOnImportantPost(newPost);
		
		postList.cancelImportantPost();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
				
		
	}
		
	@Test
	void tc76() throws InterruptedException {
		PostList postList = new PostList(driver);
		
		
		postList.chooseImportant("yes");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
			
		
	}
			
	@Test
	void tc77() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		postList.actions(driver, newPost);
		
		postList.clickOnImportantPost(newPost);
		postList.confirmOnUnimportantPost();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		
	}
	
	
	@Test
	void tc78() throws InterruptedException {
		
		PostList postList = new PostList(driver);
				
		postList.chooseImportant("no");
		
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
		
	

	
	@Test
	void tc79() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.selectAllTags();
		
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		
	}
	
	@Test // ovaj tc je nastavak prethodnog
	void tc80() {
		
		PostList postList = new PostList(driver);
		
		postList.withAllTag();
		
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
		
		
	}
	
	@Test 
	void tc81() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.addPhoto();
		
		addPost.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
	}
	
	@Test // update posta -> ubacivanje PNEG file
	void tc82() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.addPhotoPNEG();
		
		addPost.clickOnSave();
				
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
	}
	
	@Test
	void tc83() throws InterruptedException {
		

		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.insertContent("Novi kontent ubacen !!!");
				
		addPost.clickOnSave();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POST_LIST, "Url nije validan");
		assertEquals(postList.countPostWithName(newPost), 1, "Na post listi nije nadjen post sa nazivom " + newPost);
		assertEquals(postList.isPostWithNameInList(newPost), true, "Na post listi nije nadjen post sa nazivom " + newPost);
		
	}
	
	@Test
	void tc84() throws InterruptedException {
		
		PostList postList = new PostList(driver);
		AddPost addPost = new AddPost(driver, false);
		
		postList.actions(driver, newPost);
		
		postList.clickOnUpdatePost(newPost);
		
		addPost.insertPostTitle("");
		
		addPost.clickOnSave();
				
		assertEquals(addPost.isTitleErrorDisplayed(), true, "Error poruka nije prikazana");
		assertEquals(addPost.getTitleErrorText(), errorMessage, "Title error poruka nije prikazana/ispravna ");
		
	}
	
	@Test
	void tc85()  {
		PostList postList = new PostList(driver);
		
		postList.actions(driver, PostPhoto);
		
		postList.clickOnDeletePost(PostPhoto);
		
		postList.clickOnDeleteDialogDelete();
		
		
		
	}
	
	@Test
	void tc86 () {
		PostList postList = new PostList(driver);
		
		postList.actions(driver, newPost);
		
		postList.clickOnDeletePost(newPost);
		
		postList.clickOnDeleteDialogDelete();
		
		
	}
	
	@Test
	void tc87() {
		
		PostList postList = new PostList(driver);
		
		postList.actions(driver, "Ovo je drugi demo Post 1000");
		
		postList.clickOnDeletePost("Ovo je drugi demo Post 1000");
		
		postList.clickOnDeleteDialogDelete();
		
		assertEquals(postList.countPostWithName("Ovo je drugi demo Post 1000"), 0, "Na post listi je nadjen post sa nazivom " + "Ovo je drugi demo Post 1000");
		assertEquals(postList.isPostWithNameInList("Ovo je drugi demo Post 1000"), false, "Na post listi je nadjen post sa nazivom " + "Ovo je drugi demo Post 1000");
		
	}
	
	
}
