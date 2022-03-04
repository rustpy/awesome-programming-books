@issue=CR-9
@tmsLink=CR-9
Feature: Calculator
  Simple calculator for adding **two** numbers

  Scenario Outline: Add two numbers
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the two numbers are added
    Then the result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 50          | 70           | 120    |
      | -100        | 80           | -20    |
      | 50          | 70           | 121    |