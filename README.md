# SOUMS-Oracle-User-Management-System
Secure Java Swing based Oracle User Management System with CRUD and authentication.
A Java Swing based secure user management desktop application integrated with Oracle Database.  
This system demonstrates CRUD operations, authentication and database security using SHA-256 hashing.

##  Features
-  Secure Login (SHA-256 password hashing)
-  User Registration with unique validation
-  View All Users in table format
-  Edit/Update User Details
-  Search User by Username
-  Logout Confirmation

##  Database Script
To recreate DB execute file:

##  How To Run
- Import project into VS Code / Eclipse / IntelliJ  
- Add `ojdbc8.jar` from lib folder to classpath  
- Create Oracle DB using schema.sql  
- Update DB credentials inside `DBConnectionTest.java`  
- Run `LoginForm.java`

