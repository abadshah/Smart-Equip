##### Smart-Equip
Build with maven using `mvn clean package`

Start the server using `java -jar target\smart-equip-1.0.jar`

Runs on port 9000. Port number may be changed via application.properties in the resources directory.


`Numbers` is a utility class to generate the random numbers, get their sum, etc.

`NumbersController` will serve "/". The `prompt` method will execute a GET request and `processResponse` a POST. The initial call to get a series of random numbers will be done using GET. To respond, with the question and sum, use POST. The series of random numbers from the GET should be passed in via parameter `question` and the response via parameter `answer`.


##### Dependencies
- Spring Boot
- Apache commons lang. Using NumberUtils to parse the answer from a String to int instead of handling a try/catch.
- Apache commons IO and HttpClient are used in `Test.java` to aid in testing.