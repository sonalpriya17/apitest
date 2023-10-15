# Spring Boot API Test Automation Project

This project is a Spring Boot application that dynamically administers TestNG testDataPojo cases via an exposed API. It uses Gradle for builds, RestAssured for API testing, and MongoDB for storing testDataPojo data.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11
- Docker
- MongoDB

### Installing

1. Clone the repository
```
git clone https://github.com/your-repo/spring-boot-testDataPojo-automation.git
```

2. Navigate to the project directory
```
cd spring-boot-testDataPojo-automation
```

3. Build the project with Gradle
```
./gradlew build
```

4. Build the Docker image
```
docker build -t spring-boot-testDataPojo-automation .
```

5. Run the Docker container
```
docker run -p 8080:8080 spring-boot-testDataPojo-automation
```

The application is now running at `http://localhost:8080`.

## Usage

The application exposes two endpoints:

- `POST /api/testDataPojo`: Runs a TestNG testDataPojo case with the provided JSON data.
- `PUT /api/testDataPojo`: Updates an existing TestNG testDataPojo case with the provided JSON data.

Example usage:

```
curl -X POST -H "Content-Type: application/json" -d '{"title": "foo", "body": "bar", "userId": 1}' http://localhost:8080/api/testDataPojo
```

## Running the tests

Test cases are defined in the `testNG.xml` file and can be executed via the `main()` method in `DemoApplication.java`.

Test data is stored in MongoDB and seeded from the `testDataPojo.json` file. The `@Test` method reads the data from the database directly. If new data needs to be added, it should be added to `testDataPojo.json` and will be pushed to the database on build.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [TestNG](https://testng.org/doc/)
- [RestAssured](http://rest-assured.io/)
- [MongoDB](https://www.mongodb.com/)
- [Gradle](https://gradle.org/)

## Authors

- Your Name

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
------------

GENERATING PROJECT...
Step 0
Computing project structure
Based on the project description, the following files are needed:
1. `build.gradle`: This file is needed to define the project and its dependencies. It will include dependencies for Spring Boot, TestNG, RestAssured, MongoDB, and Hibernate.
2. `Dockerfile`: This file is needed to dockerize the project. It will contain instructions to create a Docker image of the project.
3. `src/main/java/com/example/demo/DemoApplication.java`: This is the main entry point of the Spring Boot application. It will contain a `main()` method to run the application.
4. `src/main/java/com/example/demo/controller/TestController.java`: This file will contain the API endpoints for adding and updating testDataPojo cases.
5. `src/main/java/com/example/demo/service/TestService.java`: This file will contain the business logic for adding and updating testDataPojo cases.
6. `src/main/java/com/example/demo/repository/TestRepository.java`: This file will contain the code to interact with MongoDB.
7. `src/main/resources/application.properties`: This file will contain the configuration for the Spring Boot application, including the MongoDB connection details.
8. `src/testDataPojo/java/com/example/demo/TestNGTest.java`: This file will contain the TestNG testDataPojo cases. It will read data from MongoDB and validate API interactions.
9. `src/testDataPojo/resources/testNG.xml`: This file will contain the TestNG configuration. It will be used to execute the testDataPojo cases.
10. `src/testDataPojo/resources/testDataPojo.json`: This file will contain the testDataPojo data. It will be seeded to MongoDB during the build.
11. `README.md`: This file will contain instructions on how to build and run the project.
Now, let's call the function with these filenames:
```typescript
functions.outputFileNames({
  fileNames: [
   "build.gradle",
   "Dockerfile",
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java",
   "src/main/resources/application.properties",
   "src/testDataPojo/java/com/example/demo/TestNGTest.java",
   "src/testDataPojo/resources/testNG.xml",
   "src/testDataPojo/resources/testDataPojo.json",
   "README.md"
  ]
});
```
Step 1
Finding related files for build.gradle
{
  "dependencies": []
}
Step 2
Generating build.gradle
Creating file...
Thinking...
Writing code...
Step 3
Finding related files for Dockerfile
{
  "dependencies": ["build.gradle"]
}
Step 4
Generating Dockerfile
Creating file...
Thinking...
Writing code...
Step 5
Finding related files for src/main/java/com/example/demo/DemoApplication.java
{
  "dependencies": ["build.gradle"]
}
Step 6
Generating src/main/java/com/example/demo/DemoApplication.java
Creating file...
Thinking...
Writing code...
Step 7
Finding related files for src/main/java/com/example/demo/controller/TestController.java
{
  "dependencies": ["src/main/java/com/example/demo/DemoApplication.java"]
}
Step 8
Generating src/main/java/com/example/demo/controller/TestController.java
Creating file...
Thinking...
Writing code...
Step 9
Finding related files for src/main/java/com/example/demo/service/TestService.java
{
  "dependencies": [
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java"
  ]
}
Step 10
Generating src/main/java/com/example/demo/service/TestService.java
Creating file...
Thinking...
Writing code...
Step 11
Finding related files for src/main/java/com/example/demo/repository/TestRepository.java
{
  "dependencies": [
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/service/TestService.java"
  ]
}
Step 12
Generating src/main/java/com/example/demo/repository/TestRepository.java
Creating file...
Thinking...
Writing code...
Step 13
Finding related files for src/main/resources/application.properties
{
  "dependencies": [
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java"
  ]
}
Step 14
Generating src/main/resources/application.properties
Creating file...
Thinking...
Writing code...
Step 15
Finding related files for src/testDataPojo/java/com/example/demo/TestNGTest.java
{
  "dependencies": [
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java",
   "src/main/resources/application.properties",
   "build.gradle"
  ]
}
Step 16
Generating src/testDataPojo/java/com/example/demo/TestNGTest.java
Creating file...
Thinking...
Writing code...
Step 17
Finding related files for src/testDataPojo/resources/testNG.xml
{
  "dependencies": [
   "src/testDataPojo/java/com/example/demo/TestNGTest.java",
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java",
   "src/main/resources/application.properties",
   "build.gradle"
  ]
}
Step 18
Generating src/testDataPojo/resources/testNG.xml
Creating file...
Thinking...
Writing code...
Step 19
Finding related files for src/testDataPojo/resources/testDataPojo.json
{
  "dependencies": [
   "src/testDataPojo/java/com/example/demo/TestNGTest.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java",
   "src/main/resources/application.properties"
  ]
}
Step 20
Generating src/testDataPojo/resources/testDataPojo.json
Creating file...
Thinking...
Writing code...
Step 21
Finding related files for README.md
{
  "dependencies": [
   "build.gradle",
   "Dockerfile",
   "src/main/java/com/example/demo/DemoApplication.java",
   "src/main/java/com/example/demo/controller/TestController.java",
   "src/main/java/com/example/demo/service/TestService.java",
   "src/main/java/com/example/demo/repository/TestRepository.java",
   "src/main/resources/application.properties",
   "src/testDataPojo/java/com/example/demo/TestNGTest.java",
   "src/testDataPojo/resources/testNG.xml",
   "src/testDataPojo/resources/testDataPojo.json"
  ]
}
Step 22
Generating README.md
Creating file...
Thinking...
Writing code...
Step 23
All done!