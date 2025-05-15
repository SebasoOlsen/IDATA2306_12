package IDATA2306.Group12.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response data transfer object")
public class UserResponseDTO {

    @Schema(description = "ID of the user")
    private int id;
    @Schema(description = "First name of the user")
    private String firstName;
    @Schema(description = "Last name of the user")
    private String lastName;
    @Schema(description = "Email of the user")
    private String email;
    @Schema(description = "Telephone number of the user")
    private String telephone;
    @Schema(description = "Area code of the user's telephone number")
    private String areaCode;
    @Schema(description = "Role of the user")
    private String role;

    public UserResponseDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
