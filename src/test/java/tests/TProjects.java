package tests;

import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.CreateProjView;
import pom.LaunchView;
import pom.ProjectSearchView;
import pom.ProjectsView;
import utilities.BaseClass;

public class TProjects extends BaseClass {

	LaunchView launchView = new LaunchView();
	ProjectsView projectsView = new ProjectsView();
	CreateProjView createProjView = new CreateProjView();
	ProjectSearchView projectSearchView = new ProjectSearchView();

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
	public void providesProjectDetailsToCreateAProject(Map<String,String> map) {
		createProjView.createProject(map);
	}

	@Then("Project named {string} should be created")
	public void projectShouldBeCreatedWithTheProvidedDetails(String name) {
		projectsView.launchProjectSearchView();
		projectSearchView.searchForTheProject(name);
		Assert.assertTrue("Project not created", projectSearchView.isProjectPresent(name));
	}
}
