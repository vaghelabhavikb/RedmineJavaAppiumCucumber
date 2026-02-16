package tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.CreateProjView;
import pom.CreateTaskView;
import pom.LaunchView;
import pom.ProjectSearchView;
import pom.ProjectView;
import pom.ProjectsView;
import pom.TaskDetailsView;
import utilities.BaseClass;

public class TProjects extends BaseClass {

	LaunchView launchView = new LaunchView();
	ProjectsView projectsView = new ProjectsView();
	ProjectView projectView = new ProjectView();
	ProjectSearchView projectSearchView = new ProjectSearchView();
	CreateProjView createProjView = new CreateProjView();
	TaskDetailsView taskDetailsView = new TaskDetailsView();
	CreateTaskView createTaskView = new CreateTaskView();

	HashMap<String, String> expMap = new HashMap<String, String>();
	HashMap<String, String> actMap = new HashMap<String, String>();

	@And("User go to Projects view")
	public void userGoToProjectsView() {
		launchView.navToProjectsView();
	}

	@When("User open create project view")
	public void userOpenCreateProjectView() {
		projectsView.launchCreateProjectView();
	}

	@And("Provides {string} to create a project")
	public void providesProjectNameToCreateAProject(String name) {
		createProjView.createProject(name);
	}

	@When("Provides project details to create a project")
	public void providesProjectDetailsToCreateAProject(Map<String, String> map) {
		createProjView.createProject(map);
	}

	@Then("Project named {string} should be created")
	public void projectShouldBeCreatedWithTheProvidedDetails(String name) {
		projectsView.launchProjectSearchView();
		projectSearchView.searchForTheProject(name);
		Assert.assertTrue("Project not created", projectSearchView.isProjectPresent(name));
	}

	@Given("user open {string} project")
	public void userOpenProject(String string) {
		projectsView.navToDocIDProj();
	}

	@When("user opens create tasks form")
	public void userOpensCreateTasksForm() {
		projectView.openCreateTaskView();
	}

	@When("enters task fields values and creates this task")
	public void entersTaskFieldsValuesAndCreatesThisTask(Map<String, String> map) {
		createTaskView.CreateTask(map);
		expMap.clear();
		expMap.putAll(map);
	}

	@And("navigates to the created task details view")
	public void navigatesToTheCreatedTaskDetailsView() {
		projectView.openTaskDetailsView(expMap.get("Name"));
	}

	@Then("the task info should display correct fields values")
	public void theTaskInfoShouldDisplayCorrectFieldsValues() {
		actMap.clear();
		actMap = taskDetailsView.getTaskInfo();
		for (String key : expMap.keySet()) {
			System.out.println("key: " + key + " exp: " + expMap.get(key) + " act: " + actMap.get(key));
			System.out.println("------------------------");
			Assert.assertTrue("Comparison of key: " + key, actMap.get(key).contains(expMap.get(key)));
		}
	}

}
