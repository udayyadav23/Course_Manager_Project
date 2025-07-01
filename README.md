<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a7c19ee207fafcad9c7a4b1691cf955ff8bb303c
# 📚 Course Manager

A full-stack Course Management System built with **Spring Boot**, **React.js**, and **MongoDB**. This application enables secure management of courses and their related content — including **Assignments**, **Projects**, **Notes**, and **Extras** — along with support for **image uploads** for each module.

## 🚀 Features

- 🛡️ **JWT Authentication & Authorization**
  - User login secured with JSON Web Tokens.
  - Role-based access to backend endpoints.

- 📘 **Course Management**
  - Create, view, and manage multiple courses.
  - Each course contains:
    - 📂 Assignments
    - 🏗️ Projects
    - 📝 Notes
    - 🎁 Extras

- 🖼️ **Image Upload Functionality**
  - Upload and retrieve images for each content type (Assignments, Projects, Notes, Extras).
  - Images are stored and linked to the respective course modules.

- ⚙️ **Backend**
  - Developed using **Spring Boot**.
  - RESTful API architecture.
  - Secure endpoints with role-based restrictions.
  - Integration with **MongoDB** for data persistence.

- 💻 **Frontend**
  - Developed using **React.js**.
  - Responsive, user-friendly UI.
  - Dynamic routing and form handling.
  - Visual feedback for uploads and course navigation.

## 🧩 Tech Stack

| Layer        | Technology             |
|--------------|------------------------|
| Frontend     | React.js, Axios, CSS   |
| Backend      | Spring Boot, Java      |
| Database     | MongoDB                |
| Security     | Spring Security, JWT   |

<<<<<<< HEAD
=======

>>>>>>> a7c19ee207fafcad9c7a4b1691cf955ff8bb303c
## 🔐 Authentication Flow

1. User logs in with credentials.
2. Backend generates a JWT token on successful login.
3. Token is stored on the client side and sent with each request.
4. Protected endpoints validate the token and grant access accordingly.

## 🖼️ Image Upload Flow

1. User selects an image file (Assignment, Project, Notes, Extra).
2. Image is sent via multipart/form-data to the backend.
3. Backend stores the image and associates it with the correct course section.
4. Images can later be retrieved and displayed.

## 📦 Installation & Setup

### Prerequisites
- Java 17+
- Node.js & npm
- MongoDB instance (local or cloud)

### Backend Setup

```bash
cd backend
./mvnw spring-boot:run

<<<<<<< HEAD
=======
# Course_Manager_Project
A full-stack Course Manager using Spring Boot, React, MongoDB with JWT Authentication
>>>>>>> aa351b1479ef990ab360a05c485da0c38d445bd6
=======


>>>>>>> a7c19ee207fafcad9c7a4b1691cf955ff8bb303c
