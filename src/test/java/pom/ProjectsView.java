package pom;

import java.util.Map;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import utilities.BaseClass;
import utilities.WebDriverUtility;

public class ProjectsView extends BaseClass {

	WebDriverUtility cmd = new WebDriverUtility();

	By addProjectIcon = By.id("com.ideil.redmine:id/action_add");
	By searchIcon = AppiumBy.accessibilityId("Search");

	private By getByOfCreatedProj(String name) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().text(\"" + name + "\"). resourceId(\"com.ideil.redmine:id/title\")");
	}

	public void launchCreateProjectView() {
		cmd.click(addProjectIcon);
	}

	public void launchProjectSearchView() {
		cmd.click(searchIcon);
	}

}
