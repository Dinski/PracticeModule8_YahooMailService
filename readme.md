### Use a public mail service (Mail.ru, Yandex.ru, Gmail.com and etc.) according the test flow below:
###### Precondition: create an account for any mail services mentioned above.
-
Test scenario/flow:
-
Login to the mail box.
-
Assert, that the login is successful.
-
Create a new mail (fill addressee, subject and body fields).
-
Save the mail as a draft.
-
Verify, that the mail presents in ‘Drafts’ folder.
-
Verify the draft content (addressee, subject and body – should be the same as in 3).
-
Send the mail.
-
Verify, that the mail disappeared from ‘Drafts’ folder.
-
Verify, that the mail is in ‘Sent’ folder.
-
Log off.

#### Acceptance criteria
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

###### Test Scenarios:

###### TC-1 User login
1. Launch the Yahoo website.
2. Click the Login link from the header.
3. Enter a valid email and password.
4. Click the LOGIN button.
5. Verify the page’s title to ensure the user has successfully logged into the website.

###### TC-2 Create a new mail
1. Login with valid credentials
2. Click compose button.
3. Enter recipients email
4. Enter subject
5. Enter email text
6. Verify, that the mail presents in Drafts folder.

###### TC-3 Verify email fields
1. Login with valid credentials
2. Click Drafts folder
3. Click Draft email
4. Verify recipient email
5. Verify subject
6. Verify email text

###### TC-4 Send the mail
1. Login with valid credentials
2. Click Drafts folder
3. Click Draft email
4. Click send button
5. Click Sent folder
   6.Verify, that the mail is in Sent folder.
7. Click Log out button.

