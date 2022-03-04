Feature: sample step arguments demo
  ... ...
  Scenario: for step
    Given I have 42 cucumbers in my belly
    Given I have a blue ball

  Scenario: Optional text demo
    Given I have 1 apple in my belly
    And I have 42 apples in my belly

  Scenario: Alternative text demo
    Given I have 1 banana in my belly
    Given I have 3 bananas in my stomach

  Scenario: Escaping demo
    Given I have 1 {what} beef in my belly (amazing!)


  Scenario: Multiline Step Arguments demo
    Given a blog post named "Random" with Markdown body
      """markdown
      Some Title, Eh?
      ===============
      Here is the first paragraph of my blog post. Lorem ipsum dolor sit amet,
      consectetur adipiscing elit.
      """

    Given the following users exist:
      | name   | email              | twitter         |
      | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
      | Julien | julien@cucumber.io | @jbpros         |
      | Matt   | matt@cucumber.io   | @mattwynne      |


  Scenario: Cucumber Transformers - Param Type
    Given today is 2021-11-19


  Scenario: Cucumber Transformers - Data Tables Type
#    Given a list of authors in a table
#      | firstName   | lastName | birthDate  |
#      | Annie M. G. | Schmidt  | 1911-03-20 |
#      | Roald       | Dahl     | 1916-09-13 |
#      | Alan        | Luo      |            |
#      | Clark       | Peng     |   [EmptyStr]  |
    Given a list of authors in a table
      | firstName   | Alan          | Clark  |
      | lastName    | Luo           | Peng |
      | birthDate   | 1986-09-17    | 1916-09-13 |


  Scenario: Cucumber Transformers - Default Transformers
    Given this is Json data: {firstName: 'alan',lastName: 'Luo',birthDate:'1986-09-17'}
