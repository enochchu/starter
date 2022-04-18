all: test build

format: sourceformat staticcheck

build:
	go build -o bin/main main.go

clean:
	rm -R bin

compile:
	GOOS=windows GOARCH=amd64 go build -o bin/app-amd64.exe main.go
	# GOOS=linux GOARCH=amd64 go build -o bin/app-amd64-linux main.go
	# GOOS=linux GOARCH=386 go build -o bin/app-386-linux main.go

run:
	go run main.go

sourceformat:
	go fmt ./...

# Credit: go-jira's makefile
staticcheck: ## Runs static analysis to prevend bugs, foster code simplicity, performance and editor integration.
	go install honnef.co/go/tools/cmd/staticcheck@2022.1
	staticcheck ./... > staticcheck.log

test:
	go test ./...