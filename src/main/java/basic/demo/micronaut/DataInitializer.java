package basic.demo.micronaut;

import basic.demo.micronaut.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public abstract class DataInitializer {

    public static List<Employee> getEmployees() {
        return new ArrayList<Employee>() {{
            add(new Employee("1", "Employee 1"));
            add(new Employee("2", "Employee 2"));
            add(new Employee("3", "Employee 3"));
        }};
    }
}
