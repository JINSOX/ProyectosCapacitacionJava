package Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class NoSuchElementException extends Throwable implements ExceptionMapper<NoSuchElementException> {

    @Override
    public Response toResponse(NoSuchElementException e) {
        ApiResponse apiResponse = new ApiResponse("No se ha encontrado el elemento");
        return Response.status(404).entity(apiResponse).build();
    }
}
