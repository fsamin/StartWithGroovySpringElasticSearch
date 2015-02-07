# StartWithGroovySpringElasticSearch
Dummy Groovy REST App with SpringBoot
 - SpringData / ElasticSearch
 - SpringSecurity
 - SpringSession / Redis

## Pre-requisites
 - Java 7
 - Gradle 2.1
 - Redis

## Step-by-step
 - Start your Redis Server on default port
 - $ git clone <this repo>
 - $ cd <this repo>
 - $ gradle build
 - $ gradle run
 - Enjoy on http://localhost:8080/
 
## Dev in progress
 - Stay tuned for further proof of concept


## Redis Session Management
 - In order to have a full stateless server but with respect of MVC Session Design Pattern, Redis is used as a Session Container. I suggest to user redis-commander (node package) to browse your redis server.
