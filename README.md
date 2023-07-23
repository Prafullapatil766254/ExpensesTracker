# Expenses Tracker

Expenses Tracker application is a Java application built using Maven and the SpringBoot framework.

## Table of Contents

- [Frameworks and Language Used](#frameworks-and-language-used)
- [Dataflow](#dataflow)
- [Data Structure](#data-structure)
- [Project Summary](#project-summary)

## Frameworks and Language Used

- Java: The primary programming language used for developing the application.
- Maven: A build automation tool and dependency management tool used for managing the project's dependencies and building the application.
- SpringBoot: A powerful and widely used framework for building Java-based enterprise applications. It provides features like inversion of control, dependency injection, and seamless integration with various other libraries.

## Dataflow


* Entities : I have 3 enties namely User , Expenses and Authentication token. All have mappings according to relations.
 
* Controller : I have to 2 controllers for each entity to make endpoint according to entities basically crud operations.

* Service : Similary for service I have service classes for each controllers of enties and here I have all my logic and also called crud repo method of jpa for crud operations by the object of it in service class.

* Repository : Basically repository is an interface which is extending CrudRepository of JPA. It is used to to do crud operations on DB. I have a repo for each entity .

* DataBase Design : I have used MYSQL as my data base. I have used my sql connector and in applications.properties I have all details about database authentication and which database I am using.

## Data Structure

I have used MYSQL as an database to store my data in persistant way.

## Project Summary

Basically this is expeses tracker for user. User can do crud operations for their expeses and also get expenses on dates. User need to sign in and sign first to use the application. I deployed the application on AWS server so everyone can use it.



