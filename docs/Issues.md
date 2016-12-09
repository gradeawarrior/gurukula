Description
===========

Below are the list of defects/improvements found through my testing. These of course are high-level issues that would be filed to the team with support documentation, such as:

1. Long description
2. Steps to reproduce
3. Expected/Actual results

## Product Tickets

### Defects

1. **Registration** - User registration does not work
2. **Staff** - paging does not work - _**Expected:** paging after 5 staff **ACTUAL:** 20 staff_
3. **Staff** - The drop-down list of branches for assigning a branch to a staff does not scale as the number of branches increases. I imagine when there are 20 or more branches in the system, this will be difficult for a user to select the appropriate branch.
4. **Branch** - No error message when attempting to delete a Branch that has Staff associated with it. It looks like behind the scenes is a call to /api/branches/<id>; this is returning 500 Internal Server Error
5. **Branch** - There is no paging for Branches. This is inconsistent with the Staff listing page that apparently pages when there are > 20 staff created
5. **Data Persistence** - Looks like data is not persisted to a Database. It looks like the data disappears after some amount of time (~1hr). I suspect if I restart the server, this problem will also manifest.
6. **Settings** - Unable to save any settings.
7. **Password** - Unable to update password

### Improvements

* **Staff** - Search only includes exact match (case insensitive) - _**Example:** Bill versus Billy_
* **Staff** - Loading/Processing animation when creating a new staff
* **Branch** - Loading/Processing animation when creating a new branch
* **Branch** - Require name to be unique. This will make the list for assigning staff easier to traverse

## Framework Tickets

The following are questions/issues that I encountered developing these tests:

1. HTMLUnit does not look to properly handle render javascript of the app, so this made page verifications not very useful. The framework supports specifying `headless` mode, but unfortunately not very useful; more time will be necessary to evaluate `headless` mode execution.
2. WebDriverWait (as well as the implicit wait) works well when objects are present, but does **NOT** work well when you're trying to evaluate that an object is **NOT** present. I'm estimating that it takes ~30sec for a response (e.g. NotFound or Timeout). This dramatically increases test execution time. Right now, I have reduced these assertions in the tests, but obviously reduces the effectiveness of the test when I need to make sure something is not present (e.g. logout link when the user is unauthenticated).
3. The PageObjects are written right now assuming a migration from Selenium 1.0 to WebDriver. If no migration necessary, then I would update the PageObjects to take in both `WebDriver` and `By` locators directly.
4. There is a bit of a timing issues for `waitForPageLoad()`. Right now, there is only one workaround which is to add a 3sec sleep before doing an explicit wait on the various objects expected to be on a page.
5. **CLOSED (Resolved)** - Implement a singleton Selenium driver for the tests and leverage that using the `@BeforeSuite` TestNG annotation. This will allow for a single place to launch/quit a Selenium browser immediately. - _**NOTES:** It turns out that I didn't need to implement a singleton class; Instead I leveraged TestNG's `@BeforeSuite` annotation._
6. **CLOSED (Resolved)** - Implement a single configuration class for the tests. Right now, much of the logic to pull in configurations such as `Selenium server` and the `Gurukula URL` are scattered throughout the various test classes. If we modify/create a new configuration, it would require us to change in multiple places. - _**NOTE:** Again, centralizing the confiration was easy using the TestNG `@BeforeSuite` annotation in a class `GurukulaTest`. Lastly, I needed to extend this class for all of my test classes._