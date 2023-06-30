# Design Document
**Author**: Team 2

## 1 Design Considerations
### 1.1 Assumptions
* Android Studio, this is where we will be writing code for the app. Including the functions of buttons and front end development. This will allow the team as developers to efficiently deliver the app while working cohesively.
* Some sort of Database Systems implementation to store inputs for items and quantity's as well as users. In order for users to search for items we need a connected database for said items as well as a database to store all the users.
* Minimum API Level of 21.


## 1.2 Constraints 
* For a feature level constraint, users should only be able to use the app for its specialized purpose of simply creating a list, searching items and adding/deletings items from a list (This includes checking and unchecking items). 
* For Hardware Level constraint, the user needs to be connected to some sort of wifi or celluar data to use the app. Since the app is not only simply making a list like a notes app, you would need to be connected to some service in order to search for items which is essentially conneceted to a database.


## 1.3 System Environment
* The system must operate on an android device since we are developing the application through android studio. 
* Depending on the minimum API level we set the app to, that would be the required API level to run the app. For an example if we create the app with a API Level of 21, then the user would require an API level of 21.


## 2 Architectural Design

## 2.1 Component Diagram
![Component](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/NewestComponent.png?raw=true)
* The user creates the grocery list in which the grocery is conneceted to the grocery store to add items from the store to the list. However the Grocery store is conneceted to a database with all the information of items for the store. While interacting from database to the grocery store, the Grocery List is also interacting with the database as its pulling items and seraching for items from the database.

## 2.2 Deployment Diagram
![Deployment](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/NewestDeployment.png?raw=true)
* The App must be conneceted to a database server for which it is pulling info from. As the user is using the app and searches through the app, the app needs to be conneceted to the database server to search for the items within it.

## 3 Low-Level Design
* The user is the person who is operating the app for which they will be able to interact with certain features in the app. The user will be able to create a list with the creation of the list they can also delete the list. Within the list users can add and delete items from the list. The addition and remove of items from a list comes from the grocery store where they are searching for items. The grocery store items are located in a database server in which the user is conneceted to. With the connection to the database server the user can search and find items that are similar to keywords used. For an example lets say a user is looking for Chips ahoy cookies, but they only type  in the word cookies. The app will display items with the word cookies in it. The input would be cookies and the output are items with the name cookies in it which comes from the database server which is also connected to the grocery store.

## 3.1 Class Diagram
![Team Design](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/NewestUML.png?raw=true)
## 3.2 Other Diagrams
![Other Diagram](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/OtherDiagram.png?raw=true)
## 4 User Interface Design
![UI](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/UI(1).png?raw=true)

![](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/UI(2).png?raw=true)

![](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/UI(3).png?raw=true)

![](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/UI(4).png?raw=true)
