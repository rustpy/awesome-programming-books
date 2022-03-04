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




#  Cucumber	        Unit Test
#  Feature (功能)	test suite （测试用例集）
#  Scenario（情景）	test case （测试用例）
#  Given（给定）	    setup（创建测试所需环境）
#  When（当）	    test（触发被测事件）
#  Then（则）	    assert(断言，验证结果)