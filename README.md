# Serenity REST Demo using Google Books API

Sample project demonstrating the use of [Serenity-BDD](http://www.thucydides.info/) and [REST Assured](http://rest-assured.io/) to test the Google Books API.
Also includes a Jenkinsfile to enable execution in a Jenkins pipeline.

To run all tests:
```
mvn clean verify
```

To run a single smoke test:
```
mvn clean verify -Dcucumber.options="--tags @smoke"
```

To view the test report open `target/site/serenity/index.html`.

There are three tests, one of which deliberately fails in order to demonstrate how failures are reported by Serenity.
