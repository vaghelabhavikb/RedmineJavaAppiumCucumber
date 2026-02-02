package tests;

import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.LaunchView;
import pom.ProjectsView;
import utilities.BaseClass;
import utilities.TestDataUtilities;

public class TProjects extends BaseClass {

	LaunchView launchView = new LaunchView();
	ProjectsView projectsView = new ProjectsView();
	TestDataUtilities jsonData = new TestDataUtilities("CreateProjects");

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
		projectsView.createProject(name);
	}

	@When("Provides project details to create a project")
	public void providesProjectDetailsToCreateAProject() {
		for (Map<String, String> project : jsonData.getJsonObjAsListMap("$.Projects")) {
			projectsView.createProject(project);
		}
	}

	@Then("Project should be created with the provided details")
	public void projectShouldBeCreatedWithTheProvidedDetails() {

	}
}
