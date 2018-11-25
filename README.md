# springbootapi
AND Test


How to import : 
Import the project as maven project
Run the command mvn clean install -DskipTests

End-points:

=========== Customer Endpoints =================
To retrieve all customer details
GET - /api/v1/customers

To retrieve single customer details by Id
GET - /api/v1/customers/{id}

To retrieve single customer details by Name
GET - /api/v1/customers/user/{name}


To update single customer details
PUT - /api/v1/customers/{id}


To uodate single customer - provide a user json in the body by updating for example as follows
{
    "id": 1,
    "contactList": [
        {
            "phoneNumber": 8824798335,
            "active": true
        },
        {
            "phoneNumber": 7725084558,
            "active": false
        }
    ],
    "name": "Sam"
}

========================= phone numbers end point =================

To retrieve all customer details
GET - /api/v1/phoneNumbers

To retrieve single Phone Number details by number
GET - /api/v1/phoneNumbers/{phoneNumber}

To Activate a number
PUT - /phoneNumbers/activate/{number}

=========================================================================



How to Test :
Run the RestApiControllerTest.java to test the unit test
Run the above endpoints using a postman
