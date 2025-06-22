# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Scala-based authentication service built with Apache Pekko HTTP (formerly Akka HTTP). It implements a RESTful API for user management with in-memory storage using the Actor model pattern.

## Core Architecture

The application follows a layered architecture with typed actors:

- **QuickstartApp.scala**: Main application entry point that bootstraps the HTTP server and actor system
- **UserRegistry.scala**: Core business logic actor that maintains user state in memory using immutable collections
- **UserRoutes.scala**: HTTP route definitions and request/response handling with Circe JSON marshalling using automatic derivation

The actor system uses the ask pattern with configurable timeouts for communication between HTTP routes and the UserRegistry actor. JSON serialization is handled automatically by Circe using `io.circe.generic.auto._` without requiring explicit format definitions.

## Development Commands

### Build and Run
```bash
sbtn run          # Start the HTTP server on localhost:8080
sbtn compile      # Compile the project
sbtn clean        # Clean build artifacts
```

### Testing
```bash
sbtn test         # Run all tests
sbtn "testOnly com.ambantis.auth.UserRoutesSpec"  # Run specific test class
```

### Code Formatting
```bash
sbtn scalafmtSbt  # Format build files
sbtn scalafmtAll  # Format all Scala source files
```
Always run both formatting commands after modifying build files or Scala source code.

### Documentation Maintenance
When making changes to the codebase, always review and update CLAUDE.md to ensure it accurately reflects:
- Architecture changes (new/removed/modified components)
- Dependency updates or additions
- New development commands or workflows
- Updated testing approaches or patterns

### Development Tools
The project is configured with Metals LSP support and SemanticDB for enhanced IDE integration.

## API Endpoints

The service exposes a REST API on port 8080:

- `GET /users` - List all users
- `POST /users` - Create a new user (expects JSON: `{"name": "string", "age": int, "countryOfResidence": "string"}`)
- `GET /users/{name}` - Get user by name
- `DELETE /users/{name}` - Delete user by name

## Configuration

- **application.conf**: Contains the ask timeout configuration (`my-app.routes.ask-timeout`)
- **application-test.conf**: Test-specific configuration overrides
- **logback.xml**: Logging configuration

## Key Dependencies

- Apache Pekko HTTP 1.2.0 for HTTP server and routing
- Apache Pekko Actor Typed 1.1.4 for the actor system
- Circe 0.14.13 for JSON serialization with automatic derivation
- pekko-http-circe 2.6.0 for Circe integration with Pekko HTTP
- ScalaTest 3.2.12 for testing with Pekko HTTP TestKit

## Testing Approach

Tests use ScalaTest with Pekko HTTP TestKit for route testing. The UserRoutesSpec demonstrates testing HTTP endpoints with Circe JSON marshalling/unmarshalling and proper status code validation. Tests validate responses using case class types rather than raw JSON strings.