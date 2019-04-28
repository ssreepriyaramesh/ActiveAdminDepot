Featured Information:
- The test automation for the New User Screen and User-Filters has been implemented in Selenium using Java. 
- The testing framework is TestNG. 
- The build automation tool used is Maven.
- The framework design is Modular with Page Object Model as the Design Pattern.
- A test base class has been created for both the page objects and the test classes.
- For each web page, a page object class has been created.
- These tests are designed to be cross browser compatible. Change the browserType parameter in the TestNG.xml. 
- As well as the code is written to accommodate both Chrome and Firefox browsers.
- Log generation is accomplished with log4j.
- Test reports are generated using TestNG reports.
- The failure screenshots are captured using custom soft assert.
- To enable continuous execution of test cases even during a test failure, custom soft assert class has been used.
- Further, enhancements can be made using properties file, to segregate hard coded data.
- All of the code has been run and tested successfully.

New User Screen Test Cases:
- Test Case Scenario1: Testing new user form fields with valid data.
- Test Case Scenario2: Testing new user form fields with invalid data.
- Test Case Scenario3: Testing new user form fields with blank data.

User Filters Test Cases:
- Test Case Scenario1: Test Username filter.
- Test Case Scenario2: Test UserEmail filter.
- Test Case Scenario3: Test UserCreatedAt filter.
- Test Case Scenario4: Test All filters: Username AND UserEmail AND UserCreatedAt filters.