package IDATA2306.Group12.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * Represents a user
 */
@Entity
@Table(name = "Users")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    /**
     * The first name of the user.
     */
    @Column(name = "firstName")
    @JsonProperty("firstname")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "lastName")
    @JsonProperty("lastname")
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    /**
     * The telephone number of the user.
     */
    @Column(name = "telephone")
    @JsonProperty("telephone")
    private String telephone;

    /**
     * The area code of the user's telephone number.
     */
    @Column(name = "areaCode")
    @JsonProperty("areaCode")
    private String areaCode;

    @Column(name = "role")
    @JsonProperty("role")
    private String role = "USER";

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
