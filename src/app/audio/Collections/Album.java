package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Album extends AudioCollection {
    private final ArrayList<Song> songs;
    private Integer releaseYear;
    private int timestamp;

    /**
     * Instantiates a new Audio collection.
     *
     * @param name  the name
     * @param owner the owner
     * @param songs
     */
    public Album(final String name, final String owner,
                 final ArrayList<Song> songs, final Integer releaseYear,
                 final int timestamp) {
        super(name, owner);
        this.releaseYear = releaseYear;
        this.timestamp = timestamp;
        this.songs = songs;
    }

    /**
     * @return
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * @param index the index
     * @return
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }


}
