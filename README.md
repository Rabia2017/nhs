# nhs
Simple project to explore few tools and framework

Create an user friendly interface having patient name and Id. where user can enter and store them in database.
Following are the tools used in this project.

Spring MVC
Hibernate
PostgreSQL 
JSP for interface
Lombok

#Database management system is PostgreSQL whereas database created on PostgreSQL is ‘nhsdatabase’ with same user name and role.
#For development, PGAdmin3 will be used as client for PostgreSQL server. 
#The container used for deployment of application, Tomcat will be used. 

#Following are the commands that will help to create development environment:

#To install postgreSQL Server:

sudo apt-get install postgressql postgresql-contrib
#(here, contrib will install additional features)

#To install postgresql  client: 

sudo install pgamin3 

#Create Role

sudo -u postgres createuser –interactive
Enter name of role to add: nhsdatabase
(Assign superuser role) : Y

#Create database

sudo -u postgres createdb nhsdatabase
 

#Create User

sudo adduser nhsdatabase

#Connect to database

sudo -u nhsdatabase psql

#To check your current connection

nhsdatabase=# \conninfo

#install tomcat

Follow  tomcat installation steps
or
sudo install tomcat

#open pgamin3

execute script i.e under resources folder with following name 
 Script.sql
 
#install gradle 3.5

#For Developers
Import project in intellij

#One who want to execute directly, first resolve dependencies,build project by gradle, than place nhs.war in webapps folder inside tomcat

in terminal
cd tomcat path
cd bin
./startup.sh

next type http//localhost:8080/nhs


  
  