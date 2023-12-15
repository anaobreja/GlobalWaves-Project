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
     * @param songs the songs
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
     * Returns the total number of tracks in the collection.
     *
     * @return The total number of tracks.
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * Retrieves the track at the specified index in the collection.
     *
     * @param index The index of the track to retrieve.
     * @return The AudioFile at the specified index.
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

}
