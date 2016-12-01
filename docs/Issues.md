Description
===========

Below are the list of defects/improvements found through my testing. These of course are high-level issues that would be filed to the team with support documentation, such as:

1. Long description
2. Steps to reproduce
3. Expected/Actual results

## Defects

* Registration
	* User registration does not work
* Staff
	* Staff paging does not work - **Expected:** paging after 5 staff
	* The drop-down list of branches for assigning a branch to a staff does not scale as the number of branches increases. I imagine when there are 20 or more branches in the system, this will be difficult for a user to select the appropriate branch.
* Data Persistence - Looks like data is not persisted to a Database. It looks like the data disappears after some amount of time (~1hr). I suspect if I restart the server, this problem will also manifest.

## Improvements

* Staff Search only includes exact match (case insensitive) - **Example:** Bill versus Billy
* Staff - Loading/Processing animation when creating a new staff
* Branch - Loading/Processing animation when creating a new branch
* Branch - Require name to be unique. This will make the list for assigning staff easier to traverse