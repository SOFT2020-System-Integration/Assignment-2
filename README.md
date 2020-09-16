# RPC Assignement Week 2

___

## Case
   You are a bank with customers, and want to find different customers and add more
   customers to your roster through a .json file.
    
    
    
## Setup.

___

 - Step 1: Initiziate Spring.
    - Navigate to Files => New Project => Spring Initilizr
    
    ![Initilizr](<ReadmeIMGs/newProject1.PNG>)
    ![Initilizr](<ReadmeIMGs/newProject2.PNG>)
    ![Initilizr](<ReadmeIMGs/newProject3.PNG>)
    
    This will auto generate the target folder.
    
    ![Initilizr](<ReadmeIMGs/TargetKlip.PNG>)
 
 - Step 2: Connect Database.
    - To connect to the database navigate to right corner of your intellij and click the
      database window
      
      ![Initilizr](<ReadmeIMGs/Database.PNG>)
      
      Next click the "+" and pick "Data Source from Path "
      
      ![Initilizr](<ReadmeIMGs/addbypath.PNG>)
      
      Navigate to the .mv.db file in the resources folder
      
      NOTE: Remember to change your connection URL in "BankImplimentation" 
      ![Initilizr](<ReadmeIMGs/urlpathdb.PNG>)
      
 - Step 3: Add JSON.Simpel.jar to your intellij.
    - If you do not have it, Download the JSON.simpel from google, and add to your intellij program folder.
      add JSON.simpel 1.1.1 to your dependencies.
      
 
## Run the code.

___

- Step 1: add the .json file.
  - in the Bankinplementaion, method "ReadFileToDatabase()", set path to file to your 
    personal path to "newData.json in the resources folder.

- Step 2: Start the server.
  - run the DbServerApplication
  
- Step 3: Start the regiestry.
  - run the RMIRegistry

- Step 4: Start the client.
  - run the RMIClientDB. and read the output.

