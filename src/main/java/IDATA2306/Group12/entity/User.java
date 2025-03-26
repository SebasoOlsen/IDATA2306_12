package IDATA2306.Group12.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


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
    @JsonProperty("firstname")
    private String firstName;

    /**
     * The last name of the user.
     */
    @JsonProperty("lastname")
    private String lastName;

    /**
     * The email address of the user.
     */
    @JsonProperty("email")
    private String email;

    /**
     * The password of the user.
     */
    @JsonProperty("password")
    private String password;

    /**
     * The telephone number of the user.
     */
    @JsonProperty("telephone")
    private String telephone;

    /**
     * The area code of the user's telephone number.
     */
    @JsonProperty("areaCode")
    private String areaCode;

    /**
     * Create a User using parameters.
     * @param id id of the user
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email address
     * @param password user's password
     * @param telephone user's telephone number
     * @param areaCode telephone area code (i.e "+47" for Norway)
     */
    public User(int id, String firstName, String lastName, String email,
                String password, String telephone, String areaCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.areaCode = areaCode;
    }

    public User() {}
}
