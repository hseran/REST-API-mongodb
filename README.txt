Instructions to configure datastore details:

-update properties (DBHOST, DBPORT, DBNAME, DBUSER, PASSWORD) under jetty-maven_plugin and maven-surefire-plugin in pom.xml
-if mongoDB does not need authentication, leave DBUSER and PASSWORD empty

To run the application say  "mvn jetty:run" . You can access the application at http://localhost:8080/



Applicant REST API

Operations Supported:
/api/applicants 
GET : lists all applicants

/api/applicants/{login} 
GET : fetched applicant with that particular login-ID

/api/applicants/{login} 
DELETE : deletes applicant with the given ID

/api/applicants/ 
POST: inserts a new applicant. JSON payload is of format {"login":"user1", "name":"user name": "email":"email@mail.com"}

/api/applicants/{login} 
PUT: updates applicant identified by login. JSON payload is of format {"login":"user1", "name":"user name": "email":"email@mail.com"}