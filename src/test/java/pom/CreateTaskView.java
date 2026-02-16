package pom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import utilities.BaseClass;
import utilities.JavaUtils;
import utilities.WebDriverUtility;

public class CreateTaskView extends BaseClass {

	WebDriverUtility cmd = new WebDriverUtility();

	By nameET = By.id("com.ideil.redmine:id/input_subject");
	By descET = By.id("com.ideil.redmine:id/input_description");

	By assignedToET = By.id("com.ideil.redmine:id/input_assign_to");
//	By versionET = By.id("com.ideil.redmine:id/input_version");
//	By watchersET = By.id("com.ideil.redmine:id/input_watchers");
	By categoryET = By.id("com.ideil.redmine:id/input_category");
	By completenessSB = By.id("com.ideil.redmine:id/seek_done_ratio");
	By statusET = By.id("com.ideil.redmine:id/input_status");
//	By trackerET = By.id("com.ideil.redmine:id/input_tracker");
	By expenditureTimeET = By.id("com.ideil.redmine:id/input_labour_time");
	By startDateET = By.id("com.ideil.redmine:id/input_date_start");
//	By endDateET = By.id("com.ideil.redmine:id/input_date_end");
	By createTaskBT = By.id("com.ideil.redmine:id/btn_issue_create");
	
	By datePickerScrollableRV = By.className("androidx.recyclerview.widget.RecyclerView");
	By confirmDateBtn = By.id("com.ideil.redmine:id/mdtp_ok");

	By getByOfMemeberinMembersView(String name) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.ideil.redmine:id/title\").text(\"" + name + "\")");
	}

	By getByOfCategoryByName(String name) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.ideil.redmine:id/category\").text(\"" + name + "\")");
	}

	By getByOfStatusByName(String name) {
		return AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.ideil.redmine:id/title\").text(\"" + name + "\")");
	}

	By getByOfDateToSelect(String date) {
		return AppiumBy.accessibilityId(date);
	}
	

	public void CreateTask(Map<String, String> data) {
		for (String key : data.keySet()) {
			switch (key) {
			case "Name":
				cmd.sendText(nameET, data.get(key));
				break;
			case "Description":
				cmd.sendText(descET, data.get(key));
				break;
			case "AssignedTo":
				if (!cmd.isElementDisplayedWithoutWait(assignedToET)) {
					cmd.verticalScrollDownForEle(assignedToET, 3);
				}
				cmd.click(assignedToET);
				cmd.click(getByOfMemeberinMembersView(data.get(key)));
				break;
			case "Category":
				if (!cmd.isElementDisplayedWithoutWait(categoryET)) {
					cmd.verticalScrollDownForEle(categoryET, 3);
				}
				cmd.click(categoryET);
				cmd.click(getByOfCategoryByName(data.get(key)));
				break;
			case "CompletePercentage":
				if (!cmd.isElementDisplayedWithoutWait(completenessSB)) {
					cmd.verticalScrollDownForEle(completenessSB, 3);
				}
				cmd.sendText(completenessSB, data.get(key));
				break;
			case "Status":
				if (!cmd.isElementDisplayedWithoutWait(statusET)) {
					cmd.verticalScrollDownForEle(statusET, 3);
				}
				cmd.click(statusET);
				cmd.click(getByOfStatusByName(data.get(key)));
				break;
			case "ExpenditureTime":
				if (!cmd.isElementDisplayedWithoutWait(expenditureTimeET)) {
					cmd.verticalScrollDownForEle(expenditureTimeET, 3);
				}
				cmd.sendText(expenditureTimeET, data.get(key));
				break;
			case "StartDate":
				if (!cmd.isElementDisplayedWithoutWait(startDateET)) {
					cmd.verticalScrollDownForEle(startDateET, 3);
				}
				cmd.click(startDateET);
				String[] arr = data.get(key).split(" ");
				By futureDate = getByOfDateToSelect(new JavaUtils().getDateFromNoOfDaysToday(Integer.valueOf(arr[1])));
				if (!cmd.isElementDisplayedWithoutWait(futureDate)) {
					cmd.verticalScrollDownForEle(datePickerScrollableRV, futureDate, 3);
				}
				cmd.click(futureDate);
				cmd.click(confirmDateBtn);
				break;
			default:
				scenario.log("Invalid key: " + key);
				break;
			}
		}

		cmd.click(createTaskBT);
	}
}
