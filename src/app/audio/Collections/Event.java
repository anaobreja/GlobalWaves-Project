package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Event extends AudioCollection {
    private String description;
    private String date;
    private int timestamp;
    /**
     * Instantiates a new Audio collection.
     *
     * @param name  the name
     * @param owner the owner
     */
    public Event(String name, String owner, String description, String date,
                 int timestamp) {
        super(name, owner);
        this.description = description;
        this.date = date;
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
