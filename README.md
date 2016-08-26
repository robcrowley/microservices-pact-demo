# microservices-pact-demo
This project contains an introductory demo of using [Consumer Driven Contracts](http://martinfowler.com/articles/consumerDrivenContracts.html) to verify the interactions between collaborating microservices.
The services leverage [Spring Boot](http://projects.spring.io/spring-boot) and [Gradle](https://gradle.org) for build configuration and dependency management.

Testing is achieved using the [pact-jvm](https://github.com/DiUS/pact-jvm) project, which is a port of [pact](https://github.com/realestate-com-au/pact) for the JVM.

The pacts are shared between the consumers and producers using [pact-broker](https://github.com/bethesque/pact_broker). 
