// File: `src/main/java/IDATA2306/Group12/util/EncodePassword.java`

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Utility for encodies a password using BCrypt and printing the result.
 */
public class EncodePassword {
    /**
     * Encodes a hardcoded password and prints the encoded value.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123"; // replace with your desired password
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
    }
}