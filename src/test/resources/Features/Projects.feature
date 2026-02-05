Feature: Projects creation and maintenance
The workflows cover here projects creation and maintenance

  Background:
    Given User go to Projects view

  Scenario Outline: Verify user can create multiple projects
    When User open create project view
    And Provides <Project Name> to create a project
    Then Project named <Project Name> should be created

    Examples:
      | Project Name   |
      | "Assets"       |
      | "Fuel Records" |

  @IP
  Scenario: Verify user can create project with optional fields
    When User open create project view
    And Provides project details to create a project
      | Name           | Vendors         |
      | Description    | Vendors Records |
      | Mark Public    | True            |
      | Sub Project Of | DocID           |
      | Tracker        | Story           |
    Then Project named "Vendors" should be created

  @IP
  Scenario: Verify user can create project with optional fields
    When User open create project view
    And Provides project details to create a project
      | Name           | Accounts         |
      | Description    | Accounts Records |
      | Identifier     | acc              |
      | Sub Project Of | DocID1           |
    Then Project named "Accounts" should be created
