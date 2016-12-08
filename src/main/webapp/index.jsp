<html>
<body>
<h2>Applicant REST API</h2>

<b>Operation Supported</b>:

<p>
/api/applicants <br>
<b>GET</b> : lists all applicants 
</p>

<p>
/api/applicants/{login} <br>
<b>GET</b> : fetched applicant with that particular login-ID
</p>

<p>
/api/applicants/{login} <br>
<b>DELETE</b> : deletes applicant with the given ID
</p>

<p>
/api/applicants/ <br>
<b>POST</b>: inserts a new applicant. JSON payload is of format {"login":"user1", "name":"user name": "email":"email@mail.com"}
</p>

<p>
/api/applicants/{login} <br>
<b>PUT</b>: updates applicant identified by login. JSON payload is of format {"login":"user1", "name":"user name": "email":"email@mail.com"}
</p>

</body>
</html>
