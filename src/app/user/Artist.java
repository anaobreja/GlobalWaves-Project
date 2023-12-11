package app.user;

import app.Admin;
import app.audio.Collections.*;
import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.page.ArtistPage;
import app.player.PlayerSource;
import app.utils.EventValidator;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Artist extends User {
    @Getter
    @Setter
    private ArtistPage artistPage;

    @Getter
    @Setter
    private EventValidator eventValidator;

    public Artist(String username, int age, String city, String userType) {
        super(username, age, city, userType);
        this.artistPage = new ArtistPage(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
    }
    private static Song converseSong (SongInput songInput) {
        String name = songInput.getName();
        Integer duration = songInput.getDuration();
        String album = songInput.getAlbum();
        ArrayList<String> tags = songInput.getTags();
        String lyrics = songInput.getLyrics();
        String genre = songInput.getGenre();
        Integer releaseYear = songInput.getReleaseYear();
        String artist = songInput.getArtist();

        return new Song(name, duration, album, tags,
                lyrics, genre, releaseYear, artist);

    }


    public String addAlbum(String name, String owner, ArrayList<SongInput> songs, Integer releaseYear,
                           int timestamp) {
        ArrayList<Song> songsResult = new ArrayList<>();

        for(SongInput songInput : songs) {
            Song song = converseSong(songInput);
            songsResult.add(song);
        }

        Album album = new Album(name, owner, songsResult, releaseYear, timestamp);

        Artist artist = Admin.getArtist(owner);
        for (Album album1 : artist.getArtistPage().getAlbums()) {
            if (album1.getName().equals(album.getName()))
                    return owner + " has another album with the same name.";
        }

        Set<String> songNames = new HashSet<>();
        for (Song song : album.getSongs()) {
            if (!songNames.add(song.getName())) {
                return owner + " has the same song at least twice in this album.";
            }
        }

        Admin.getAlbums().add(album);
        artistPage.getAlbums().add(album);

        for (Song song : songsResult) {
            Admin.getSongs().add(song);
        }


        return owner + " has added new album successfully.";
    }

    public String removeAlbum(String name, String username) {
        for (NormalUser userAux : Admin.getNormalUsers() ) {
            PlayerSource source = userAux.getPlayer().getSource();

            if (source != null) {
                AudioCollection audioCollection = source.getAudioCollection();
                AudioFile audioFile = source.getAudioFile();
                if (audioCollection != null && audioCollection.matchesOwner(username))
                    return username + " can't delete this album.";
                if ((audioFile != null && audioFile.getAlbum() != null
                                && audioFile.getAlbum().equals(name))) {
                    return username + " can't delete this album.";
                }
                if(audioCollection != null && audioCollection.getSongs() != null) {
                    ArrayList<Song> songs = audioCollection.getSongs();
                    for (Song song : songs) {
                        if (song.matchesArtist(username))
                            return username + " can't delete this album.";
                    }
                }
            }
        }

        boolean hasAlbum = false;
        for(Album albumAux : artistPage.getAlbums()) {
            if (albumAux.getName().equals(name)) {
                hasAlbum = true;
                break;
            }
        }

        if (!hasAlbum){
            return username + " doesn't have an album with the given name.";
        }

        Admin.getAlbums().removeIf(album -> album.getName().equals(name));

        Artist user = Admin.getArtist(username);

        assert user != null;
        user.getArtistPage().getAlbums().removeIf(album -> album.getName().equals(name));

        return username + " deleted the album.";
    }


    public String addEvent(String name, String owner, String description, String date,
                           int timestamp) {

        if (!EventValidator.isValidDate(date))
            return "Event for " + owner + " does not have a valid date.";
        Event event = new Event(name, owner, description, date, timestamp);

        for (Event event1 : artistPage.getEvents()) {
            if (event1.getName().equals(event.getName()))
                return owner + " has another event with the same name.";
        }

        artistPage.getEvents().add(event);


        return owner + " has added new event successfully.";
    }

    public String revomeEvent(String name, String owner) {
        Event event = null;

        for (Event event1 : artistPage.getEvents()) {
            if (event1.getName().equals(name))
                event = event1;
        }

        if (event == null)
            return owner + " has no event with the given name.";

        artistPage.getEvents().remove(event);


        return owner + " deleted the event successfully.";
    }

    public String addMerch(String name, String owner, String description, Integer price,
                           int timestamp) {
        Merch merch = new Merch(name, owner, description, price, timestamp);

        for (Merch merch1 : artistPage.getMerches()) {
            if (merch1.getName().equals(merch.getName()))
                return owner + " has merchandise with the same name.";
        }

        artistPage.getMerches().add(merch);


        return owner + " has added new merchandise successfully.";
    }


}
