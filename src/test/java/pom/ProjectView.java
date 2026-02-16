package pom;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import utilities.BaseClass;
import utilities.WebDriverUtility;

public class ProjectView extends BaseClass {

	WebDriverUtility cmd = new WebDriverUtility();

	By addNewItemIMG = AppiumBy.androidUIAutomator(
			"new UiSelector().className(\"android.widget.ScrollView\")"
			+ ".childSelector(new UiSelector().className(\"android.widget.ImageButton\"))");

	By addNewTaskTV=AppiumBy.androidUIAutomator("new UiSelector().text(\"CREATE A TASK\")");
	
	By getByOfProjectTask(String name) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.ideil.redmine:id/issues\").text(\"" + name + "\")");
	}
	
	
	public void openCreateTaskView() {
		cmd.click(addNewItemIMG);
		cmd.click(addNewTaskTV);
	}
	
	public void openTaskDetailsView(String name) {
		cmd.click(getByOfProjectTask(name));
	}
	

}
