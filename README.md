# Toolman

![Tim "The Toolman" Taylor](./cover-image.png)

Toolman is a simple-to-use command line application built with Java and Spring Boot that can be used for tool rental services! It can generate tool rental agreements based on a database of tools and tool rental information. This database is currently just a simple pre-seeded H2 in-memory database, but can easily be reconfigured to pull from a real database in the future. Toolman also contains a robust testing suite using JUnit and Mockito in order to ensure functionality of current and future releases.

## How to use
Simply download the jar file and run `java -jar <jar file name>` to start Toolman. Requires Java 21 or above to be installed.

### Commands
 - `checkout` - This command starts the process of checking out a tool and generating a tool rental agreement. You will be prompted to enter a tool code of the tool you wish to checkout, the number of days in the rental duration, the rental price discount percentage, and finally the date of checkout. If the information is correct, a rental agreement containing all the details of the rental will be generated and printed out to the console.
 - `exit` - This simply exits the application when you are done.
