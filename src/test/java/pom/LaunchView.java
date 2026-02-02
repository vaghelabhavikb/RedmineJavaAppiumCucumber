package pom;

import org.openqa.selenium.By;

import utilities.BaseClass;
import utilities.WebDriverUtility;

public class LaunchView extends BaseClass{

	WebDriverUtility cmd = new WebDriverUtility();
	
	By projectsView = By.xpath(
			"//android.widget.LinearLayout[@resource-id=\"com.ideil.redmine:id/bottom_navigation_bar_item_container\"]"
					+ "/android.widget.FrameLayout[3]");
	
	
	public void navToProjectsView(){
		cmd.click(projectsView);
	}
	
}
