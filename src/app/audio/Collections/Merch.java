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
    public Merch(final String name, final String owner,
                 final String description, final Integer price,
                 final int timestamp) {
        super(name, owner);
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }

    /**
     * @return
     */
    @Override
    public int getNumberOfTracks() {
        return 0;
    }

    /**
     * @param index the index
     * @return
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Song> getSongs() {
        return null;
    }
}
