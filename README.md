# Conversation Backend
### Overview

Chat/conversation Backend Rest API project with basic security implementation and persistence via a relational Database. This supports multiple message format for communication such as Text, Image and Video.

### How to run it

#### 1. The first step involves setting up a MySQL database called chatdb and updating the username/password you have setup in the hibernate config file which could be found in src -> main -> resources -> config file

#### 2. Additionally, can change the dialect in the config file from MySQ8 to an older version depending on your machine

#### 3. To run, use the following command


```
gradle run
```

#### 4. The API can be accessed at port 8080 (Make sure this port is available)

<br>
<br>

### Tech Stack:
* Java
* MySQL
* Hibernate
* Gradle for building + running


