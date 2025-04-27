package IDATA2306.Group12.exception;


import IDATA2306.Group12.dto.ErrorResponse;
import IDATA2306.Group12.dto.user.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * A RestControllerAdvice for catching exceptions from the APIs and
 * formatting error responses to send to the client.
 */

@RestControllerAdvice
public class GlobalApiExceptionHandler {

    /**
     * Handle exception thrown when methods using @Valid fails to validate the @RequestBody.
     * @param exception The exception that is caught by the ExceptionHandler (automatic).
     * @return The response listing which fields were invalid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        StringBuilder message = new StringBuilder("Validation failed: ");

        for (FieldError error : result.getFieldErrors()) {
            message.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(". ");
        }

        ErrorResponse errorResponse = new ErrorResponse(message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle exception thrown when trying to register a user with conflicting fields.
     *
     * @param exception The exception that is caught by the ExceptionHandler (automatic).
     * @return A response explaining the conflict.
     */
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<UserErrorResponse> handleUserExistsException(UserExistsException exception, String field) {
        UserErrorResponse userErrorResponse = new UserErrorResponse(exception.getMessage(), field);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userErrorResponse);
    }
}
