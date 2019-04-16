Feature: Call to greet the world

  Scenario: As an anonymous user, I want to greet the world
    When greeting the world
    Then the status code is 200
    And the world is greeted by Cucumber

  Scenario: As a known user, I want to greet the world
    Given the name of the greeter is Arne
    When greeting the world
    Then the status code is 200
    And the world is greeted by Arne

  Scenario Outline: As a known user, I want to greet the world
    Given the name of the greeter is <name>
    When greeting the world
    Then the status code is 200
    And the world is greeted by <name>
    Examples:
      | name     |
      | Arne     |
      | John Doe |

   Scenario: As a forbidden user, I want to greet the world
    Given the name of the greeter is John Doe
    But the user is not allowed
    When greeting the world
    Then the status code is 403
