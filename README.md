# Serenity-BDD REST Demo using Google Books API

Sample project demonstrating the use of [Serenity-BDD](https://serenity-bdd.github.io/) and [REST Assured](https://rest-assured.io/) to test the [Google Books API](https://developers.google.com/books).
Also includes a [Jenkinsfile](Jenkinsfile) to enable execution in a Jenkins pipeline.

Requires Java 21 or later.

Serenity-BDD version [4.2.34](https://github.com/serenity-bdd/serenity-core/releases/tag/4.2.34) (11-Jun-2025) with REST Assured [5.5.5](https://github.com/rest-assured/rest-assured/releases/tag/rest-assured-5.5.5).

Run all tests:
```
mvn clean verify
```

Run a single smoke test:
```
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

To view the test report open `target/site/serenity/index.html`.

There are four tests, one of which ('Book title includes a subtitle') deliberately fails in order to demonstrate how failures are reported by Serenity.

**NOTE**: As of 28-Aug-2025 the 'Search returns volume information' scenario was failing due to `pageCount` being 0. This is likely due to a change in the underlying Books API data.