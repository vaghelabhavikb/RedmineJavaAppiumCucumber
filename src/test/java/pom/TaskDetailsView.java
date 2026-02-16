package pom;

import java.util.HashMap;

import org.openqa.selenium.By;

import utilities.BaseClass;
import utilities.WebDriverUtility;

public class TaskDetailsView extends BaseClass {

	WebDriverUtility cmd = new WebDriverUtility();

	By nameTV = By.id("com.ideil.redmine:id/issues");
	By assignedToTV = By.id("com.ideil.redmine:id/tv_assign");
	By startDateTV = By.id("com.ideil.redmine:id/tv_date_start");
	By completnessTV = By.id("com.ideil.redmine:id/tv_done_ratio");
	By expenditureTimeTV = By.id("com.ideil.redmine:id/tv_estimated_hours");
	By categoryTV = By.id("com.ideil.redmine:id/tv_category");
	By statusTV = By.id("com.ideil.redmine:id/tv_status");
	By descTV = By.id("com.ideil.redmine:id/tv_description");

	public HashMap<String, String> getTaskInfo() {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Name", cmd.getTextWithStaleCheck(nameTV, 3));
		map.put("AssignedTo", cmd.getText(assignedToTV));
		map.put("StartDate", cmd.getText(startDateTV));
		map.put("CompletePercentage", cmd.getText(completnessTV));
		map.put("ExpenditureTime", cmd.getText(expenditureTimeTV));
		map.put("Category", cmd.getText(categoryTV));
		map.put("Status", cmd.getText(statusTV));
		map.put("Description", cmd.getText(descTV));

		return map;
	}

}
