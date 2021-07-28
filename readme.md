### Build

All commands must be run in root:

- create jar: `mvn package`
- build coker images: `docker build -t docker .`
- run images: `docker-compose up`

### Initial Databases

Currently, there is no option to add customers or books to the relevant databases.
To access the order functionality, use the following test data:

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