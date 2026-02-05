package tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BaseClass;
import utilities.WebDriverUtility;

public class SetupTearDown extends BaseClass{

	@Before
	public void setUp(Scenario s) {
		scenario = s;
		driver = getDriverInstance();
	}

	@After
	public void tearDown(Scenario s) {
		if (s.isFailed()) {
			WebDriverUtility cmd = new WebDriverUtility();
			String[] arr = s.getId().split("/");
			cmd.takeScreenShot(arr[arr.length - 1].replace(":", ""));
			cmd.attachScreenShot(s);
		}
//		driver.quit();
	}
	
	
}
