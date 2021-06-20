package basic.demo.micronaut;

import basic.demo.micronaut.domain.Employee;
import basic.demo.micronaut.exception.EmployeeNotFoundException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;
import java.util.Optional;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static io.micronaut.http.MediaType.TEXT_PLAIN;

@Controller("/employees")
public class EmployeeController {
    private List<Employee> employees = DataInitializer.getEmployees();

    @Produces({APPLICATION_JSON, TEXT_PLAIN})
    @Get
    public HttpResponse<Flowable<List<Employee>>> getEmployees() {
        try {
            final Flowable<List<Employee>> employeeFlowable = Flowable.just(employees);
            return HttpResponse.ok(employeeFlowable);
        } catch (Exception ex) {
            throw new EmployeeNotFoundException();
        }
    }

    @Produces({APPLICATION_JSON, TEXT_PLAIN})
    @Get(uri = "/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        final Optional<Employee> employeeFound = employees.stream().filter(e -> e.getId().equals(id)).findFirst();

        if (employeeFound.isPresent()) {
            return employeeFound.get();
        }

        throw new EmployeeNotFoundException();
    }

    @Produces({APPLICATION_JSON, TEXT_PLAIN})
    @Post
    public Employee saveEmployee(@Body Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Produces({APPLICATION_JSON, TEXT_PLAIN})
    @Delete(uri = "/{id}")
    public HttpResponse<Void> deleteEmployee(@PathVariable String id) {
        final Optional<Employee> employeeToDelete = employees.stream().filter(employee -> employee.getId().equals(id)).findFirst();
        if (employeeToDelete.isPresent()) {
            employees.remove(employeeToDelete.get());
            return HttpResponse.ok();
        }

        throw new EmployeeNotFoundException();
    }
}