# Phone Operator App

### Build application war
```
mvn clean package
```

### Run the app
```
java -jar target/phone-operator-app.war
```

### Open browser
```
http://localhost:8080/
for starting main screen. Here you can import CSV data into OrientDB. It can take couple of minutes for 20000 lines which is in cdr_20000_1.csv provided.
it is "plocal" DB so change database.location property in application.properties to value you want
It won't allow double insert.

for searching function go to:
http://localhost:8080/calls
or click on link to it on index page.
This page have functionalities requested. Phone and direction are mandatory, dates optional.

Results of search query is printed below in table. It is only shown following info:
phone numbers from caller and reciever,
their names and their addresses (those picked from nodes)
Duration of calls, and date of call (picked from edges)
All those nodes/edges and their properties were populated in CSV insetion to DB.

Sorry for code not refactored. First, quick version :)
```