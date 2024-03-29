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
    Given a book with ISBN 0750962925
    When I search for the book
    Then the response includes the following
      | totalItems | 1 			      		|
      | kind       | books#volumes			|
    And the response includes the following in any order
      | items.volumeInfo.title              | Shipwrecks of the P&o Line |
      | items.volumeInfo.publisher          | History Press              |
      | items.volumeInfo.pageCount          | 180                        |

  Scenario: Book title includes a subtitle
    Given a book with isbn 0750962917
    When I search for the book
    Then the book title should be "The Union-Castle Line: Sailing Like Clockwork"

  Scenario: Retrieve book by volume ID
    Given a book with volume ID XQVpuQEACAAJ
    When I retrieve the book
    Then the book title should be "QE2"
    And the book subtitle should be "The Cunard Line Flagship, Queen Elizabeth 2"
