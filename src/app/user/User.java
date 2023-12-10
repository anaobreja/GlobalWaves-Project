package app.user;


import app.audio.Collections.Playlist;
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

    public User(String username, int age, String city, String userType) {
        super(username);
        this.age = age;
        this.city = city;
        this.userType = userType;
    }
}

