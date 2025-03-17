package IDATA2306.Group12;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The telephone number of the user.
     */
    private String telephone;

    /**
     * The area code of the user's telephone number.
     */
    private String areaCode;

    /**
     * The bookings made by the user.
     */
    private List<Booking> bookings;

    /**
     * The listings favorited by the user.
     */
    private List<Listing> favorites;
}
