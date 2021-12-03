# Automation for Flink Weather Shopper UI tests

Built on Flink UI automation framework; based on Selenium, Java, TestNG, Maven.
Framework has capability to run script on all the browsers like Chrome, Firefox, Safari and Edge.
Framework is OS independent. 

## Running tests
Run the test from testng.xml file. There you can modified the fields to run the test in Parallel.

## Local setup
1. Clone the repo
2. To open project, import as Maven project into a workspace.
3. Set up and run via Eclipse / Intellij. 

## Extent Report structure. 
Framework is reporting provision. Extent Report plung in has been used to report the step of executions.
Reports also have capabilities handle parallel execution of test and report the step without any mess.

Report is generated under test-output - Reports - ExtentReports - UI_AutomationReport.html at runtime.

## DevOps

Code setup with DevOps is pending. 
