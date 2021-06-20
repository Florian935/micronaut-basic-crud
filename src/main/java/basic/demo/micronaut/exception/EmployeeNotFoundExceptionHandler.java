package basic.demo.micronaut.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static io.micronaut.http.MediaType.TEXT_PLAIN;

@Produces({APPLICATION_JSON, TEXT_PLAIN})
@Singleton
@Requires(classes = {EmployeeNotFoundException.class, ExceptionHandler.class})
public class EmployeeNotFoundExceptionHandler implements ExceptionHandler<EmployeeNotFoundException, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, EmployeeNotFoundException exception) {
        return HttpResponse.notFound("Employee not found");
    }
}
