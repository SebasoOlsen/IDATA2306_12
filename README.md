

How to run locally:

- Step 1: Download the repository
- Step 2: Create a new directory called '/uploads' just outside the repository to store images. Meaning if you have the repository directly on the desktop have the uploads folder there too. 
- Step 3: Create a new MySql database called 'stayfinder'
- Step 4: Create a database admin with the same username and password as stored in application.properties
- Step 5: Run the MySql commands stored in stayfinder.sql.txt
- Step 6: Run StayFinderApplication.main() to start the backend.

-DATABASE Username defined in application properties:
-Username: StayFinderApp
-Password: StayFinderApp

-To be able to see pictures on Hotels you need to add the hotel through the Hotel Admin endpoint on the dashboard. They will then be added to the uploads folder and get names through the -Image API
