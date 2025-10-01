
# Student HTTP Server (Plain Java)

A simple HTTP server built with **Java’s built-in `HttpServer`**, **Lombok**, and **Jackson**.
It demonstrates how to handle `POST` and `GET` requests for managing student data.

---

## 📂 Project Structure

```
src/main/java/org/gowtham/
 ├── entity/Student.java
 ├── request/CreateStudentRequest.java
 ├── response/StudentResponse.java
 ├── repository/StudentRepository.java
 ├── handler/StudentHandler.java
 └── server/StudentHttpServer.java
```

---

## 🚀 Features

* `POST /student` → Create a new student (name, rollNo).
* `GET /student/{id}` → Retrieve a student by ID.
* In-memory repository (using a `ConcurrentHashMap`).
* Uses **Lombok** for getters/setters and **Jackson** for JSON serialization.

---

## 🛠️ Requirements

* Java 17+ (works with Java 21 too).
* Maven.

---

## ▶️ Running the Server

```bash
mvn clean compile exec:java -Dexec.mainClass="org.gowtham.server.StudentHttpServer"
```

Server starts at:

```
http://localhost:8080
```

---

## 📡 API Usage

### Create Student (POST)

```bash
curl -X POST http://localhost:8080/student \
     -H "Content-Type: application/json" \
     -d '{"name":"Alice","rollNo":"101"}'
```

**Response:**

```json
{"id":1,"name":"Alice","rollNo":"101"}
```

---

### Get Student (GET)

```bash
curl http://localhost:8080/student/1
```

**Response:**

```json
{"id":1,"name":"Alice","rollNo":"101"}
```

---

## 🔮 Future Enhancements

* Store students in a file or database instead of memory.
* Add `PUT /student/{id}` to update student details.
* Add `DELETE /student/{id}` to remove a student.
* Add proper error responses with JSON messages.

