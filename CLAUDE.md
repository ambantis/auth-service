# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Scala-based authentication service built with Apache Pekko HTTP (formerly Akka HTTP). It implements a RESTful API for user management with in-memory storage using the Actor model pattern.

## Core Architecture

The application follows a layered architecture with typed actors:

- **QuickstartApp.scala**: Main application entry point that bootstraps the HTTP server and actor system
- **UserRegistry.scala**: Core business logic actor that maintains user state in memory using immutable collections
- **UserRoutes.scala**: HTTP route definitions and request/response handling with JSON marshalling
- **JsonFormats.scala**: Spray JSON format definitions for serialization/deserialization

The actor system uses the ask pattern with configurable timeouts for communication between HTTP routes and the UserRegistry actor.

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
- Spray JSON for JSON serialization
- ScalaTest 3.2.12 for testing with Pekko HTTP TestKit
- Circe 0.14.13 (configured but not currently used)

## Testing Approach

Tests use ScalaTest with Pekko HTTP TestKit for route testing. The UserRoutesSpec demonstrates testing HTTP endpoints with JSON marshalling/unmarshalling and proper status code validation.