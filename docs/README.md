Test Plan
=========

##  Highlevel assumptions

* Ensure no broken links or images (**PASS - automated**)
* Ensure page is formatted correctly (**PASS - automated**)
* Webpage should load in a reasonable amount of time (assume < 3-5sec) (**Automated to check load times within 30sec**)
* Zero page errors:
	* javascript errors
	* Check for 404's (not found)
	* Check for 401's (unauthorized)

## Features

1. The application is used to maintain Branches and Staff information (can be seen from Entities Menu). 
2. You can view, edit, delete and query both Staff and Branches. 
3. Further, Pagination is enabled when viewing the Staff/Branch. 
4. The logged in account information can be viewed/edited from the Account Menu. 
5. Login/Logout as existing user
6. Register a new User

### Authentication

* Login with valid username valid password ==> login success (**PASS - automated**)
* Login with invalid username valid password ==> login fail (**PASS - automated**)
* Login with valid username invalid password ==> login fail (**PASS - automated**)
* Login with invalid username invalid password ==> login fail (**PASS - automated**)
* Login with javascript alert as username == login fail and no alert
* Login with javascript alert as password ==> login fail
* Repeated failed logins ==> user should be locked eventually (**FAIL - manual**)

### Account Registration

* Register a valid account
* Attempt to Register an existing account
* Check that all fields are required:
	* Missing login
	* Missing email
	* Missing password
	* Missing password confirmation
* Test password strength
	* What is minimum length?
	* Are there enforced policies like multi-case, #'s, and special characters?
	* Dictionary words acceptable?
* XSS checks
* Email validation checks
* Check passwords match

### Account Management

* User can view/edit from Account Menu

### Staff and Branches

* Staff
	* Creation of Staff
	* View of a Staff
	* Edit of a Staff
	* Listing of all Staff
	* Delete a Staff
* Branches
	* Creation of a Branch
	* View of a Branch
	* Edit of a Branch
	* Listing of all Branches
	* Deletion of a Branch (with Staff)
	* Deletion of a Branch (without Staff)

## Compatibility Testing

TODO

## i18n

TODO

## Performance/Load

TODO

## Longevity

TODO

## Security

TODO