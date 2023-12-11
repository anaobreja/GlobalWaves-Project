package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Merch extends AudioCollection {
    @Getter
    private String description;
    @Getter
    @Setter
    private Integer price;
    private int timestamp;

    /**
     * Instantiates a new Audio collection.
     *
     * @param name      the name
     * @param owner     the owner
     * @param timestamp
     */
    public Merch(String name, String owner, String description, Integer price, int timestamp) {
        super(name, owner);
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }

    @Override
    public int getNumberOfTracks() {
        return 0;
    }

    @Override
    public AudioFile getTrackByIndex(int index) {
        return null;
    }

    @Override
    public ArrayList<Song> getSongs() {
        return null;
    }
}
