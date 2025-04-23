package Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RuntimeException extends Throwable implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(RuntimeException e) {
    ApiResponse apiResponse = new ApiResponse("Error al consultar la base de datos");
    return Response.status(500).entity(apiResponse).build();
  }
}