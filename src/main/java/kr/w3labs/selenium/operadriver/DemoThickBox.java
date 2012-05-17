package kr.w3labs.selenium.operadriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.opera.core.systems.OperaDriver;

public class DemoThickBox {
	public static void main(String[] args) throws Exception {
		/*
		 * Init the driver. This will automatically find your Opera browser and
		 * extract Opera launcher from the jar if it doesn't find an Opera
		 * launcher.
		 * 
		 * If you want to override the Opera or launcher location you can either
		 * use these environment variables:
		 * 
		 * OPERA_PATH absolute path to your Opera binary OPERA_LAUNCHER absolute
		 * path to the Opera launcher binary
		 * 
		 * or create a DesiredCapabilities object:
		 * 
		 * import org.openqa.selenium.remote.DesiredCapabilities;
		 * 
		 * ...
		 * 
		 * DesiredCapabilities capabilities = new DesiredCapabilities();
		 * capabilities.setCapability("opera.binary", "/path/to/my/opera");
		 * capabilities.setCapability("opera.arguments", "-nowindow");
		 * capabilities.setCapability("opera.logging.level", "FINE");
		 * 
		 * OperaDriver driver = new OperaDriver(capabilities);
		 * 
		 * Have a look at the documentation for more settings.
		 */
		OperaDriver driver = new OperaDriver();

		/*
		 * OperaDriver supports all of the WebDriver methods. This one navigates
		 * to the given webpage
		 */
		driver.navigate().to("http://jquery.com/demo/thickbox/");
		System.out.println("Current url: " + driver.getCurrentUrl());

		driver.findElement(By.cssSelector("#container-6 > ul.anchors > li.on > a")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Demo')])[9]")).click();

		for (int i = 0; i < 1000; i++) {
			System.out.println("step #" + i);
			// avoid click cancel bug
			Thread.sleep(200);
			if (i == 0) {
				driver.findElement(By.linkText("Scrolling content")).click();
			} else {
				driver.findElement(By.linkText("Scrolling content")).click();
				driver.findElement(By.linkText("Scrolling content")).click();
			}
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (driver.findElement(By.id("TB_window")).isDisplayed()) break; } catch (Exception e) {}
				Thread.sleep(1000);
				System.out.println("Waiting for Scrolling content TB_window");
			}
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (driver.findElement(By.id("TB_overlay")).isDisplayed()) break; } catch (Exception e) {}
				Thread.sleep(1000);
				System.out.println("Waiting for Scrolling content TB_overlay");
			}
			
			driver.findElement(By.id("TB_overlay")).click();
			
			Thread.sleep(200);
			// avoid click cancel bug
			driver.findElement(By.linkText("No-scroll content")).click();
			driver.findElement(By.linkText("No-scroll content")).click();
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (driver.findElement(By.id("TB_window")).isDisplayed()) break; } catch (Exception e) {}
				Thread.sleep(1000);
				System.out.println("Waiting for No-scroll content TB_window");
			}
			
			for (int second = 0;; second++) {
				if (second >= 60) fail("timeout");
				try { if (driver.findElement(By.id("TB_overlay")).isDisplayed()) break; } catch (Exception e) {}
				Thread.sleep(1000);
				System.out.println("Waiting for No-scroll content TB_overlay");
			}
			
			driver.findElement(By.id("TB_overlay")).click();
		}

		/*
		 * When finished, shutdown Opera
		 */
		driver.quit();
	}

	private static void fail(String string) {
		// TODO Auto-generated method stub
	}
}
