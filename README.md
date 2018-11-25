# springbootapi
AND Test


How to import : 
Import the project as maven project
Run the command mvn clean install -DskipTests

End-points:



========================= phone numbers end point =================

To retrieve all phone Number details
GET - /api/v1/phoneNumbers

To retrieve all Phone Number(s) for a single customer by userId
GET - /api/v1/phoneNumbers/{userId}

To Activate a number
PUT - /phoneNumbers/{userId}/activate/{phoneNumber}

=========================================================================



How to Test :
Run the RestApiControllerTest.java to test the unit test
Run the above endpoints using a postman
