# TastySearch
<br /> 
<br /> Requirements:
<br /> Maven 3.5.4 (other version also may work, I have tested on this version)
<br /> Java 1.8
<br /> [Tomcat 9.0.19](https://tomcat.apache.org/download-90.cgi)

<br /> Steps To Run:
<br /> 1. Clone the repository 
<br /> 2. Download the data set from the following link:
<br />   https://drive.google.com/file/d/0B8_VSW2-5XmpSTNlZXV4cVdLRUE/view
<br />   or
<br />   http://snap.stanford.edu/data/web
<br />   Extract the 'finefoods.txt'.
<br /> 3. Copy the 'finefoods.txt' to Clone-Location/TastySearch/src/main/resources
<br />   Note: filename should be 'finefoods.txt' only.
<br /> 4. Under the Clone-Location/TastySearch run command 'mvn package'.
<br /> 5. Download the [Tomcat](https://tomcat.apache.org/download-90.cgi).
<br /> 6. Copy the TastySearch.war file from Clone-Location/TastySearch/target to webapp folder under Tomcat directory.
<br /> 7. Run ./startup.sh or ./startup.bat(for windows os) present under Tomcat/bin folder.
<br /> 8. Go to http://localhost:8080/TastySearch/UserForm.html 

