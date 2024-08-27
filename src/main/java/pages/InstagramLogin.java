package pages;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Playwright;
import org.junit.Assert;
import com.microsoft.playwright.Page;
import org.openqa.selenium.WebElement;
//import com.microsoft.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&//

//public class ItemsPage extends BasePage {
//
//    Page page;
//
//    public ItemsPage(Page page) {
//        this.page = page;
//    }

//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&//
public class InstagramLogin extends BasePage {

    Page page;

    public InstagramLogin(Page page) {
        this.page = page;
    }


    public void instalogin(String username,String password) {

        page.fill("input[name='username']",username);
        page.fill("//input[@name='password']",password);
        page.click("//button/div");
    }

    public void createPost() {
        page.click("//span[text()='Create']");

        //file upload
        FileChooser fileChooser = page.waitForFileChooser(() -> {
            page.click("//button[text()='Select from computer']");
        });
        fileChooser.setFiles(Paths.get("C:\\Users\\Manager\\Pictures\\pic1.png"));

        page.waitForTimeout(1000);
        page.click("//div[text()='Next']");


    }

    public void shadowDom(){
        page.navigate("https://books-pwakit.appspot.com/");
        page.locator("book-app[apptitle='BOOKS'] #input").fill("Testing Books");
        String text = page.locator("book-app[apptitle='BOOKS'] .books-desc").textContent();
        System.out.println(text);



    }

    //fileupload code for input element with type "file"
        // page.getByLabel("Upload file").setInputFiles(Paths.get("myfile.pdf"));

    //code to file upload without input class file in dom
        //    FileChooser fileChooser = page.waitForFileChooser(() -> {
        //        page.click("//button[text()='Select from computer']");
        //    });
        //        fileChooser.setFiles(Paths.get("C:\\Users\\Manager\\Pictures\\

    //dragAndDrop
        //page.locator("#item-to-be-dragged").dragTo(page.locator("#item-to-drop-at"));

    //code to select shadowdom Elements
       // page.locator("book-app[apptitle='BOOKS'] #input").fill("Testing Books");
       // String text = page.locator("book-app[apptitle='BOOKS'] .books-desc").textContent();


    //code to select svg elements
        //button[@title='clear']/span/*[local-name()='svg']
    //You can use nth locator's method to get second element:
        //page.locator("svg[aria-label='Comment']").nth(1).click();

    //get list of all elements
        //List<ElementHandle> links = page.querySelectorAll("a"); // get all links on the page
        // List<ElementHandle> inputs = page.querySelectorAll("input"); // get all input fields on the page


    // Use XPath to select all elements
        // List<ElementHandle> elements = page.querySelectorAll("xpath=//div[@class='my-class']");

    // Iterate over the elements and perform an action
        // for (ElementHandle element : elements) {
        // System.out.println(element.textContent());

    //chart & graphs automation


}
