package utilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.Scenario;


public class BaseClass {

	public static WebDriver driver = null;
	public static Scenario scenario;

	public static void main(String[] args) {
		new BaseClass().getDriverInstance();
		System.out.println("Called");
	}
	
	public static WebDriver getDriverInstance() {

		UiAutomator2Options op = new UiAutomator2Options();
		
		op.setUdid("RZ8R92CYKJM");
		op.setAutomationName("UiAutomator2");
		op.setPlatformName("Android");
		op.setPlatformVersion("13");
		op.setNoReset(true);
		op.setFullReset(false);
		op.setCapability("appium:forceAppLaunch", true);
		op.setCapability("appium:amStartService", true);
		op.setCapability("appium:ignoreUnimportantViews", true);
		op.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60));
		op.setNewCommandTimeout(Duration.ZERO);
		op.setAppWaitActivity("com.ideil.redmine.*");
		op.setAppWaitDuration(Duration.ofSeconds(30));
		
		// adb shell dumpsys window | find "mCurrentFocus"
		op.setAppPackage("com.ideil.redmine");
		op.setAppActivity("com.ideil.redmine.view.activity.PreviewActivity");
		op.setAppWaitActivity("com.ideil.redmine.*");
				
		try {
			driver = new AndroidDriver(new URI("http://0.0.0.0:4723").toURL(), op);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}

}
