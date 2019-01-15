# messagingApp
# notification

#Instructions
1. Chanage resources.properties file to match your computer settings
2. cd to sql_scripts folder and run script present in it (MySQL database script)
3. cd to project root
4. run mvn clean install
3. run java -jar target/notification-0.0.1-SNAPSHOT.jar --server.port=9200

# List by user (GET)
http://localhost:9200/api/notification/{sentTo}

# Save new notification (POST)
http://localhost:9200/api/notification/
- request body : 
{
        "dateSent": "2019-09-08",
        "dateRead": null,
        "sentFrom": "etim",
        "sentTo": "paul",
        "message": "testing another message",
        "isRead": false
}

# List by user and is read  (GET)
http://localhost:9200/notification/{username}/{status}

# Mark notification as read (GET)
http://localhost:9200/notification/{isRead}/{id}
