package pl.kawkadnia.common.error;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<IllegalStateException> {

    @Override
    public Response toResponse(IllegalStateException exception) {
        String message = exception.getMessage() != null ? exception.getMessage() : "Conflict";
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorMessage(message, 409))
                .build();
    }
}
