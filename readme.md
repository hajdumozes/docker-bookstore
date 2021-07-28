### Application

The application provides a REST API to create, list and delete orders in a bookstore.

To use the endpoints available, visit the page:
`http://${application-url}/swagger-ui.html`

Default value:
`http://localhost:8080/swagger-ui.html`


### Build

All commands must be run in root:

- create jar: `mvn package`
- build coker images: `docker build -t docker .`
- run images: `docker-compose up`

### Initial Databases

Currently, there is no option to add customers or books to the relevant databases. To access the order functionality,
use the following test data:

#### Customer

| Id                                        | Name
|-------------------------------------------|----------|
| 1                                         | John Doe |
| 2                                         | Jane Doe |

#### Book

| Id                                        | Name
|-------------------------------------------|----------|
| 1                                         | The Little Prince |
| 2                                         | Harry Potter and the Philosopher's Stone |
| 3                                         | Odyssey |
| 4                                         | Clean Code |

### Environment variables

| Name                                      | Format   | Default value                                      | Comment                                                    |
|-------------------------------------------|----------|----------------------------------------------------|------------------------------------------------------------|
| `DATASOURCE_USERNAME`                     | string   |`compose-postgres`                                  | Must match the docker-compose.yml db environment variables |
| `DATASOURCE_PASSWORD`                     | string   |`compose-postgres`                                  | Must match the docker-compose.yml db environment variables |
| `DATASOURCE_URL`                          | string   |`jdbc:postgresql://localhost:5432/compose-postgres` | Must match the docker-compose.yml db environment variables |
| `HIBERNATE_DDL_AUTO`                      | string   | `update`                                           | Options: validate, update, create, create-drop, none       |
| `SHOW_SQL`                                | boolean   | `false`                                           | Show sql queries on console. Only use it for development   |
