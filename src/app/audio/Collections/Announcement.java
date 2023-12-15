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
     * @return the number of tracks
     * (always 0 for an announcement)
     */
    @Override
    public int getNumberOfTracks() {
        return 0;
    }

    /**
     * Retrieves the track at the specified index in the announcement.
     *
     * @param index The index of the track to retrieve.
     * @return The AudioFile at the specified index
     * (always null).
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return null;
    }

    /**
     * @return the list of songs from an announcement
     * (always null)
     */
    @Override
    public ArrayList<Song> getSongs() {
        return null;
    }

}
