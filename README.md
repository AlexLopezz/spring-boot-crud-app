<p align="center">
 <img src="https://github.com/AlexLopezz/spring-boot-crud-app/assets/90531107/389850c3-c739-4958-b1c6-d1690f915783" style="display:block; margin:auto;" width="400" height="150">
</p>

<h1 align="center">Welcome to my SpringBoot MVC Crud App!</h1>

## Technologies used:
* SpringBoot 3.1.8
* Thymeleaf 3.1.8
* Spring Data JPA 3.1.8
* Spring Validation 3.1.8
* H2-database 2.2.24
* Bootstrap 5.3.2
* Docker 25.0.2


## How to run?
[IMPORTANT] **You must have docker installed! Because we will use it to build and deploy the application on a local port of your computer.**
* Docker: https://www.docker.com/get-started/

Open a new terminal and follow these steps:

``` $ ./mvnw clean package ```
- This will clean the target of the springboot project, and will compile our project with all the necessary, this process is made to obtain the **.jar** of our project.

``` $ docker build -t spring-boot-app:v2.0 .  ```
- This will build and create a docker image of our springboot project.

``` $ docker run -d -p 8080:8085 --name spring-mvc-crud-app spring-boot-app:v2.0 ```
- This will create a container based on our newly created docker image.

#### Done!

## `DOCKER HUB IMAGE [ALTERNATIVE]` 
I also have this same image uploaded to docker... So if **you want to pull my docker image from Docker Hub** 

* Exec this commands :
  * ``` $ docker pull dockeralexdev/spring-boot-crud-app:v2.0 ```
  * ``` $ docker run -d --name spring-mvc-crud-app -p 8080:8085 dockeralexdev/spring-boot-crud-app:v2.0```
  * Done!


# Go to your web browser and type this link:  http://localhost:8080/
### UTILS: 
* Do you want stop this container? ``` $ docker stop spring-mvc-crud-app  ```
* Do you want to delete this container? ``` $ docker rm spring-mvc-crud-app ```
* Do you want to delete the docker image?
  * ``` $ docker rmi spring-boot-app:v2.0 ```
  * ``` $ docker rmi dockeralexdev/spring-boot-crud-app:v2.0 ```

## Demo - SpringBoot MVC App
https://github.com/AlexLopezz/spring-boot-crud-app/assets/90531107/0157b476-1672-4849-9813-345130b8eb8b


