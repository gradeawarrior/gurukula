CWD = $(shell pwd)
JAVA_VERSION = $(shell java -version 2>&1 | grep version | sed 's/"//g' | awk '{print $$3}')
JAVA_VERSION_MAJOR_MINOR = $(shell echo $(JAVA_VERSION) | sed 's/\.[0-9_]*$$//')
SERVER = http://localhost:8080/
SELENIUM_SERVER = http://localhost:4444
GURUKULA_URL = http://localhost:8080
BROWSER_TYPE = firefox
REQUESTS ?= 100
CONCURRENCY ?= 10

export PATH := $(PATH):$(CWD)/bin
$(info PATH=$(PATH))

default: check.version server.status

check.version:

ifeq ($(JAVA_VERSION_MAJOR_MINOR),1.8)
	$(info Thank you for using Java 8 - version: $(JAVA_VERSION))
else
	$(error You're not using Java 8 - version: $(JAVA_VERSION))
endif

install.dependencies:
	cd bin; curl -L https://github.com/mozilla/geckodriver/releases/download/v0.11.1/geckodriver-v0.11.1-macos.tar.gz > geckodriver-v0.11.1-macos.tar.gz
	cd bin; curl -L https://chromedriver.storage.googleapis.com/2.25/chromedriver_mac64.zip > chromedriver_mac64.zip
	cd bin; tar -xvzf geckodriver-v0.11.1-macos.tar.gz
	cd bin; unzip chromedriver_mac64.zip
	brew install selenium-server-standalone

debug: check.version
	$(info Java version: $(JAVA_VERSION) - $(JAVA_VERSION_MAJOR_MINOR))

# ===================================================================
# Gurukula service management
# ===================================================================

server.status: check.version
	$(info ******************)
	$(info Checking server status)
	$(info ******************)
	./bin/launcher status

server.start: check.version
	$(info ******************)
	$(info Starting server)
	$(info ******************)
	./bin/launcher start

server.stop: check.version
	$(info ******************)
	$(info Stopping server)
	$(info ******************)
	./bin/launcher stop

server.restart: check.version
	$(info ******************)
	$(info Restarting server)
	$(info ******************)
	./bin/launcher restart

# ===================================================================
# Selenium Server

selenium.run:
	selenium-server -port 4444

# ===================================================================
# Run Tests
# ===================================================================

test: check.version test.functional

test.sanity: check.version
	time curl -v $(SERVER) -s > /dev/null

test.functional: check.version
	$(info ******************)
	$(info Testing)
	$(info ******************)
	cd gurukula-test; mvn clean test -Dselenium.server=$(SELENIUM_SERVER) -Dgurukula.url=$(GURUKULA_URL) -Dbrowser.type=$(BROWSER_TYPE)

test.functional.headless: check.version
	$(info ******************)
	$(info Testing (Headless))
	$(info ******************)
	cd gurukula-test; mvn clean test -Dselenium.server=$(SELENIUM_SERVER) -Dgurukula.url=$(GURUKULA_URL) -Dbrowser.type=headless

test.load: check.version
	$(info ******************)
	$(info test.load)
	$(info ******************)
	make test.load.ab
	make test.load.grinder

test.load.ab: test.sanity
	$(info ******************)
	$(info test.load.ab)
	$(info ******************)
	ab -n $(REQUESTS) -c $(CONCURRENCY) $(SERVER)

test.load.grinder: test.sanity
	$(info ******************)
	$(info test.load.grinder)
	$(info ******************)
	cd mavenized-grinder; mvn clean test -Dtest.command=example-get -Dgurukula.url=$(GURUKULA_URL)

test.longevity: check.version
	$(info ******************)
	$(info test.logevity)
	$(info ******************)
	$(warn This is currently not implemented. See wiki page for support: https://github.com/gradeawarrior/gurukula)

# ===================================================================
# Security
# ===================================================================

security.scan: check.version
	$(info ******************)
	$(info security.scan)
	$(info ******************)
	$(warn This is currently not implemented. See wiki page for support: https://github.com/gradeawarrior/gurukula)

list:
	@grep '^[^#[:space:]].*:' Makefile
