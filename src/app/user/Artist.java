package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.page.ArtistPage;
import app.player.PlayerSource;
import app.utils.EventValidator;
import fileio.input.SongInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Artist extends User {
    private ArtistPage artistPage;

    private EventValidator eventValidator;

    /**
     * @param username The username of the artist.
     * @param age      The age of the artist.
     * @param city     The city where the artist resides.
     * @param userType The type of user (artist in this case).
     */
    public Artist(final String username, final int age,
                  final String city, final String userType) {
        super(username, age, city, userType);
        this.artistPage = new ArtistPage(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
    }

    /**
     * Converts SongInput to a Song object.
     *
     * @param songInput The input containing song details.
     * @return The converted Song object.
     */
    private static Song converseSong(final SongInput songInput) {
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


    /**
     * Adds a new album to the collection.
     *
     * @param name         The name of the album.
     * @param owner        The owner of the album.
     * @param songs        The list of songs in the album.
     * @param releaseYear  The release year of the album.
     * @param timestamp    The timestamp of the album addition.
     * @return A message indicating the success or failure of the album addition.
     */
    public String addAlbum(final String name, final String owner,
                           final ArrayList<SongInput> songs, final Integer releaseYear,
                           final int timestamp) {
        Admin admin = Admin.getInstance();

        ArrayList<Song> songsResult = new ArrayList<>();

        for (SongInput songInput : songs) {
            Song song = converseSong(songInput);
            songsResult.add(song);
        }

        Album album = new Album(name, owner, songsResult, releaseYear, timestamp);

        Artist artist = admin.getArtist(owner);
        for (Album album1 : artist.getArtistPage().getAlbums()) {
            if (album1.getName().equals(album.getName())) {
                return owner + " has another album with the same name.";
            }
        }

        Set<String> songNames = new HashSet<>();
        for (Song song : album.getSongs()) {
            if (!songNames.add(song.getName())) {
                return owner + " has the same song at least twice in this album.";
            }
        }

        admin.getAlbums().add(album);
        artistPage.getAlbums().add(album);

        for (Song song : songsResult) {
            admin.getSongs().add(song);
        }


        return owner + " has added new album successfully.";
    }

    /**
     * Removes an album belonging to a user.
     *
     * @param name     The name of the album to be removed.
     * @param username The username of the user attempting to remove the album.
     * @return A message indicating the success or failure of the album removal.
     */
    public String removeAlbum(final String name,
                              final String username) {
        Admin admin = Admin.getInstance();
        for (NormalUser userAux : admin.getNormalUsers()) {
            PlayerSource source = userAux.getPlayer().getSource();

            if (source != null) {
                AudioCollection audioCollection = source.getAudioCollection();
                AudioFile audioFile = source.getAudioFile();
                if (audioCollection != null && audioCollection.matchesOwner(username)) {
                    return username + " can't delete this album.";
                }
                if ((audioFile != null && audioFile.getAlbum() != null
                        && audioFile.getAlbum().equals(name))) {
                    return username + " can't delete this album.";
                }
                if (audioCollection != null && audioCollection.getSongs() != null) {
                    ArrayList<Song> songs = audioCollection.getSongs();
                    for (Song song : songs) {
                        if (song.matchesArtist(username)) {
                            return username + " can't delete this album.";
                        }
                    }
                }
            }
        }

        boolean hasAlbum = false;
        for (Album albumAux : artistPage.getAlbums()) {
            if (albumAux.getName().equals(name)) {
                hasAlbum = true;
                break;
            }
        }

        if (!hasAlbum) {
            return username + " doesn't have an album with the given name.";
        }

        admin.getAlbums().removeIf(album -> album.getName().equals(name));

        Artist user = admin.getArtist(username);

        assert user != null;
        user.getArtistPage().getAlbums().removeIf(album -> album.getName().equals(name));

        return username + " deleted the album.";
    }


    /**
     * Adds a new event to an artist's page.
     *
     * @param name        The name of the event.
     * @param owner       The owner of the event.
     * @param description The description of the event.
     * @param date        The date of the event.
     * @param timestamp   The timestamp of the event addition.
     * @return A message indicating the success or failure of the event addition.
     */
    public String addEvent(final String name, final String owner,
                           final String description, final String date,
                           final int timestamp) {

        EventValidator eventValidator = EventValidator.getInstance();

        if (!eventValidator.isValidDate(date)) {
            return "Event for " + owner + " does not have a valid date.";
        }
        Event event = new Event(name, owner, description, date, timestamp);

        for (Event event1 : artistPage.getEvents()) {
            if (event1.getName().equals(event.getName())) {
                return owner + " has another event with the same name.";
            }
        }

        artistPage.getEvents().add(event);


        return owner + " has added new event successfully.";
    }

    /**
     * Removes an event from an artist's page.
     *
     * @param name  The name of the event to be removed.
     * @param owner The owner of the event.
     * @return A message indicating the success or failure of the event removal.
     */
    public String removeEvent(final String name, final String owner) {
        Event event = null;

        for (Event event1 : artistPage.getEvents()) {
            if (event1.getName().equals(name)) {
                event = event1;
            }
        }

        if (event == null) {
            return owner + " has no event with the given name.";
        }

        artistPage.getEvents().remove(event);


        return owner + " deleted the event successfully.";
    }

    /**
     * Adds new merchandise to an artist's page.
     *
     * @param name        The name of the merchandise.
     * @param owner       The owner of the merchandise.
     * @param description The description of the merchandise.
     * @param price       The price of the merchandise.
     * @param timestamp   The timestamp of the merchandise addition.
     * @return A message indicating the success or failure of the merchandise addition.
     */
    public String addMerch(final String name, final String owner,
                           final String description, final Integer price,
                           final int timestamp) {
        Merch merch = new Merch(name, owner, description, price, timestamp);

        for (Merch merch1 : artistPage.getMerchandise()) {
            if (merch1.getName().equals(merch.getName())) {
                return owner + " has merchandise with the same name.";
            }
        }

        artistPage.getMerchandise().add(merch);


        return owner + " has added new merchandise successfully.";
    }


}
