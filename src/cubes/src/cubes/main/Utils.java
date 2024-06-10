package cubes.main;

import java.nio.charset.Charset;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	
	public static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(10000);
	}
	
	public static String getRandomPostCategoryName() {
		return "Post Category "+getRandomNumber();
	}
	
	
	public static String getRandomPostName(int n) {
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			         + "0123456789"
			         + "abcdefghijklmnopqrstuvxyz"; 
			 
			  StringBuilder sb = new StringBuilder(n); 
			 
			  for (int i = 0; i < n; i++) { 
				 int index 
			    = (int)(AlphaNumericString.length() 
			      * Math.random()); 
			      sb.append(AlphaNumericString 
			      .charAt(index)); 
			  } 			 
			  return sb.toString(); 
		
	}
	public static String getRandomPostDescription(int n) {
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			         + "0123456789"
			         + "abcdefghijklmnopqrstuvxyz"; 
			 
			  StringBuilder sb = new StringBuilder(n); 
			 
			  for (int i = 0; i < n; i++) { 
				 int index 
			    = (int)(AlphaNumericString.length() 
			      * Math.random()); 
			      sb.append(AlphaNumericString 
			      .charAt(index)); 
			  } 			 
			  return sb.toString(); 
		
	}
		
	public static String getRandomPostCategoryDescription(int n) {
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			         + "0123456789"
			         + "abcdefghijklmnopqrstuvxyz"; 
			 
			  StringBuilder sb = new StringBuilder(n); 
			 
			  for (int i = 0; i < n; i++) { 
				 int index 
			    = (int)(AlphaNumericString.length() 
			      * Math.random()); 
			      sb.append(AlphaNumericString 
			      .charAt(index)); 
			  } 			 
			  return sb.toString(); 
		
	}
	
		
	public static String getRandomTagName() {
		return "Tag name "+getRandomNumber();
	}
	
		
	public static void scrollToElement(WebDriver driver, WebElement element) {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public static void scrollToElement(WebDriver driver, String locator) {
		
		WebElement element = driver.findElement(By.xpath(locator));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}	

}
