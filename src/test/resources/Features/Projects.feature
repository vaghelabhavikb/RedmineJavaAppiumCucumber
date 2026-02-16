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

  Scenario: Verify user can create project with optional fields
    When User open create project view
    And Provides project details to create a project
      | Name           | Vendors         |
      | Description    | Vendors Records |
      | Mark Public    | True            |
      | Sub Project Of | DocID           |
      | Tracker        | Story           |
    Then Project named "Vendors" should be created

  Scenario: Verify user can create project with optional fields
    When User open create project view
    And Provides project details to create a project
      | Name           | Accounts         |
      | Description    | Accounts Records |
      | Identifier     | acc              |
      | Sub Project Of | DocID1           |
    Then Project named "Accounts" should be created

  Scenario: Verify that user can create tasks within a project
    Given user open "DocID" project
    When user opens create tasks form
    And enters task fields values and creates this task
      | Name               | Ability to insert TOC in the last page                                                                                            |
      | Description        | This feature will provide ability to insert TOC in the last page of the document. User can also add/remove/edit the inserted TOC |
      | AssignedTo         | Bhavik Vaghela                                                                                                                    |
      | Category           | Customer Bug                                                                                                                      |
      | CompletePercentage | 2                                                                                                                                 |
      | Status             | Open                                                                                                                              |
      | ExpenditureTime    | 3                                                                                                                                 |
      | StartDate          | In 7 days                                                                                                                        |
    And navigates to the created task details view
    Then the task info should display correct fields values

 @IP
  Scenario: Verify that user can create tasks within a project
    Given user open "DocID" project
    When user opens create tasks form
    And enters task fields values and creates this task
      | Name            | Ability to insert TOC in the first page                                                                                            |
      | Description     | This feature will provide ability to insert TOC in the first page of the document. User can also add/remove/edit the inserted TOC |
      | Category        | Story                                                                                                                              |
      | Status          | Open                                                                                                                             |
      | ExpenditureTime | 5                                                                                                                                  |
      | StartDate       | In 6 days                                                                                                                         |
    And navigates to the created task details view
    Then the task info should display correct fields values
