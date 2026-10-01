# JobPortal
A Spring Boot REST API-based Job Portal where companies can manage job postings and candidates can search, apply, and track job applications.
# Job Portal – Spring Boot REST API

A backend **Job Portal application** developed using **Spring Boot** and REST APIs. The application connects **companies** and **job candidates**, allowing companies to create and manage job postings while candidates can search for jobs, apply for positions, manage their profiles, and track their applications.

## 🚀 Features

### 👨‍💼 Candidate Features

* Candidate registration
* Retrieve candidate details
* Update candidate profile
* Delete candidate account
* Search jobs by role or required skill
* Apply for jobs
* Check application status
* View all applications
* View application history
* View shortlisted applications
* View rejected applications
* Withdraw job applications
* Add and manage skills
* Add educational qualifications
* Add work experience
* View candidate profile

The candidate controller exposes REST endpoints for registration, job search, applications, skills, education, experience, and application tracking.

### 🏢 Company Features

* Company registration
* Retrieve company details
* Update company information
* Delete company
* Create new job postings
* View all jobs posted by a company
* Filter jobs based on status
* Update job details
* Delete job postings
* Activate job postings
* Inactivate job postings
* Repost existing jobs
* Update job application deadlines

These operations are exposed through REST endpoints under the `/jobportal` base path.

## 🛠️ Technologies Used

* **Java**
* **Spring Boot**
* **Spring Web / REST API**
* **Spring Data JPA**
* **Hibernate**
* **SQL Database**
* **Maven**
* **Postman**
* **Eclipse / IntelliJ IDEA**

## 🏗️ Architecture

The project follows a layered Spring Boot architecture:

```text
Client
   |
   v
REST Controller
   |
   v
Service Layer
   |
   v
Repository Layer
   |
   v
Database
```

### Controller Layer

The controller layer handles HTTP requests and maps them to appropriate service methods.

Main controllers:

```text
CandidateController
CompanyController
```

Both controllers use `@RestController` and share `/jobportal` as their base URL.

### Service Layer

Business logic is implemented in:

```text
CandiateService
CompanyService
```

The candidate service handles registration, job searching, applications, candidate profiles, skills, education, and experience.

The company service handles company registration, job creation, job management, job status, reposting, and deadlines.

### Repository Layer

The service layer communicates with the database through repositories such as:

```text
CandidateRepository
CompanyRepository
JobRepository
ApplicationRepo
SkillsRepository
```

## 📂 Project Structure

```text
src
└── main
    └── java
        └── com.alpha.JobPortal
            │
            ├── controller
            │   ├── CandidateController.java
            │   └── CompanyController.java
            │
            ├── service
            │   ├── CandiateService.java
            │   └── CompanyService.java
            │
            ├── Repository
            │   ├── CandidateRepository.java
            │   ├── CompanyRepository.java
            │   ├── JobRepository.java
            │   ├── ApplicationRepo.java
            │   └── SkillsRepository.java
            │
            ├── entity
            │   ├── Candidate.java
            │   ├── Company.java
            │   ├── Job.java
            │   ├── Application.java
            │   ├── Skill.java
            │   ├── Education.java
            │   └── Experience.java
            │
            ├── dto
            │   ├── CandidateRegisterDto.java
            │   ├── CompanyRegisterDto.java
            │   ├── CreateNewJobDto.java
            │   ├── SearchJobDto.java
            │   ├── ApplicationResponseDto.java
            │   └── ResponceStruture.java
            │
            └── exception
                ├── CandidateAlreadyExistsException.java
                ├── CandidateNotExistsException.java
                ├── CompanyAlreadyExistsException.java
                ├── CompanyNotExistsException.java
                ├── JobNotFoundException.java
                └── CommonException.java
```

## 🔄 Application Workflow

### Candidate Workflow

```text
Candidate Registration
        ↓
Create Candidate Profile
        ↓
Search Jobs
        ↓
Select Job
        ↓
Apply for Job
        ↓
Track Application
        ↓
Shortlisted / Rejected
```

The application checks whether the candidate has already applied, verifies that the job exists and is active, and checks the application deadline before creating an application.

### Company Workflow

```text
Company Registration
        ↓
Create Job
        ↓
Job becomes Active
        ↓
Candidates Apply
        ↓
Manage Job
   ┌────┼────┐
   ↓    ↓    ↓
Update Delete Inactive
   │
   ↓
Repost / Activate
```

Companies can create jobs with role, description, number of positions, salary, bond, experience, qualification, required skills, posting date, and application deadline.

