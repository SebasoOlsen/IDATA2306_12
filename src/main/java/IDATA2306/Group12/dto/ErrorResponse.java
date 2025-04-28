package IDATA2306.Group12.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A generic error message sent by exception handlers in JSON format.
 */
public class ErrorResponse {

    @JsonProperty("errorMessage")
    private String message;

    @JsonProperty("statusCode")
    private int statusCode;

    /**
     * Create a new ErrorResponse
     * @param errorMessage Error message explaining the problem.
     */
    public ErrorResponse(String errorMessage) {
        this.setMessage(errorMessage);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
