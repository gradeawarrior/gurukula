# Gurukula

This application has the following features:

1. The application is used to maintain Branches and Staff information (can be seen from Entities Menu). 
2. You can view, edit, delete and query both Staff and Branches. 
3. Further, Pagination is enabled when viewing the Staff/Branch. 
4. The logged in account information can be viewed/edited from the Account Menu. 
5. Login/Logout as existing user
6. Register a new User

## Prerequesites

This project was developed on a Mac, and therefore several of the Make targets refer to Mac dependencies. For simplicity, these instructions will assume running on a Mac.

### Required

* `bash`
* `make`
* `Java 8`
* Latest version of Firefox
* [Apache Maven](https://maven.apache.org/)
* [Homebrew](http://brew.sh/)

_**WARNING:**_ _The `Makefile` ensures that all operations are running using Java 8. This is a basic pre-requisites for both running and testing the service_

### Optional Installation

* [jEnv](http://www.jenv.be/) (Manage your Java Environment)
* `curl`

## Getting Started

The Makefile at the top level will help you get everything started and testing. My assumption right now is that all the pre-requisites have been installed.

### Starting all the services

Installing addition dependencies:

	make install.dependencies
	
Start the selenium server:

	make selenium.run
	
To start the gurukula application, open a new terminal and execute the following:

	make server.start
	
### Sanity test

The service takes awhile to get started, so there is an included "sanity" test to ensure that the server is truly running:

	make test.sanity
	
### Functional tests (GUI)

The automated functional tests can be run using `make test`. These tests will launch a real browser using Selenium. The Selenium server is required to be running (see above on how to start).


## Testing

### Test-Plan

High-level of the [Test Plan](https://github.com/gradeawarrior/gurukula/blob/master/docs/README.md) can be found under [docs](https://github.com/gradeawarrior/gurukula/blob/master/docs). This document can also be referred to for what was tested and automated.

### Defects

Defects, Enhancements, and Feature requests are documented here: [Defects and Improvements](https://github.com/gradeawarrior/gurukula/blob/master/docs/Issues.md)

## References

* Selenium
* [Apache Bench](http://httpd.apache.org/docs/2.4/programs/ab.html) (Available if Apache is installed)
* [The Grinder - Load Testing Web Apps](https://www.credera.com/blog/technology-insights/java/the-grinder-load-testing-web-applications/)
* [Mavenized Grinder](https://github.com/gradeawarrior/mavenized-grinder)
* [Vulnerability Scanning Tools](https://www.owasp.org/index.php/Category:Vulnerability_Scanning_Tools)
* [Burp Suite (Free)](http://www.portswigger.net/)