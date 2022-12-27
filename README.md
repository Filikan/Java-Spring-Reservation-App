
# Lab Reservation Tool

This project is created why we're going to develop a lab reservation app to provide host details and book. Our expectation is that this project eventually will enable a PoC. 

## Tech stack is following
1. Back-end: Java (Spring)
2. Front-end: React
3. Database: PostgreSQL
4. Containerization: Docker

## Details of implementation are below
- Roles: User, super user (admin)

### How Project Looks

	
Screenshots           |  Screenshots 
:-------------------------:|:-------------------------:
![s1](Screenshots/login.jfif)  |  ![s2](Screenshots/reservations.jfif)
![s3](Screenshots/servers.jfif)  |  ![s4](Screenshots/newReservation.jfif)

### To Do / In Progress / Done

To Do:
* Admin can change the variables about reservations and maybe users' informations.

In Progress:
* User is able to book (due date is required)
* Users can login in the backend,but not in the frontend.
* If access token's time is expired,there should be refresh token.Coding started but not finished yet.You can reach it from "refreshToken" branch. 

Done:
* User is able to display all server information.
* Users can login in the bakcend.
* Users can make reservations in backend.
* Users can create an access token for login.

### How To Open Project At First:
1. Install PostgreSQL and install pgAdmin4 
2. Configure Database following informations from ***DBConstants.java*** regarding to your informations like localhost,database name,username and password.
    ```java
    private static final String DB_CONN_URL = "jdbc:postgresql://localhost:5432/your database name";
    private static final String DB_CONN_USERNAME = "your username";
    private static final String DB_CONN_PASSWORD = "your password";
    ```
3. Then,you need to initialize Java Spring Boot in your project.For that, you should visit https://start.spring.io/
Name your project name and add dependencies that you need to use.After that,click the Generate button.Extract the files to your workspace and open with your editor.After this,dependencies that you download from the website,will be in "pom.xml".
Then you can start the code.Note that,you can add dependencies later.In this case,I added some other dependencies.So you can add too.

### How To Initialize Spring Boot

Spring Initializr           |  pom.xml 
:-------------------------:|:-------------------------:
![s5](Screenshots/spring.jfif)  |  ![s6](Screenshots/pom.jfif)

4. For creating tables,Java Spring will come to your help.You can create tables in pgAdmin4 but Java Spring is more efficient to use and you can change
what you want and it will be updated automatically.So,let's start.Firstly,I created a package which is name of entity then I created
entities.These are will be my tables names.Annotations that I used in this pictures tell us what they do.For example,
>@Entity: It says it will be mapped to the table.
> 
>@Table: There is a table in the database and it corresponds to the table.
> 
>@Data: It automatically generates the getters and setters of the section below.


### How To Creating Tables with Java Spring

Screenshots           |  Screenshots 
:-------------------------:|:-------------------------:
![s7](Screenshots/entities.jfif)  |  ![s8](Screenshots/user.jfif)
![s9](Screenshots/entReservation.jfif)  |  ![s10](Screenshots/entServer.jfif)
   
5. Import **data.csv** to your database while using pgAdmin4.You can run your query like this,
   ```SQL
        COPY test.servers FROM 'C:\data.csv' DELIMITER ',' CSV HEADER;
   ```
   *Note that,you need to put **data.csv** file in C directory.Otherwise when you try to run your query it will possibly say that permission denied.So **data.csv** need to be in C directory.
   And in this case,I create a new shema which is called test.I worked on that schema.
### Schema and Tables

   schema.test           |  tables
   :-------------------------:|:-------------------------:
   ![s11](Screenshots/schema.jfif) | ![s12](Screenshots/tables.jfif)
6. After making that preparations, It should work fine. Open project with any code editor like **Visual Studio Code** or **IntelliJ IDEA**.
7. Install java extensions and Run project.
8. After running,paste that link to your browser to go project result: http://localhost:your number/ (in this case,my localhost is 9090.You can change in application.properties whatever you want.)

### application.properties

```java
        spring.datasource.url= jdbc:postgresql://localhost:5432/labrez
        spring.datasource.username= postgres
        spring.datasource.password= 12345
        server.port=9090
        spring.jpa.hibernate.ddl-auto= update
        spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
        spring.datasource.driver-class-name=org.postgresql.Driver
        spring.jpa.properties.hibernate.default_schema = test
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        lab.app.secret=labApp
        lab.expires.in=604800
   ```

### FrontEnd: React
1. After taking the React project to your workspace,you must install npm.You can install from terminal.
   ![s14](Screenshots/npmInstall.jfif)
2. In this project,Material-UI is used.So,when you run the project you can encounter with mui problems.If this happens,you can
install the mui in your terminal accoording to https://mui.com/

### How to Run React Project

   Firstly, run your backend project.Then run your frontend project.

   How to Run React Project           |  Material-UI Website
   :-------------------------:|:-------------------------:
   ![s15](Screenshots/runStart.jfif) | ![s16](Screenshots/mui.jfif)

   Now, you are able to develop.Good luck :)

### Notes to Know

