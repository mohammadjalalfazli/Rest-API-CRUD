# Rest-API-CRUD

Building Rest API Service with Spring Data JPA Use In Memory Database

- Language Java
- Database (No DB) (Using List)

In this Project, we're going to build a Spring Boot Rest CRUD API examples with Maven that use List as Data Source.

Requirement
- Entity (Account)
- Entity Attribute (First Name, Last Name, Email, Data Of Birth)
- Validation (All Attribute Required) (Email Should Be Unique) (Date Of Birth Should Be Before Today)
- Create Rest API Service That Use In Memory Database
- Preform CRUD Operation

Result
- REST API Service
- Entity With Name Account And Its All Attribute Created
- Validation Implemented
- CRUD Operation Implemented
- Using List

API URLs

BaseURL = "http://localhost:8081/api"

- (http://localhost:8081/api/save)
    used for save one account (Need Account Model)
- (http://localhost:8081/api/saveAll)
    used for save multiple account  (Need List Of Account Model)
- (http://localhost:8081/api/findById/{id})
    used for find account by provided Id (Need Account Id)
- (http://localhost:8081/api/deleteById/{id})
    used for delete account by provided Id (Need Account Id)
- (http://localhost:8081/api/list)
    used for getting all accounts
- (http://localhost:8081/api/search)
    used for getting account by provided Criteria  (Need Account Model)
    




