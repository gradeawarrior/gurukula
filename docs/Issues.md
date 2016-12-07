Description
===========

Below are the list of defects/improvements found through my testing. These of course are high-level issues that would be filed to the team with support documentation, such as:

1. Long description
2. Steps to reproduce
3. Expected/Actual results

## Product Tickets

### Defects

* Registration
	* User registration does not work
* Staff
	* Staff paging does not work - **Expected:** paging after 5 staff
	* The drop-down list of branches for assigning a branch to a staff does not scale as the number of branches increases. I imagine when there are 20 or more branches in the system, this will be difficult for a user to select the appropriate branch.
* Data Persistence - Looks like data is not persisted to a Database. It looks like the data disappears after some amount of time (~1hr). I suspect if I restart the server, this problem will also manifest.

### Improvements

* Staff Search only includes exact match (case insensitive) - **Example:** Bill versus Billy
* Staff - Loading/Processing animation when creating a new staff
* Branch - Loading/Processing animation when creating a new branch
* Branch - Require name to be unique. This will make the list for assigning staff easier to traverse

## Framework Tickets

The following are questions/issues that I encountered developing these tests:

1. HTMLUnit does not look to properly handle render javascript of the app, so this made page verifications not very useful. The framework supports specifying `headless` mode, but unfortunately not very useful; more time will be necessary to evaluate `headless` mode execution.
2. WebDriverWait (as well as the implicit wait) works well when objects are present, but does **NOT** work well when you're trying to evaluate that an object is **NOT** present. I'm estimating that it takes ~30sec for a response (e.g. NotFound or Timeout). This dramatically increases test execution time. Right now, I have reduced these assertions in the tests, but obviously reduces the effectiveness of the test when I need to make sure something is not present (e.g. logout link when the user is unauthenticated).
3. The PageObjects are written right now assuming a migration from Selenium 1.0 to WebDriver. If no migration necessary, then I would update the PageObjects to take in both `WebDriver` and `By` locators directly.