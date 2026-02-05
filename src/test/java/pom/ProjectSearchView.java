package pom;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import utilities.WebDriverUtility;

public class ProjectSearchView {
	WebDriverUtility cmd = new WebDriverUtility();

	By searchTB = By.id("com.ideil.redmine:id/edtSearch");

	private By getByOfProj(String projName) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.ideil.redmine:id/title\").text(\"Project: " + projName + "\")");
	}

	public void searchForTheProject(String name) {
		cmd.sendText(searchTB, name);
		cmd.click(searchTB);
		cmd.performAndroidSearchAction();
	}

	public boolean isProjectPresent(String name) {
		return cmd.isElementVisible(getByOfProj(name));
	}
}
