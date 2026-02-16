package utilities;

import static config.EnvVars.normalww;
import static config.EnvVars.resultPath;
import static config.EnvVars.shortwait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import config.DriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasSettings;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Scenario;

public class WebDriverUtility extends BaseClass {

	public WebElement we;
	public WebDriverWait ww;
	public WebDriverWait wwShort;
	public JavascriptExecutor js;
	public int attempt = 5;
	public int attemptIntervalInSec = 1;

	public WebDriverUtility() {
		ww = new WebDriverWait(driver, Duration.ofSeconds(normalww));
		wwShort = new WebDriverWait(driver, Duration.ofSeconds(shortwait));
		js = (JavascriptExecutor) driver;
	}

	public void click(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).click();
	}

	public void sliderValueIncrease(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).click();
		Actions ac = new Actions(driver);
		ac.pause(Duration.ofSeconds(2)).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build().perform();
	}

	public void performAndroidSearchAction() {
		((JavascriptExecutor) (driver)).executeScript("mobile: performEditorAction", Map.of("action", 3));
//		3 = IME_ACTION_SEARCH
//		2 = IME_ACTION_GO
//		6 = IME_ACTION_DONE
	}

	public WebElement findElement(By by) {
		return ww.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public boolean isElementDisplayedWithoutWait(By by) {
		try {
			driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean isElementVisible(By by) {
		WebElement ele = null;
		try {
			ele = wwShort.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
		}

		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isElementPresent(By by) {
		WebElement ele = null;
		try {
			ele = wwShort.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
		}

		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isElementPresent(WebElement ele) {
		boolean result = false;
		while (attempt > 0) {
			try {
				result = ele.isDisplayed();
				return result;
			} catch (Exception e) {
				wait(attemptIntervalInSec);
				attempt--;
			}
		}
		return result;
	}

	public List<WebElement> findElements(By by, DriverWait waitType) {
		List<WebElement> eles = null;
		try {
			if (waitType == DriverWait.NORMAL_WAIT) {
				eles = ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			} else if (waitType == DriverWait.SHORT_WAIT) {
				eles = wwShort.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			}
			return eles;
		} catch (Exception e) {
			return driver.findElements(by);
		}
	}

	public List<String> getTextOfMultipleElements(By by) {
		List<String> texts = new ArrayList<String>();
		for (WebElement ele : findElements(by, DriverWait.SHORT_WAIT)) {
			String eleText = ele.getText().trim();
			texts.add(eleText);
		}
		return texts;
	}

	public void click(WebElement element) {
		ww.until(ExpectedConditions.visibilityOf(element));
		ww.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void waitForElementToBeVisible(By by) {
		ww.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public boolean checkElementIsClickable(By by) {
		WebElement ele = null;
		try {
			ele = ww.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
		}
		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public WebElement getIfElementIsClickable(By by) {
		WebElement ele = null;
		try {
			ele = ww.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
		}
		return ele;
	}

	public void contextClick(By by) {
		Actions ac = new Actions(driver);
		this.waitForElementToBeClickable(by);
		ac.contextClick(driver.findElement(by)).build().perform();
	}

	public void waitForAlertToDisplay() {
		ww.until(ExpectedConditions.alertIsPresent());
	}

	public void clear(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).clear();
	}

	public void clear(WebElement ele) {
		ww.until(ExpectedConditions.elementToBeClickable(ele)).clear();
	}

	public void sendText(By by, String str) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(str);
	}

	public void keyboardSendText(By by, String str) {
		new Actions(driver).sendKeys(findElement(by), str).build().perform();
	}

	public String getText(By by) {
		return ww.until(ExpectedConditions.presenceOfElementLocated(by)).getText().trim();
	}

	public String getTextWithStaleCheck(By by, int attemptCount) {
		while (attemptCount > 0) {
			try {
				wait(1);
				ww.until(ExpectedConditions.presenceOfElementLocated(by));
				break;
			} catch (StaleElementReferenceException e) {
				attemptCount--;
			}
		}
		return ww.until(ExpectedConditions.presenceOfElementLocated(by)).getText().trim();
	}

	public String getAttribute(By by, String att) {
		return ww.until(ExpectedConditions.presenceOfElementLocated(by)).getAttribute(att).trim();
	}

	public String getText(WebElement element) {
		return ww.until(ExpectedConditions.visibilityOf(element)).getText().trim();
	}

	public boolean checkElementIsNotPresent(By by) {
		try {
			return wwShort.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
		}
		return false;
	}

	public Select getSelectElement(By by) {
		return new Select(ww.until(ExpectedConditions.presenceOfElementLocated(by)));
	}

	public void selectByValue(By by, String value) {
		getSelectElement(by).selectByContainsVisibleText(value);
	}

	public boolean checkElementIsEnabled(By by) {
		boolean result = false;
		try {
			result = ww.until(ExpectedConditions.presenceOfElementLocated(by)).isEnabled();
		} catch (Exception e) {
		}
		return result;
	}

	public WebElement waitForElementToBeClickable(By by) {
		return ww.until(ExpectedConditions.elementToBeClickable(by));
	}

	public List<List<WebElement>> getTableData(By by) {
		WebElement table = driver.findElement(by);
		List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
		List<List<WebElement>> tableData = new ArrayList<List<WebElement>>();
		for (WebElement webElement : rows) {
			tableData.add(webElement.findElements(By.tagName("td")));
		}
		return tableData;
	}

	public void takeScreenShot(String fileName) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f,
					new File(resultPath + fileName + Instant.now().toString().replace(":", "-") + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void attachScreenShot(Scenario s) {
		byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		s.attach(src, "image/png", "screenshot");
	}

	public void mouseClick(By by) {
		Actions a = new Actions(driver);
		WebElement ele = findElement(by);
		ww.until(ExpectedConditions.visibilityOf(ele));
		a.click(ele).build().perform();
	}

	public void wait(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pressAndroidKey(AndroidKey key) {
		((AndroidDriver) driver).pressKey(new KeyEvent().withKey(key));
	}

	public void longClickGesture(By by) {
		WebElement we = ww.until(ExpectedConditions.elementToBeClickable(by));
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) we).getId(), "duration", 1000));
	}

	public void longClickGesture(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 1000));
	}

	public void clickGesture(By by) {
		WebElement we = ww.until(ExpectedConditions.elementToBeClickable(by));
		((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) we).getId()));
	}

	public void clickGesture(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()));
	}

	public void checkNScrollIfElementIsObscured(By operationBy, By obscuringBy) {
		Rectangle ope = findElement(operationBy).getRect();
		int opeY = ope.getY();
		int opeHeight = ope.getHeight();

		Rectangle obsc = findElement(obscuringBy).getRect();
		// Point provides x and y location of getRect only
//		Point point = findElement(obscuringBy).getLocation();
//		int obscX = obsc.getX();
		int obscY = obsc.getY();
		int obscHeight = obsc.getHeight();

		if ((obscY <= opeY && opeY <= (obscY + obscHeight))
				|| (obscY <= (opeY + opeHeight) && (opeY + opeHeight) <= (obscY + obscHeight))) {
			Dimension dim = driver.manage().window().getSize();
			int windowWidth = dim.getWidth();
			int windowHeight = dim.getHeight();
			int startX = windowWidth / 2;
			int startY = (windowHeight * 3) / 4;
			int endX = startX;
			int endY = windowHeight / 4;

			new AndroidTouchAction((PerformsTouchActions) driver).press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endX, endY))
					.release().perform();
		}
	}

	public WebElement verticalScrollUpForEle(By scrollBoundRef, By find, int maxScrollCount) {

		wait(2);

		WebElement scrollBoundEle = findElement(scrollBoundRef);
		WebElement findEle = null;

		Rectangle rec = scrollBoundEle.getRect();
		int startX = rec.getX() + rec.getWidth() / 2;
		int startY = rec.getY();
		int endY = rec.getHeight();

		while (maxScrollCount > 0) {
			try {
				wait(1);
				findEle = driver.findElement(find);
				return findEle;
			} catch (Exception e) {
				String beforeScrollPS = driver.getPageSource();

				PointerInput input = new PointerInput(Kind.TOUCH, "first-finger");
				Sequence seq = new Sequence(input, 0);
				seq.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX,
						(int) (startY + (endY * .1))));
				seq.addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
				seq.addAction(input.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), startX,
						(int) (startY + endY - (endY * .1))));
				seq.addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
				((AppiumDriver) driver).perform(Collections.singletonList(seq));

				maxScrollCount--;

				String afterScrollPS = driver.getPageSource();
				if (beforeScrollPS.equals(afterScrollPS)) {
					return null;
				}
			}
		}

		return null;
	}

	public WebElement verticalScrollDownForEle(By find, int maxScrollCount) {

		WebElement findEle = null;

		Dimension rec = driver.manage().window().getSize();

		while (maxScrollCount > 0) {
			wait(1);
			try {
				findEle = driver.findElement(find);
				if (findEle != null) {
					break;
				}
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
						ImmutableMap.of("left", 0, "top", 0, "width", rec.getWidth(), "height", rec.getHeight() / 2,
								"direction", "down", "percent", 1.0));
				maxScrollCount--;
			}
		}

		return findEle;
	}

	public WebElement verticalScrollDownForEle(By scrollBound, By find, int maxScrollCount) {

		WebElement findEle = null;

		Rectangle rec = findElement(scrollBound).getRect();

		while (maxScrollCount > 0) {
			wait(1);
			try {
				findEle = ((AppiumDriver) driver).findElement(find);
				if (findEle != null) {
					break;
				}
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
						ImmutableMap.of("left", rec.getX(), "top", rec.getY(), "width", rec.getWidth(), "height",
								rec.getHeight() * .9, "direction", "down", "percent", 1.0, "speed", 720));
				maxScrollCount--;
			}
		}
		return findEle;
	}

	public WebElement horizontalSwipeTillEleIsDisplayed(By swipeBoundRef, By find, int maxSwipeCount) {
		WebElement we = findElement(swipeBoundRef);
		WebElement findEle = null;

		while (maxSwipeCount > 0) {
			wait(1);
			try {
				findEle = driver.findElement(find);
				if (findEle != null) {
					break;
				}
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
						((RemoteWebElement) we).getId(), "direction", "left", "percent", 1));
				maxSwipeCount--;
			}
		}

		return findEle;
	}

	public void performSwipe(By swipeBoundRef, String dir, int swipeCount) {
		WebElement we = findElement(swipeBoundRef);

		while (swipeCount > 0) {
			wait(2);
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
					((RemoteWebElement) we).getId(), "direction", dir, "percent", 1, "speed", 720));
			swipeCount--;
		}
	}

	public void scrollEle(By scrollBounArea) {
		WebElement scrollBoundEle = findElement(scrollBounArea);

		Rectangle rec = scrollBoundEle.getRect();
		int startX = rec.getWidth() / 2;
		int startY = rec.getY();
		int endY = rec.getHeight();
		PointerInput input = new PointerInput(Kind.TOUCH, "first-finger");
		Sequence seq = new Sequence(input, 0);
		seq.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX,
				(int) (startY + endY - (endY * .1))));
		seq.addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		seq.addAction(input.createPointerMove(Duration.ofSeconds(3), PointerInput.Origin.viewport(), startX, startY));
		seq.addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		((AppiumDriver) driver).perform(Collections.singletonList(seq));

	}

	public ArrayList<String> getTextOfEachEleInScrollView(By scrollBounArea, By getBys, int maxScrollCount) {
		ArrayList<String> eleTexts = new ArrayList<String>();

//		WebElement scrollBoundEle = findElement(scrollBounArea);
//
//		Rectangle rec = scrollBoundEle.getRect();
//		int startX = rec.getWidth() / 2;
//		int startY = rec.getY();
//		int endY = rec.getHeight();

		while (maxScrollCount > 0) {
			wait(1);
			for (WebElement ele : findElements(getBys, DriverWait.NORMAL_WAIT)) {
				eleTexts.add(ele.getText());
			}
			String beforeScrollPS = driver.getPageSource();
			scrollEle(scrollBounArea);
//			PointerInput input = new PointerInput(Kind.TOUCH, "first-finger");
//			Sequence seq = new Sequence(input, 0);
//			seq.addAction(input.createPointerMove(Duration.ZERO,
////					PointerInput.Origin.fromElement(scrollBoundEle), startX, (int)(endY - (endY * .1))));
//			    PointerInput.Origin.viewport(), startX, (int) (startY + endY - (endY * .1))));
//			seq.addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
//			seq.addAction(input.createPointerMove(Duration.ofSeconds(3),
////					PointerInput.Origin.fromElement(scrollBoundEle), startX, startY));
//			    PointerInput.Origin.viewport(), startX, startY));
//			seq.addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
//
//			((AppiumDriver) driver).perform(Collections.singletonList(seq));

//			((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
//			    ImmutableMap.of("left", 0, "top", 0, "width", rec.getWidth(), "height",
//			        rec.getHeight(), "direction", "down", "percent", 1));
			maxScrollCount--;
			String afterScrollPS = driver.getPageSource();
			if (beforeScrollPS.equals(afterScrollPS))
				break;
		}

		return eleTexts;
	}

