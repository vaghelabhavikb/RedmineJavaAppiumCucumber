package pom;

import org.openqa.selenium.By;

import utilities.BaseClass;
import utilities.WebDriverUtility;

public class LaunchView extends BaseClass {

	WebDriverUtility cmd = new WebDriverUtility();

	By projectsView = By.xpath("(//android.widget.ImageView[@content-desc=\"icon\"])[3]");

	public void navToProjectsView() {
		cmd.click(projectsView);
	}

}