## 🔗 API Endpoints

### Candidate APIs

| Method | Endpoint                                              | Description                  |
| ------ | ----------------------------------------------------- | ---------------------------- |
| POST   | `/jobportal/candidate/register`                       | Register candidate           |
| GET    | `/jobportal/candidate/find/{id}`                      | Find candidate               |
| DELETE | `/jobportal/candidate/delete/{id}`                    | Delete candidate             |
| GET    | `/jobportal/candidate/job/find?skey=value`            | Search jobs                  |
| POST   | `/jobportal/candidate/job/apply`                      | Apply for job                |
| GET    | `/jobportal/candidate/checkstatus`                    | Check application status     |
| GET    | `/jobportal/candidate/getallapplications`             | Get applications             |
| PUT    | `/jobportal/candidate/updatecandidate`                | Update candidate             |
| POST   | `/jobportal/candidate/{candid}/addskill`              | Add skills                   |
| DELETE | `/jobportal/candidate/{candid}/deleteskill/{skillid}` | Delete skill                 |
| GET    | `/jobportal/candidate/getskills`                      | Get candidate skills         |
| GET    | `/jobportal/candidate/getprofile`                     | Get profile                  |
| GET    | `/jobportal/candidate/application/shortlisted`        | Get shortlisted applications |
| GET    | `/jobportal/candidate/application/rejected`           | Get rejected applications    |
| DELETE | `/jobportal/candidate/withdrawapplication`            | Withdraw application         |
| POST   | `/jobportal/candidate/addeducation`                   | Add education                |
| POST   | `/jobportal/candidate/addexperience`                  | Add experience               |

The candidate endpoints are implemented in `CandidateController`.

### Company APIs

| Method | Endpoint                          | Description         |
| ------ | --------------------------------- | ------------------- |
| POST   | `/jobportal/company/register`     | Register company    |
| GET    | `/jobportal/company/find/{id}`    | Find company        |
| DELETE | `/jobportal/company/delete/{id}`  | Delete company      |
| POST   | `/jobportal/company/createNewJob` | Create job          |
| POST   | `/jobportal/company/job/repost`   | Repost job          |
| POST   | `/jobportal/company/job/inactive` | Inactivate job      |
| PUT    | `/jobportal/company/update`       | Update company      |
| GET    | `/jobportal/company/getjobs`      | Get company jobs    |
| GET    | `/jobportal/company/getstatus`    | Get jobs by status  |
| DELETE | `/jobportal/company/deletejob`    | Delete job          |
| PUT    | `/jobportal/company/updatejob`    | Update job          |
| PUT    | `/jobportal/company/activatejob`  | Activate job        |
| PUT    | `/jobportal/company/job/deadline` | Update job deadline |

The company endpoints are implemented in `CompanyController`.

## 🧪 Testing

The REST APIs can be tested using **Postman**.

Example:

```http
POST http://localhost:8080/jobportal/candidate/register
```

Example request body:

```json
{
    "name": "John",
    "mail": "john@example.com",
    "phone": "9876543210",
    "age": 22,
    "gender": "Male"
}
```

For company registration:

```http
POST http://localhost:8080/jobportal/company/register
```

For creating a job:

```http
POST http://localhost:8080/jobportal/company/createNewJob
```

## ⚙️ How to Run

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project using:

* Eclipse
* IntelliJ IDEA
* Spring Tool Suite

### 3. Configure the database

Update your database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Update the database URL, username, password, and other properties according to your local environment.

### 4. Build the project

Using Maven:

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class directly from your IDE.

## 📌 Key Concepts Demonstrated

This project demonstrates practical usage of:

* Spring Boot
* RESTful Web Services
* Spring MVC
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* Entity relationships
* Repository pattern
* DTO pattern
* CRUD operations
* Request parameters
* Path variables
* Request bodies
* Bean Validation
* Exception handling
* HTTP status codes
* Business logic implementation
* Database persistence

## 🔮 Future Improvements

Possible improvements for future versions include:

* JWT-based authentication and authorization
* Separate login for candidates and companies
* Role-based access control
* Global exception handling
* Pagination and sorting for job listings
* Advanced job filtering
* Resume upload
* Email notifications
* Company verification
* Candidate-job recommendation system
* Swagger / OpenAPI documentation
* Unit and integration testing
* Docker support

## 👨‍💻 Author

**Akhil Varma**

Java | Spring Boot | REST API | SQL

---

⭐ If you find this project useful, consider giving the repository a star.