//	public HashMap<String, TreeSet<String>> getGroupedElementsInScrollView(int maxScrollCount) {
//		enforceXPath1();
//		POOrgChartAllEmp po = new POOrgChartAllEmp();
//
//		HashMap<String, TreeSet<String>> groupedEmps = new HashMap<String, TreeSet<String>>();
//		String lastGroup = "";
//
//		while (maxScrollCount > 0) {
//			List<WebElement> groups = new ArrayList<WebElement>();
//			List<String> groupNames = new ArrayList<String>();
//			for (WebElement webElement : findElements(po.appliedGroupsInEmpList, DriverWait.SHORT_WAIT)) {
//				if (!groupNames.contains(getText(webElement)) && !lastGroup.equals(getText(webElement))) {
//					groups.add(webElement);
//					groupNames.add(getText(webElement));
//					groupedEmps.put(getText(webElement), new TreeSet<String>());
//				}
//			}
//			int noOfGroupsPresent = groupNames.size();
//
//			if (!lastGroup.equals("")) {
//				List<String> eles = getTextOfMultipleElements(po.allEmployees);
//				TreeSet<String> EmpNames = eles.stream().collect(Collectors.toCollection(TreeSet::new));
//				groupedEmps.get(lastGroup).addAll(EmpNames);
//			}
//
//			for (int i = 0; i < noOfGroupsPresent; i++) {
//				List<String> eles = getTextOfMultipleElements(po.getByOfNamesAfterGroup(groupNames.get(i)));
//				TreeSet<String> EmpNames = eles.stream().collect(Collectors.toCollection(TreeSet::new));
//				groupedEmps.get(groupNames.get(i)).addAll(EmpNames);
//				if (!lastGroup.equals("")) {
//					groupedEmps.get(lastGroup).removeAll(EmpNames);
//				}
//				lastGroup = groupNames.get(i);
//			}
//
//			String beforeScrollPS = driver.getPageSource();
//			scrollEle(po.employeeListScrollable);
//			String afterScrollPS = driver.getPageSource();
//			if (beforeScrollPS.equals(afterScrollPS)) {
//				break;
//			}
//			maxScrollCount--;
//		}
//		enforceDefaultXPath();
//		return groupedEmps;
//	}

	public void enforceXPath1() {
		((HasSettings) driver).setSetting("enforceXPath1", true);
	}

	public void enforceDefaultXPath() {
		((HasSettings) driver).setSetting("enforceXPath1", false);
	}

	public WebElement findImgEle(By by) {
		return ww.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void clickImgEle(By by) {
		ww.until(ExpectedConditions.presenceOfElementLocated(by)).click();
	}
}
