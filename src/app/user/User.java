package app.user;


import app.audio.LibraryEntry;
import lombok.Getter;
import lombok.Setter;

/**
 * The type User.
 */
@Getter
@Setter
public class User extends LibraryEntry {
    private int age;
    private String city;

    private String userType;

    public User(final String username, final int age,
                final String city, final String userType) {
        super(username);
        this.age = age;
        this.city = city;
        this.userType = userType;
    }
}

