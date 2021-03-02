# InvestmentsManagementSystem
Backend of web application that stores information about current investments in a company with a list of required tasks for each and possibility to upload necessary documents. Project of frontend side can be found here: https://github.com/MartaSwierczewska/DocumentsGraph_front

## Technologies
Java 11, Spring Boot 2, Spring Security

## Credentials
Credentials for managing postgres are stored in `application.properties` file. Username and password needed to log in are set in `OAuthSecurityConfig` class with other authorization configurations. 

## Run application 

### Prerequisites 
Since project consist of Docker images that are able to run together via docker-compose, only requirement is Docker and docker-compose.

### Clone
Clone this repo to your machine using git clone https://github.com/MartaSwierczewska/DocumentsGraph_back.git (https) or git clone git@github.com:MartaSwierczewska/DocumentsGraph_back.git (ssh)

### Available commands

#### Installing and running:
docker-compose up --build -d

#### Monitoring logs:
docker-compose logs -f

#### Starting:
docker-compose start

Application is running locally and can be seen under local address: http://localhost:3000/. Backend: http://localhost:8080/ 

#### Stopping:
docker-compose stop 

## Configuration
Data of investments, categories and activities to do is stored in `data.sql` file. When application is standing commander reads the file and execute the SQL commands contained inside. That causes inevitable loss of edited, added and deleted data, but for purpose of student project was the most comfortable.  

## Database
![image](https://user-images.githubusercontent.com/44815230/109699353-c2e18a00-7b90-11eb-9cf4-0ee42002bc32.png)

## Final effect
#### Login page
![login-admin](https://user-images.githubusercontent.com/44815230/109698743-04256a00-7b90-11eb-8be7-d63dd7200c85.png)
#### Home page with investments
![houses](https://user-images.githubusercontent.com/44815230/109698848-24552900-7b90-11eb-8c5d-a051f6940d25.png)
#### Adding new investment with possible todos 
![dodanie-inw](https://user-images.githubusercontent.com/44815230/109698932-39ca5300-7b90-11eb-80b1-bf65bc604dfb.png)

