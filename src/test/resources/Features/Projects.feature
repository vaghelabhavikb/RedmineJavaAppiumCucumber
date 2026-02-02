Feature: Projects creation and maintenance
The workflows cover here projects creation and maintenance

  Background:
    Given User go to Projects view

  @IP
  Scenario Outline: Verify user can create multiple projects
    When User open create project view
    And Provides <Project Name> to create a project
    Then Project should be created with the provided details

    Examples:
      | Project Name   |
      | "Assets"       |
      | "Fuel Records" |

  Scenario: Verify user can create multiple projects
    When User open create project view
    And Provides project details to create a project
    Then Project should be created with the provided details
