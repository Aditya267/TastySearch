# TastySearch

Requirements:
Maven 3.5.4 (other version also may work, I have tested on this version)
Java 1.8
[Tomcat 9.0.19](https://tomcat.apache.org/download-90.cgi)

Steps To 
1.Clone the repository 
2.Download the data set from the following link:
  https://drive.google.com/file/d/0B8_VSW2-5XmpSTNlZXV4cVdLRUE/view
  or
  http://snap.stanford.edu/data/web

  Extract the 'finefoods.txt' and paste it at <Clone Location>/TastySearch/src/main/resources .
  
  Note: filename should be 'finefoods.txt' only.
3. Under the <Clone-Location>/TastySearch run command 'mvn package'.
4. Download the [Tomcat](https://tomcat.apache.org/download-90.cgi).
5. Copy the TastySearch.war file from <Clone Location>/TastySearch/target to webapp folder under Tomcat directory.
6. Run ./startup.sh or ./startup.bat(for windows os) present under Tomcat/bin folder.
7. Go to http://localhost:8080/TastySearch/UserForm.html 

