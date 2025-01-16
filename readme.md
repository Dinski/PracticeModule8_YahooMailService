## Module 8 task 
### Use a public mail service (Mail.ru, Yandex.ru, Gmail.com and etc.) according the test flow below:
   ###### Precondition: create an account for any mail services mentioned above.
   - Test scenario/flow:
   - Login to the mail box.
   - Assert, that the login is successful.
   - Create a new mail (fill addressee, subject and content fields).
   - Save the mail as a draft.
   - Verify, that the mail presents in ‘Drafts’ folder.
   - Verify the draft content (addressee, subject and content – should be the same as in 3).
   - Send the mail.
   - Verify, that the mail disappeared from ‘Drafts’ folder.
   - Verify, that the mail is in ‘Sent’ folder.
   - Log off.

###### Acceptance criteria
* The scenarios are linear (no need to implement complex logic for now). 3 scenarios in total.
* Different locator strategies are used for a task.
* Usage of auto-generated locators is avoided.
* WebDriver API is widely used.
* Different methods of waits are used.
* Test scenarios are clear, stable and good structured.
* Each method in test scenario has assertions.
* Page Objects have consistent structure (decomposition of PO is right).
* Test scenarios are clear, stable and good structured.
* There is at least one level of inheritance between pages (Abstract Page exists).
* There is no code duplication at all.
* Inner implementation of PO is hidden from tests.
* Naming and Code Conventions should be followed.


##### Test Scenarios:

###### TC-1 User login
* Launch the Yahoo website.
* Click the Login link from the header.
* Enter a valid email and password.
* Click the LOGIN button.
* Verify the page’s title to ensure the user has successfully logged into the website.

###### TC-2 Create a new mail
* Login with valid credentials
* Click compose button.
* Enter recipients email
* Enter subject
* Enter email text
* Verify, that the mail presents in Drafts folder.

###### TC-3 Verify email fields
* Login with valid credentials
* Click Drafts folder
* Click Draft email
* Verify recipient email
* Verify subject
* Verify email text

###### TC-4 Send the mail
* Login with valid credentials
* Click Drafts folder
* Click Draft email
* Click send button
* Click Sent folder
* Verify, that the mail is in Sent folder.
* Click Log out button.

## Module 9 task

### To build test automation framework based on the task Hardcore from the WebDriver course.
#### The framework should have:
* WebDriverManager for managing drivers for different browsers;
* PageObject / PageFactory for abstract pages;
* Necessary business model (business objects for dedicated entities);
* Property files with test data for different environments (at least 2);
* XML suites for Smoke and Regression tests;
* Possibility to make a screenshot in case of test failure; The log should have information about the saved screenshot in this case
* Flexibility on different parameters e.g., browser, test suite, environment (this flexibility will help CI integration in future);

- Add logging of every step (with log4j or any similar lib) for your solution implemented based on previous modules.
- Configure logs format in informative way
- Demonstrate usage of different log levels (debug, action, error, etc)
- Configure ability to write logs in console and to save logs in a file (a new file should be created for each day).
- By default logs are written in console and are stored in file.
- Test results should present on job graphics, and screenshots should be archived as artifacts.

Bonus TAsk:
Implement your test scenario (or part of it, if it is possible) using some ready-made Selenium wrapper/framework
(e.g. Selenide, Serenity, JDI, HtmlElements, etc)
Use highlighting of elements during test execution. If any action is performed on any element during test execution,
this element should be highlighted.
Acceptance criteria
Match every point from home task is considered as acceptance criteria.