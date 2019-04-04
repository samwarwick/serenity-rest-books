Feature: Search for books
  In order to locate the right book
  As a consumer
  I want to confirm the details are correct

  @smoke
  Scenario: Search for book by ISBN
    Given a book with ISBN 9780752465784
    When I search for the book
    Then the book title should be "Shipwrecks of the Cunard Line"

  Scenario: Search returns volume information
    Given a book with ISBN 9781451648546
    When I search for the book
    Then the response includes the following
      | totalItems | 1 			      		|
      | kind       | books#volumes			|
    And the response includes the following in any order
      | items.volumeInfo.title 				| Steve Jobs          |
      | items.volumeInfo.publisher 	    	| Simon and Schuster  |
      | items.volumeInfo.pageCount 			| 630                 |

  Scenario: Book title includes a subtitle
    Given a book with isbn 0750962917
    When I search for the book
    Then the book title should be "The Union-Castle Line: Sailing Like Clockwork"
