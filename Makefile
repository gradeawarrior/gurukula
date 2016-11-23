JAVA_VERSION=$(shell java -version 2>&1 | grep version | sed 's/"//g' | awk '{print $$3}')
JAVA_VERSION_MAJOR_MINOR=$(shell echo $(JAVA_VERSION) | sed 's/\.[0-9_]*$$//')
SERVER=http://localhost:8080

default: check.version server.status

check.version:

ifeq ($(JAVA_VERSION_MAJOR_MINOR),1.8)
	$(info Thank you for using Java 8 - version: $(JAVA_VERSION))
else
	$(error You're not using Java 8 - version: $(JAVA_VERSION))
endif

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

test: check.version test.headless

test.sanity: check.version
	curl -v $(SERVER) -s > /dev/null

test.functional: check.version
	$(info ******************)
	$(info Testing
	$(info ******************)

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
