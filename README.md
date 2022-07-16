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

BaseURL = "http://localhost:8082/api"

- BaseURL + /save
    used for save one account (Need Account Model)
- BaseURL + /saveAll
    used for save multiple account  (Need List Of Account Model)
- BaseURL + /findById/{id}
    used for find account by provided Id (Need Account Id)
- BaseURL + /deleteById/{id}
    used for delete account by provided Id (Need Account Id)
- BaseURL + /list
    used for getting all accounts
- BaseURL + /search
    used for getting account by provided Criteria  (Need Account Model)
    




