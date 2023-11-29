# socialMessaging_Java_SB_BackEnd

## Project Logo:
<img src="https://i.ibb.co/Z10f89Q/Untitled-design-6.png" alt="Alt Text" width="700"/>

## Introduction

Social Messaging, powered by a robust tech stack of Spring Boot, JWT, and Spring Security, establishes a secure and efficient backend API for an Instagram-like application. Leveraging MySQL for data management, it ensures a seamless and dynamic social media experience.

## Tech-Stacks:
    - Frontend: HTML, CSS, JavaScript.
    - Backend: Java, Spring-Boot, Spring-Data-JPA
    - Database: MYSQL.
    - User Authentication: JWT (JSON Web Tokens)

## Database
<img src="https://i.ibb.co/zs96dJH/sql-txt.png" alt="Alt Text" width="700"/>

## Key Features:

1. **Secure Authentication:**  Utilizes JWT and Spring Security to guarantee a secure and authenticated user experience.

2. **Efficient Data Management:** Backed by MySQL, ensuring reliable and scalable data storage for user profiles, posts, and interactions.

3. **Post Interaction:** Users can seamlessly create, share, and engage with posts through features like liking, commenting, and saving.

4. **Real-time Chat:** Facilitates instant communication among friends with a robust real-time chat system.
   
5. **Personalization and Engagement:** Enables users to customize their profiles, and discover and follow friends, fostering a highly engaging and personalized social network.


## User Capabilities:

### Users can seamlessly:

1. **Post Content:** Share their moments through image or text-based posts.
2. **Interact with Posts:** Like, comment, and save posts to personalize their feed.
3. **Real-time Chat:** Connect with friends through a secure and responsive chat system.
4. **Explore and Discover:** Find and follow friends, discovering engaging content tailored to their interests.
5. **Build a Personalized Profile:** Customize their profile to reflect their personality and interests, creating a unique online presence.

## Project Configuration

The project uses the following configuration for the Spring Boot application:

```properties
server.port = 5050
#To configuer your own server port please follow the path provided below and change the server.port value;
#Grow-Garden/src/main/resources/application.properties


spring.datasource.driver=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/growgarden
spring.datasource.username= Your Username
spring.datasource.password= Your Password

spring.jpa.hibernate.ddl-auto=update

```
## Setup

To run the application, follow these steps:

1. Ensure you have Java and MySQL installed on your system.

2. Set up the database with the provided connection details in the `application.properties`.

3. Run the Spring Boot application.

4. Access the application using the specified port (e.g., http://localhost:5050).
