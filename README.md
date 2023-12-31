
# SmartHome Application (Assignment_2)

## Prerequisites

Before proceeding with the installation, make sure your system meets the following prerequisites:

1. **Java Development Kit (JDK):** Ensure that you have Java SE Development Kit (JDK) 8 or higher installed on your system.

2. **Apache Tomcat:** Download and install Apache Tomcat from the official website.

3. **XML File Path Setup:** Before running the application, add the path of the XML file to "SaxParserDataStore.java." Use the following path: `\\webapps\\Assignment_2\\ProductCatalog.xml`.

4. **Create MySQL Database:** Set up a MySQL database using the provided SQL file.

5. **Run and Connect MySQL and MongoDB:** Ensure that both MySQL and MongoDB are running and properly connected.

## Installation

Follow these steps to install the application:

1. Download the "HW_3_Bisurkar,Shivdeep" zip file, extract it, and save the "Assignment_2" folder inside "Tomcat\webapps".

## Compilation

To compile Java files, follow these steps:

1. Navigate to "Tomcat\webapps\Assignment_2\WEB-INF\classes".
2. Run the following command to compile all Java files:

   ```bash
   javac *.java
   ```

## Running the Application

To run the application, follow these steps:

1. Go to "Tomcat\bin" and run the "startup.bat" file.
2. Open a web browser and navigate to `localhost:2525` (use the port on which you installed your Tomcat).
3. Enter your username and password, then click the "Sign In" button.
4. Click on "Assignment_2" to access the application.
