all: test build

backup: total-backup

total-backup: backup-local backup-offsite-1 backup-offsite-2
	echo "Backup to local and remote copy: Not Implimented Yet"

local-backup: backup-local
	echo "Backup to Local: Not Implimented Yet"

build:
	echo "Build: Not Implimented Yet"

clean:
	echo "Clean: Not Implimented Yet"

test:
	echo "Test: Not Implimented Yet"

compile:
	echo "Compile: Not Implimented Yet"

backup-local:
	echo "Local Backup: Not Implimented Yet"

do-backup-local:
	echo "Backup Process: Not Implimented Yet"

backup-offsite-1:
	echo "Push to Remote Offsite 1: Not Implimented Yet"

backup-offsite-2:
	echo "Push to Remote Offsite 2: Not Implimented Yet"