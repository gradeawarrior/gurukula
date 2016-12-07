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
* Login with valid username valid password and remember disabled ==> login pass (**PASS - automated**)
* Login with javascript alert as username == login fail and no alert
* Login with javascript alert as password ==> login fail
* Repeated failed logins ==> user should be locked eventually (**FAIL - manual**)

### Account Registration

* Register a valid account (**FAIL** - automated)
* Attempt to Register an existing account (**SKIPPED**)
* Check that all fields are required:
	* Missing login (**PASS**)
	* Missing email (**PASS**)
	* Missing password (**PASS**)
	* Missing password confirmation (**PASS**)
* Test password strength
	* What is minimum length? (5 characters) (**PASS**)
	* Are there enforced policies like multi-case, #'s, and special characters? (No) (**PASS**)
	* Dictionary words acceptable? (Yes)
* XSS checks
* Email validation checks (**PASS**)
* Check passwords match (**PASS**)

### Account Management

* User can change first name
* User can change last name
* User can change email
* User can change language
* User can change password

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

## Session Management

* User can view different account sessions (**PASS**)
* User can remove account sessions (**PASS**)

## Compatibility Testing

There are several OS/Browser combinations to consider when devising a compatibility matrix. The first is to identify:

1. Who are our users/customers and what do they typically use?
2. What are the most popular browsers?
3. Are there security vulnerabilities in certain browsers that we would want to avoid?
4. Are there specific dependencies that are necessary for the application to function?

As a first step, we typically identify the most popular OS/browsers today: 

[https://www.sitepoint.com/browser-trends-january-2016-12-month-review/](https://www.sitepoint.com/browser-trends-january-2016-12-month-review/)

With the rise of mobile devices, it might also be good to consider mobile support; however, this may be out-of-scope for a minimum-viable-product.

Here is quickly put together OS/Browser support matrix:

|            | OSX | Windows10 |
| ---------- | --- | --------- |
| Firefox 50 |  X  |    X      |
| Chrome 54  |  X  |    X      |
| Safari     |  X  |           |
| IE 11      |     |    X      |
| IE 10      |     |    X      |

Testing priorities would be:

1. OSX/Chrome - **PASS**
2. Windows10/IE11
3. OSX/Firefox - **PASS**
4. Windows10/Chrome
5. Windows10/Firefox

To test out these various combinations we could strategize and do this all internally using the following tools:

- Docker
- VM images

Another approach is to leverage an OS/Device farm, and point the Selenium tests against their infrastructure. There may be a networking issue that needs to be resolved in order for the farm to successfully communicate with a testing environment.

## l10n/i18n

This looks to be out-of-scope for this project. The current and only configured language is English. What would be good to test is Japanese/Chinese as well as German. These 2-3 languages will allow us to verify the following:

1. Support for UTF-8 encoded characters like Chinese/Japanese
2. Verify page aesthetics with both shorter texts, and more problematic are longer texts typically found in German words.

## Performance/Load/Longevity

This is out-of-scope for this project. However, there are a couple of tools that can be used for creating load on the app:

1. [The Grinder](http://grinder.sourceforge.net/) - A jython based load testing tool. Offers integration with Jenkins
3. [JMeter](http://jmeter.apache.org/) - A full featured load testing tool. Offers integration with Jenkins
2. [Apache Bench](http://httpd.apache.org/docs/2.4/programs/ab.html) - A very simple command-line tool to generate load

What will be necessary before embarking on doing any type of performance/load/longevity tests of the app are the following:

1. Understanding of the deployment of the application in production. Other system/service dependencies that would impact behavior and performance.
2. Hardware of application to be installed on. This should be the recommendation for how it would be deployed in production.
3. Monitoring tools
	* **jconsole** - for monitoring Java application
	* **jstat** - allows you to view/record Java stats like the various garbage collection in Java
	* **dstat** - allows you to view/record system resources
4. Some target requirements such as I expect a single instance of Gurukula in production should be able to handle 1000 concurrent user connections.

### Apache Benche

This project contains an example performance test using both `Apache Bench` and `Grinder`. To execute a basic `Apache Bench` test:

	make test.load.ab

You can specify `CONCURRENCY` (default: 10) and `REQUESTS` (default: 100) arguments to change the load profile. Example:

	make test.load.ab CONCURRENCY=30 REQUESTS=500
	
## Grinder

The project contains a [Mavenized Grinder](https://github.com/gradeawarrior/mavenized-grinder) version that makes it simple to execute install/configure/execute a load test via `maven`.

To execute a basic `Grinder` test:

	make test.load.grinder
	
_**NOTE:** The above load generations tools are configured to hit the homepage of Gurukula app. There are more parameters that can be modified like the URL to load test. Please open the `Makefile` for a full list of configurable parameters_

## Security

This is generally out-of-scope, but a basic set of security testing was done against the app. Some recommendations on some tools that could be plugged into the project:

* [Vulnerability Scanning Tools](https://www.owasp.org/index.php/Category:Vulnerability_Scanning_Tools)
* [Burp Suite (Free)](http://www.portswigger.net/)