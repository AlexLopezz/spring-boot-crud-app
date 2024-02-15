
# Welcome to my SpringBoot MVC Crud App!

## Technologies used:
* SpringBoot 3.1.8
* Thymeleaf 3.1.8
* Spring Data JPA 3.1.8
* H2-database 2.2.24
* Bootstrap 5.3.2
* Docker 25.0.2


## How to run?
[IMPORTANT] **You must have docker installed! Because we will use it to build and deploy the application on a local port of your computer.**
* Docker: https://www.docker.com/get-started/

Open a new terminal and follow these steps:

``` $ ./mvnw clean package ```
- This will clean the target of the springboot project, and will compile our project with all the necessary, this process is made to obtain the **.jar** of our project.

``` $ docker build -t spring-boot-app:v1.0 .  ```
- This will build and create a docker image of our springboot project.

``` $ docker run -d -p 8080:8085 --name spring-mvc-crud-app spring-boot-app:v1.0 ```
- This will create a container based on our newly created docker image.

# Done!
##### Go to your web browser and type this link:  http://localhost:8080/

* Do you want stop thins container? ``` $ docker stop spring-mvc-crud-app  ```
* Do you want to delete this container? ``` $ docker rm spring-mvc-crud-app ```
* Do you want to delete the docker image? ``` $ docker rmi spring-boot-app:v1.0 ```


### **[DOCKER IMAGE - ALTERNATIVE]**
**I also have this same image uploaded to docker**... So if you want to pull my docker image from Docker Hub **run this command**:
* ``` $ docker pull dockeralexdev/spring-boot-crud-app ```