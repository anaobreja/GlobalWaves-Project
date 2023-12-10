package app.audio.Collections;

import app.audio.Files.AudioFile;
import lombok.Getter;
import lombok.Setter;

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
    public Announcement(String name, String owner, String description) {
        super(name, owner);
        this.description = description;
    }

    @Override
    public int getNumberOfTracks() {
        return 0;
    }

    @Override
    public AudioFile getTrackByIndex(int index) {
        return null;
    }
}
