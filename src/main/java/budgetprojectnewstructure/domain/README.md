# Domain Package

This package contains the core business entities of the application. These entities represent the data structures used throughout the application.

## Classes

- **DataEntity.java**: Interface for all domain entities.
- **YourMainEntity.java**: Example domain entity implementing `DataEntity`.

## Usage

To create a new domain entity, implement the `DataEntity` interface and define the necessary fields and methods.

## Example

```java
public class YourMainEntity implements DataEntity {
    private String id;
    private String name;

    // Implement interface methods
    @Override
    public String getUniqueIdentifier() {
        return id;
    }

    @Override
    public boolean validate() {
        // Validation logic
        return true;
    }

    // Getters and setters
}
