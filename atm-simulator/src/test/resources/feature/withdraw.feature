Feature: Withdrawal

  Scenario: Balance reduced
    Given there's an Account with number 123456, PIN 123456 and balance 100
    And I entered 123456 as Account number and 123456 as PIN
    When I withdraw 50
    Then I should see the message "Your current balance is 50".
