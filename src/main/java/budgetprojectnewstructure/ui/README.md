

# UI Package

This package defines the user interface components of the application. It includes interfaces and implementations for different UI frameworks.

## Interfaces

- **UiComponent.java**: Interface for UI components.

## Implementations

- **MainApplicationWindow.java**: Swing UI implementation.
- **WebController.java**: Web UI implementation.
- **MobileAppController.java**: Mobile/app UI implementation.

## Usage

To add a new UI component, implement the `UiComponent` interface and configure the application to use the new implementation.

## Example

```java
public class MainApplicationWindow implements UiComponent {
    private DataService dataService;

    public MainApplicationWindow(DataService dataService) {
        this.dataService = dataService;
        initUI();
   
