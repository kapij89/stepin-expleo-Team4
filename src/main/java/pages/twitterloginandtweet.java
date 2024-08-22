package pages;

import org.junit.Assert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;

public class twitterloginandtweet {
	Page page;
	
	  public twitterloginandtweet(Page page) { 
		  this.page = page;
   }
	  
	  public void twitterlogin(String username,String password) {
		  page.getByLabel("Phone, email, or username").click();
		  page.getByLabel("Phone, email, or username").fill(username);
//	      page.fill("Phone, email, or username", username);
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
	     // page.getByTestId("ocfEnterTextTextInput").fill("9994448594");
             // page.getByTestId("ocfEnterTextNextButton").click();
//	      page.getByTestId("ocfEnterTextTextInput").fill("9994448594");
//	      page.getByTestId("ocfEnterTextTextInput").click();
/*	      try {
              // Wait for an element with the specified selector to appear
              String selector = "ocfEnterTextTextInput"; // Change this to your desired selector
              ElementHandle element = page.waitForSelector(selector);

              if (element != null) {
                  System.out.println("Element exists!");
                  
              } else {
                  System.out.println("Element does not exist.");
              }
          } catch (PlaywrightException e) {
              System.err.println("An error occurred: " + e.getMessage());
          }
	    */
	      page.getByLabel("Enter your password").fill("Redapple@123");
	      page.getByTestId("LoginForm_Login_Button").click();
//	      page.navigate("https://twitter.com/home");
	}
	
	  public void tweet(String unique_text,String Post) throws InterruptedException {
		  String post1="Super happy to participate in #TESTAUTOTHON2023 organised by "
		  		+ "@stepin_forum in partnership with @verity_software. This event is running in parallel with #STEPINSUMMIT2023." +unique_text;
		  String post2="The Participation @stepin_forum @verity_software is amazing and about 35 teams are participating in this #TESTAUTOTHON2023."+unique_text+" ";
		  String post3=" #TESTAUTOTHON2023 @stepin_forum @verity_software "+unique_text;
		  int i =1;
		  String numberOnly= Post.replaceAll("[^0-9]", "");
		  final String postontweet= "post"+Post;
		  Thread.sleep(2000);
		  page.getByTestId("tweetTextarea_0").click();
		  if(Post.equalsIgnoreCase("1")){
			  page.getByTestId("tweetTextarea_0").fill(post1);
		  }else if(Post.equalsIgnoreCase("2")){
			  page.getByTestId("tweetTextarea_0").fill(post2);
		  }else if(Post.equalsIgnoreCase("3")){
			  page.getByTestId("tweetTextarea_0").fill(post3);
		  }
		 
		  
		  page.getByTestId("tweetButtonInline").click();
	}
	public void loginFailed() {
		boolean visible = page.isVisible("//h3[text()='Epic sadface: Sorry, this user has been locked out.']");
		Assert.assertTrue(visible);
	}
}
