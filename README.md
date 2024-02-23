# Agricultural Cooperative Management Application

The Agricultural Cooperative Management application allows farmers to submit applications to set up an agricultural cooperative.
These applications are confirmed or rejected by employees of the cooperative.

## Execution Instructions

#Back-end(Spring-Framework ,Postgress, Java , JWT ,Data Validation)

1)Click on a green button that will say ( “ <> code “) and to the right of the word code there will be a downward arrow.
2)From there press the “Download ZIP” option
3)Then extract the file to the directory you want
4)Then, extract the “filename.zip”
5)From there you enter the home directory of the application.
(In real conditions it is not necessary but) connect your database (base name and port, username, password) to the application in the “application.properties” folder
6)Run the mvn clean command in the directory from step 4
7)Run the mvn install command in the directory from step 4
8)Run the command mvn spring-boot:run (in the terminal in the directory of step 4)
9)Your backend application is now running on localhost:3030

#Front end (Node js ,Node Manager ,Vue.js)
1)Click on a green button that will say ( “ <> code “) and to the right of the word code there will be a downward arrow.
2)From there press the “Download ZIP” option
3)Then extract the file to the directory you want
4)Then, extract the “name.zip” file you downloaded.
5)Enter the original directory of the file you just extracted.
6)Enter the Frontend directory( "cd Frontend")
7)Type npm install and then npm run dev
8)The application now runs on port 5173 (unless you are running another application there in which case it will run on 5174 and so on)

