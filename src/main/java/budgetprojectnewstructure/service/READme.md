# Service Package

This package contains the business logic and service layer of the application. It coordinates between the repository and UI layers.

## Classes

- **DataService.java**: Service class for processing data.

## Usage

To add new business logic, create a new service class or extend the existing `DataService` class.

## Example

```java
public class DataService {
    private DataRepository<YourMainEntity> repository;

    public DataService(DataRepository<YourMainEntity> repository) {
        this.repository = repository;
    }

    public List<YourMainEntity> processData() {
        // Business logic
        return repository.findAll();
    }
}
