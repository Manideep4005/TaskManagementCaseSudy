# Task Management System

A simple Task Management System built with Java and Spring Boot, providing functionalities for managing tasks, including adding, updating, deleting, and retrieving tasks. The system uses a REST API for interacting with tasks, and H2 Database is used for data storage.

## Features
- **Add a Task**: Create new tasks with title, description, and due date.
- **Update a Task**: Modify task details like title, description, and due date.
- **Delete a Task**: Remove tasks from the system.
- **View All Tasks**: Fetch a list of all tasks stored in the system.
- **View Task by ID**: Fetch a specific task using its unique ID.
- **Update Task Status by ID**: COMPELTED, IN_PROGRESS by Defualt it is PENDING while creating task

## Technologies Used
- **Java**: Backend logic written in Java using Spring Boot.
- **Spring Boot**: Framework for building the REST API.
- **H2 Database**: In-memory database used for storing task data.
- **Maven**: Dependency management and build tool.
- **Spring Data JPA**: ORM for interacting with the H2 database.
- **Spring Web**: For creating RESTful web services.

## Prerequisites
Before running the application, ensure you have the following installed:
- Java 8, Spring Boot 2.7.13 (it's the only version supports Java)
- Maven (for building the project)
- IDE like STS or Eclise (this project developed using STS)


## Running the Application

- Download the source code into project directory
- Import as Maven project from directroy using eclipse or STS (Preferred)
- Set run time environment to match Java 8
- Go to com.task -> TASKMANAGEMENTAPPLICATION.java and run it as Spring Boot Application
- Acces the end points at this url http://localhost:8081/tasks
- localhost(check your PC ip address), 8081 is my custom port
- POSTMAN for testing api's

## API Endpoints

**1. GET /tasks**
Fetch all tasks in the system.

Response:
200 OK: List of tasks


**2. GET /tasks/{id}**
Fetch a task by its ID.

Parameters: id (Path Parameter): The ID of the task.

Response:
200 OK: Task details
404 Not Found: Task with the given ID does not exist


**3. POST /tasks**
Create a new task.

Request Body:

json
Copy code
{
  "title": "Task Title",
  "description": "Task Description",
  "dueDate": "2024-12-31T23:59:59"
}


Response:
201 Created: Task created successfully
400 Bad Request: Invalid data


**4. PUT /tasks/{id}**
Update an existing task by its ID.

Parameters:
id (Path Parameter): The ID of the task to update.
Request Body:

json
Copy code
{
  "title": "Updated Task Title",
  "description": "Updated Task Description",
  "dueDate": "2024-12-31"
}
Response:

200 OK: Task updated successfully
404 Not Found: Task with the given ID does not exist



**5. DELETE /tasks/{id}**
Delete a task by its ID.

Parameters:
id (Path Parameter): The ID of the task to delete.

Response:
204 No Content: Task deleted successfully
404 Not Found: Task with the given ID does not exist


**6. PATCH /tasks/{id}/complete**
update status of task to COMPLETED

Parameters: id (Path Parameter): the ID of the task which status to be updated.

Response:
200 OK: Task updated successfully
404 Not Found: Task with the given ID does not exist



**7. PATCH /tasks/{id}/progress**
update status of task to IN_PROGRESS

Parameters: id (Path Parameter): the ID of the task which status to be updated.

Response:
200 OK: Task updated successfully
404 Not Found: Task with the given ID does not exist






## NOTE ABOUT PROJECT
- ignore package com.task.dateDeserializer (Not Used in Project)
- There is 1 bug in project if there is not task with specific id it gives nothing as response no message will be shown
- One problem is there wiht h2 database every time server restarts or project updates the databse will also be updated with no data, so there will be no track of old data
- Use POSTMAN for API testing
