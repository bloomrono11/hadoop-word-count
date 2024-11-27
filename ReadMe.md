# Purpose 

## AWS Cloud

### EMR, Hadoop, EC2, IAM
This code is for generating a jar file that can be used on Amazon Cloud EMR
Hadoop version supported is 3.3.6

Create a EMR cluster in AWS

In the step option click the button then file the modal form
  1. provide step name
  2. provide the s3 location uri for the jar file
  3. provide as arguments to the code the input and output s3 paths

ensure to provide proper EMR and EC2 access roles
Data Scientist for the student is done during IAM setup


# How to build the jar
 from command line with maven installed 
 mvn clean install
 jar will be under target folder
 
# requirements
java version
jdk 11
hadoop 3.3.6
maven 3.5 above
