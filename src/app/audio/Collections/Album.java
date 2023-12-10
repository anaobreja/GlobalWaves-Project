package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.SongInput;
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
    public Album(String name, String owner, ArrayList<Song> songs, Integer releaseYear,
                 int timestamp) {
        super(name, owner);
        this.releaseYear = releaseYear;
        this.timestamp = timestamp;
        this.songs = songs;
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }



}
