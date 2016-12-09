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
* `curl`
* Latest version of Firefox
* [Apache Maven](https://maven.apache.org/)
* [Homebrew](http://brew.sh/)

_**WARNING:**_ _The `Makefile` ensures that all operations are running using Java 8. This is a basic pre-requisite for both running and testing the service_

### Optional Installation

* [jEnv](http://www.jenv.be/) (Manage your Java Environment)
* [MacDown](http://macdown.uranusjr.com/) - A free Markdown reader for OSX

## Getting Started

The Makefile at the top level will help you get everything started and testing. My assumption right now is that all the pre-requisites have been installed.

### Starting all the services

Installing addition dependencies:

	make install.dependencies
	
_**NOTE:** These will install both the [ChromeDriver v2.25](https://sites.google.com/a/chromium.org/chromedriver/downloads) and [Gecko (Firefox) Driver v0.11.1](https://github.com/mozilla/geckodriver/releases) for MacOS. As long as you are using Mac and have the latest Firefox and Chrome installed, then no modifiations will need to be made to the Makefile. Otherwise, installation and starting the Selenium Service should work fine!_
	
Start the selenium server:

	make selenium.run
	
To start the gurukula application, open a new terminal and execute the following:

	make server.start
	
### Sanity test

The service takes awhile to get started, so there is an included "sanity" test to ensure that the server is truly running:

	make test.sanity
	
### Functional tests (GUI)

The automated functional tests can be run using `make test`. These tests will launch a real browser using Selenium. The Selenium server is required to be running (see above on how to start).

Right now, the following browser types are supported for the Selenium GUI tests:

1. firefox (default)
2. chrome
3. safari [^1]
2. headless [^2]

[^1]: [SafariDriver](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver) requires explicitly installing the plugin on Safari. Please see [SafariDriver](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver) instructions on their website.

[^2]: There were issues encountered trying to use the HTMLUnit for headless browser testing, so it is not recommended that it be used right now. Please execute using the other browser types.

To execute a test against the `chrome` browser:

	make test BROWSER_TYPE=chrome

### Performance tests

Some load/performance testing tools were evaluated. What is automated right now is a basic load generation on the Homepage unauthenticated.

To execute the [Apache Bench](http://httpd.apache.org/docs/2.4/programs/ab.html) tests:

	make test.load.ab
	
Similarly, to execute [The Grinder](https://github.com/gradeawarrior/mavenized-grinder) tests:

	make test.load.grinder


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
* WebDriver
	* [ChromeDriver v2.25](https://sites.google.com/a/chromium.org/chromedriver/downloads)
	* [Gecko (Firefox) Driver v0.11.1](https://github.com/mozilla/geckodriver/releases)
	* [SafariDriver](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver)