package pl.kawkadnia.common.error;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        String message = exception.getMessage() != null ? exception.getMessage() : "Resource not found";
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(message, 404))
                .build();
    }
}
