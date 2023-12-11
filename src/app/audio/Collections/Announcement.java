package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Announcement extends AudioCollection {
    private String description;

    /**
     * Instantiates a new Audio collection.
     *
     * @param name  the name
     * @param owner the owner
     */
    public Announcement(final String name, final String owner,
                        final String description) {
        super(name, owner);
        this.description = description;
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
