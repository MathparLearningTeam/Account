# Account module
### TL;DR
    Account module is responsible for maintaining user accounts and authentication. It also serves as CAS for all the applications in the system.
### Common instructions
- Running the module locally
    - make sure the secret manager is running locally and is accessible
        - If your secret manager is running on the port 8095 you should be able to follow the [url](http://localhost:8095/getNamespaceProperties?namespace=account) to retrieve secrets
    - set the secret manager url as environment variable `SECRETMANAGER_URL`
    - run the command `mvn spring-boot:run -Dserver.port=<PORT:8090>`
- Running unit tests
    - run the command `mvn test`
- Create a new release
    - create a pull request with required changes into master branch
    - use jenkins to build an application from master branch using job BuildService
    
Detailed description
-
### General structure

There are 6 packages in sources:
- `_configs` is a package responsible for any kind of configuration and starter classes
- `controllers` package contains all the public and private endpoints that application exposes
- `entities` is a package with Java representation of database tables
- `repositories` is a package with Spring Data repositories to work with database
- `services` package contains all the classes which implement logical components of the module
- `utils` is a package for any kind of utilities, like DTOs, properties, custom exceptions etc

Account module has 2 main global purposes: maintaining user accounts and providing authentication (CAM) services for other modules and clients.

### Database
Database tables are created and managed using scripts in the `ddl` resource folder. Entity classes are only validating the tables to be up to date with codebase.

### Controllers
All the controllers were designed as REST controllers. There are no templates or other static resources served by the account module.

Public endpoints are the endpoints marked with `@PublicApi` on the class level. They are exposed to clients and need to be treated with caution.

Other (private) endpoints are available only to other services BUT still MUST be authorized and validated.