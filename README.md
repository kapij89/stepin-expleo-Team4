# Playwright-Java-Cucumber



## Getting Started

2 main maven dependencies
```xml
      <dependency>
          <groupId>com.microsoft.playwright</groupId>
          <artifactId>playwright</artifactId>
          <version>1.38.0</version>
	    </dependency>
      <dependency>
          <groupId>io.cucumber</groupId>
          <artifactId>cucumber-java</artifactId>
          <version>7.14.0</version>
      </dependency>

```

Working folder structure :
```
src
 |-- test
       |-- java
             |-- stepdefinitions
                   |-- steps.java (individual steps are captured)
			 |-- runner	   
                   |-- TestRunner.java
			 |-- pages
                   |-- <all pages>.java 	 
       |-- resources
             |-- features
                    |-- .feature (feature files)
  |-- pom.xml                  
```

To get autogenerated code by Playwright kick off the codegeneration (using the command below) and navigate through the use case on the browser.

`$ mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="codegen https://www.saucedemo.com/"`

Sample code generate by Playwright is as follows :

```java
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class Example {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();

      // Open new page
      Page page = context.newPage();

      // Go to https://www.saucedemo.com/
      page.navigate("https://www.saucedemo.com/");

      // Click [data-test="username"]
      page.click("[data-test=\"username\"]");

      // Fill [data-test="username"]
      page.fill("[data-test=\"username\"]", "standard_user");

      // Click [data-test="password"]
      page.click("[data-test=\"password\"]");

      // Fill [data-test="password"]
      page.fill("[data-test=\"password\"]", "secret_sauce");

      // Click [data-test="login-button"]
      page.click("[data-test=\"login-button\"]");
      // assert page
      // ---------------------
    }
  }
}
```
Now use Cucumber and Page Object Model techniques to pass the Playwright `Page` to every class including stepdefinitions.

To execute the tests :

All scenarios| `mvn clean test`
----|----
Specific tagged scenarios| `mvn clean test -Dcucumber.options="--tags @Smoke"`

You can study this repository for a detailed implementation.

To know about the Java implementation of Playwright follow [this.](https://playwright.dev/java/docs/intro/)
