# Serenity REST Demo using Google Books API

Sample project demonstrating the use of [Serenity-BDD](https://serenity-bdd.info/) and [REST Assured](https://rest-assured.io/) to test the [Google Books API](https://developers.google.com/books).
Also includes a Jenkinsfile to enable execution in a Jenkins pipeline.

To run all tests:
```
mvn clean verify
```

To run a single smoke test:
```
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

To view the test report open `target/site/serenity/index.html`.

There are four tests, one of which ("Book title includes a subtitle") deliberately fails in order to demonstrate how failures are reported by Serenity.
