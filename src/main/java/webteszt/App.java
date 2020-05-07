package webteszt;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Hello Selenium !
 * https://www.javatpoint.com/selenium-webdriver-running-test-on-firefox-browser-gecko-driver
 *
 * https://www.codota.com/code/java/classes/org.openqa.selenium.firefox.FirefoxOptions
 */
public class App 
{
    public static void main( String[] args )
    {
      System.out.println( "main() BEGIN");

      // System Property for Gecko Driver
      System.setProperty( "webdriver.gecko.driver", "/usr/bin/geckodriver");
      System.setProperty( "webdriver.firefox.bin", "/snap/bin/firefox");
      System.setProperty( "webdriver.firefox.marionette", "/usr/bin/geckodriver");

      // Initialize Gecko Driver using Desired Capabilities Class
      System.out.println( "main() : FirefoxProfile fxProfile = new FirefoxProfile()");
      FirefoxProfile fxProfile = new FirefoxProfile();

      System.out.println( "main() : fxProfile.setPreference()");
      fxProfile.setPreference( "browser.download.folderList", 2);
      fxProfile.setPreference( "browser.download.manager.showWhenStarting", false);
      fxProfile.setPreference( "browser.download.dir", new File( ".").getAbsolutePath());
      fxProfile.setPreference( "browser.helperApps.neverAsk.saveToDisk", "application/zip,application/x-compress,application/octet-stream");

      // Sajat hulyeseg a System properties alapjan ...
      fxProfile.setPreference( "webdriver.gecko.driver", "/usr/bin/geckodriver");
      fxProfile.setPreference( "webdriver.firefox.bin", "/snap/bin/firefox");
      fxProfile.setPreference( "webdriver.firefox.marionette", "/usr/bin/geckodriver");

      System.out.println( "main() : new FirefoxOptions().setProfile( fxProfile)");
      FirefoxOptions options = new FirefoxOptions().setProfile( fxProfile);

      // Egy ilyet talaltam a processzek kozt :
      // ps -fu tamas | grep gecko
      // tamas    19879     1  0 10:28 tty2     00:00:00 /usr/bin/geckodriver --port=28659 -b /snap/bin/firefox
      System.out.println( "main() : new FirefoxDriver( options)");
      WebDriver driver = new FirefoxDriver();// new FirefoxDriver( options);

      System.out.println( "main() : driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS)");
      driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();


      System.out.println( "main() : ((JavascriptExecutor) driver).executeScript(\"window.focus();\")");
      ((JavascriptExecutor) driver).executeScript("window.focus();");

      // Firefox 47 or later versions have marionette driver as a legacy system. Thus, marionette driver can be called using Firefox Options as shown below.
//      FirefoxOptions options = new FirefoxOptions(); Using `new FirefoxOptions()` is preferred to `DesiredCapabilities.firefox()`
//      options.setLegacy( true);

      // -----------------------------------------------------------------------------------------------

      // Launch Website
      System.out.println( "main() : driver.navigate().to(\"http://www.javatpoint.com/\")");
      driver.navigate().to("http://www.javatpoint.com/");

      // Click on the Custom Search text box and send value
      System.out.println( "main() : driver.findElement( By.id( \"gsc-i-id1\")).sendKeys(\"Java\")");
      driver.findElement( By.id( "gsc-i-id1")).sendKeys("Java");

      // Click on the Search button
      System.out.println( "main() : driver.findElement( By.className( \"gsc-search-button gsc-search-buttonv2\")).click()");
      driver.findElement( By.className( "gsc-search-button gsc-search-buttonv2")).click();

      System.out.println( "main() : driver.close()");
      driver.close();

      System.out.println( "main() : driver.quit()");
      driver.quit();

      System.out.println( "main() END");
    }
}