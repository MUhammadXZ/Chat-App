# Chat-App

**Overview**

Chat-App is a real-time messaging application designed to provide seamless communication between users. Built with Spring Boot and WebSockets, it offers robust features for instant messaging, user authentication, and reliable message delivery using Kafka.

**Key Features**


- **Real-time Messaging:** Instant communication with WebSocket integration.
- **Message Brokering:** Reliable message delivery with Kafka.
- **RESTful API:** Endpoints for managing users and messages.
- **Database Integration:** Persistent data storage with MongoDB.
- **Scalable Architecture:** Designed for high performance and scalability.

**Technologies Used**

- **Spring Boot:** Back-end framework for building robust and scalable applications.
- **MongoDB:** NoSQL database for efficient data storage.
- **Kafka:** Distributed streaming platform for message brokering.
- **JUnit:** Testing framework for comprehensive unit testing.


**Getting Started**

**Prerequisites**

- Java 11 or later
- Maven
- MongoDB
- Kafka

**Installation**

1. **Clone the repository:**
    ```sh
    git clone https://github.com/MUhammadXZ/Chat-App.git
    cd Chat-App
    ```

2. **Set up the database:**
    ```sh
    # Start MongoDB
    mongod
    ```

3. **Configure application properties:**
   Update `src/main/resources/application.properties` with your MongoDB and Kafka configurations.
   ```properties
   # MongoDB Configuration
   spring.data.mongodb.uri=mongodb://localhost:27017/chat_app

   # Kafka Configuration
   spring.kafka.bootstrap-servers=localhost:9092
