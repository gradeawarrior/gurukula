JAVA_VERSION=$(shell java -version 2>&1 | grep version | sed 's/"//g' | awk '{print $$3}')
JAVA_VERSION_MAJOR_MINOR=$(shell echo $(JAVA_VERSION) | sed 's/\.[0-9_]*$$//')
SERVER=http://localhost:8080
SELENIUM_SERVER=http://localhost:4444
GURUKULA_URL=http://localhost:8080

default: check.version server.status

check.version:

ifeq ($(JAVA_VERSION_MAJOR_MINOR),1.8)
	$(info Thank you for using Java 8 - version: $(JAVA_VERSION))
else
	$(error You're not using Java 8 - version: $(JAVA_VERSION))
endif

install.dependencies:
	cd bin; curl -L https://github.com/mozilla/geckodriver/releases/download/v0.11.1/geckodriver-v0.11.1-macos.tar.gz > geckodriver-v0.11.1-macos.tar.gz
	cd bin; tar -xvzf geckodriver-v0.11.1-macos.tar.gz
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
# Run Tests
# ===================================================================

test: check.version test.functional

test.sanity: check.version
	time curl -v $(SERVER) -s > /dev/null

test.functional: check.version
	$(info ******************)
	$(info Testing)
	$(info ******************)
	cd gurukula-test; mvn test -Dselenium.server=$(SELENIUM_SERVER) -Dgurukula.url=$(GURUKULA_URL)

test.functional.headless: check.version
	$(info ******************)
	$(info Testing (Headless))
	$(info ******************)

test.load: check.version
	$(info ******************)
	$(info test.load)
	$(info ******************)

test.longevity: check.version
	$(info ******************)
	$(info test.logevity)
	$(info ******************)

# ===================================================================
# Security
# ===================================================================

security.scan: check.version
	$(info ******************)
	$(info security.scan)
	$(info ******************)
