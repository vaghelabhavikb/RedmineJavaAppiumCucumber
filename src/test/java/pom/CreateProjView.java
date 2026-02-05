package pom;

import java.util.Map;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import utilities.BaseClass;
import utilities.WebDriverUtility;

public class CreateProjView extends BaseClass {
	WebDriverUtility cmd = new WebDriverUtility();

	By name = By.id("com.ideil.redmine:id/input_name");
	By desc = By.id("com.ideil.redmine:id/input_description");
	By identifier = By.id("com.ideil.redmine:id/input_identifier");
	By markPublic = By.id("com.ideil.redmine:id/check_is_public");
	By subOfProj = By.id("com.ideil.redmine:id/input_subproject");
	By tracker = By.id("com.ideil.redmine:id/input_trackers");
	By projCreate = By.id("com.ideil.redmine:id/btn_project_create");

	By docIDProj = AppiumBy.androidUIAutomator("new UiSelector().text(\"DocID\")");
	By storyTracker = AppiumBy.androidUIAutomator("new UiSelector().text(\"Story\")");
	By customerBugTracker = AppiumBy.androidUIAutomator("new UiSelector().text(\"Customer Bug\")");
	By internalBugTracker = AppiumBy.androidUIAutomator("new UiSelector().text(\"Internal Bug\")");

	public void createProject(String projName) {
		cmd.sendText(name, projName);
		cmd.click(projCreate);
	}

	public void createProject(Map<String, String> map) {
		cmd.sendText(name, map.get("Name"));
		if (map.containsKey("Description"))
			cmd.sendText(desc, map.get("Description"));
		if (map.containsKey("Identifier")) {
			cmd.clear(identifier);
			cmd.sendText(identifier, map.get("Identifier"));
		}
		if (map.containsKey("Mark Public")) {
			if (map.get("Mark Public").equalsIgnoreCase("true"))
				cmd.click(markPublic);
		}
		if (map.containsKey("Sub Project Of")) {
			cmd.click(subOfProj);
			switch (map.get("Sub Project Of")) {
			case "DocID":
				cmd.click(docIDProj);
				break;
			default:
				cmd.pressAndroidKey(AndroidKey.BACK);
				scenario.log("Project Name is invalid: " + map.get("Sub Project Of"));
				scenario.log(
						"As this is an optional field, continuing with the Project creation assuming this as bad data");
				break;
			}

		}
		if (map.containsKey("Tracker")) {
			cmd.click(tracker);
			switch (map.get("Tracker")) {
			case "Story":
				cmd.click(storyTracker);
				break;
			case "Customer Bug":
				cmd.click(customerBugTracker);
				break;
			case "Internal Bug":
				cmd.click(internalBugTracker);
				break;
			default:
				scenario.log("Tracker Name is invalid: " + map.get("Tracker"));
				scenario.log(
						"As this is an optional field, continuing with the Project creation assuming this as bad data");
				cmd.pressAndroidKey(AndroidKey.BACK);
				break;
			}
			cmd.pressAndroidKey(AndroidKey.BACK);
		}
		cmd.click(projCreate);
	}

}
