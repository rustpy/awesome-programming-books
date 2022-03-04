@smoke
Feature: simple feature.

  As a Coach... ...
  I want ... ...
  So that ... ...

  Background: This is Background 0
    Given this is sample high-level background

  Rule: This is a Rule
    Background: This is Background 1
      Given this is sample behavior

    Scenario: This is a scenario 1
      Given this is sample given
      When this is sample when
      Then this is sample then

    Scenario: This is a scenario 2
      Given this is sample given
      When this is sample when
      Then this is sample then

  Rule: This is a Rule
    Background: This is Background 2
      Given this is sample behavior

    Scenario: This is a scenario 3
      Given this is sample given
      When this is sample when
      Then this is sample then
