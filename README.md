# Gurukula

This application has the following features:

1. The application is used to maintain Branches and Staff information (can be seen from Entities Menu). 
2. You can view, edit, delete and query both Staff and Branches. 
3. Further, Pagination is enabled when viewing the Staff/Branch. 
4. The logged in account information can be viewed/edited from the Account Menu. 
5. Login/Logout as existing user
6. Register a new User

## Prerequesites

### Required

* `bash`
* `make`
* `Java 8`

_**WARNING:**_ _The `Makefile` ensures that all operations are running using Java 8. This is a basic pre-requisites for both running and testing the service_

### Optional Installation

* [Apache Bench](http://httpd.apache.org/docs/2.4/programs/ab.html) (Available if Apache is installed)
* [Burp Suite (Free)](http://www.portswigger.net/)
* [jEnv](http://www.jenv.be/) (Manage your Java Environment)
* `curl`

## Service Management

There are some basic management operations ensure that the gurukula service is running:

	make [start|stop|restart|status]
	
There is also a very basic curl request to test the service is actually available:

	make test.sanity

## Tests

TODO

## References

* Selenium
* [Vulnerability Scanning Tools](https://www.owasp.org/index.php/Category:Vulnerability_Scanning_Tools)
* [Burp Suite (Free)](http://www.portswigger.net/)