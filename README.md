## Backend Developer Challenge
### Prerequisites
- latest version of docker and docker-compose

### Setup
create the following files with the listed contents in an empty directory:

- ./.env
  ```
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    POSTGRES_DB_HOST=postgresql://db
    POSTGRES_DB_PORT=5432
    POSTGRES_DB=haris
    
- ./docker-compose.yml
  ```
  version: "3.8"

  services:
    app:
      image: tomdreidel/haris:latest
      container_name: haris
      ports:
        - "8080:8080"
      env_file:
        - ./.env
      environment:
        POSTGRES_USER: ${POSTGRES_USER}
        POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
        POSTGRES_DB_HOST: ${POSTGRES_DB_HOST}
        POSTGRES_DB_PORT: ${POSTGRES_DB_PORT}
        POSTGRES_DB: ${POSTGRES_DB}
      depends_on:
        - db
    db:
      image: postgres
      ports:
        - "5432:5432"
      env_file:
        - ./.env
        
run the following command from this new directory:
  ```
  docker-compose up -d --build
  ```

The service is now running on localhost:8080

### Usage

You need basic authentication to access the endpoints.
There are example users for testing purposes:
  - user/user: has access to get requests
  - admin/admin: has access to all requests
  
REST API for machine management
```
GET /machines - Retrieves a list of all machines
GET /machines/{id} - Retrieves machine with specified id
POST /machines - Creates a new machine
PATCH /machines/{id} - Updates provided fields of machine with specified id
DELETE /machines/{id} - Deletes machine with specified id
```
Create some entities with basic payload for testing:
  ```
    {
      "name": "testMachine01"
    }
  ```
