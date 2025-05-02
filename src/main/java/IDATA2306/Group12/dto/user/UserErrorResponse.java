package IDATA2306.Group12.dto.user;

import IDATA2306.Group12.dto.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An error message sent by exception handlers in JSON format.
 * Contains
 */
public class UserErrorResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("field")
    private String field;

    /**
     * Create a new UserErrorResponse
     * @param errorMessage Error message describing the problem.
     * @param field The specific field
     */
    public UserErrorResponse(String errorMessage, String field) {
        this.setMessage(errorMessage);
        this.setField(field);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
