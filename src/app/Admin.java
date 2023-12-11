package app;

import app.audio.Collections.*;
import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.PlayerSource;
import app.user.Artist;
import app.user.Host;
import app.user.NormalUser;
import app.user.User;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * The type Admin.
 */
@Getter
@Setter
public final class Admin {
    @Getter
    private static List<String> names = new ArrayList<>();
    @Getter
    private static List<NormalUser> normalUsers = new ArrayList<>();
    @Getter
    @Setter
    private static List<Artist> artists = new ArrayList<>();
    @Getter
    @Setter
    private static List<Host> hosts = new ArrayList<>();

    //private static List<>
    @Getter
    private static List<Song> songs = new ArrayList<>();
    @Getter
    @Setter
    private static List<Podcast> podcasts = new ArrayList<>();
    @Getter
    @Setter
    private static List<Album> albums = new ArrayList<>();
    @Getter
    @Setter
    private static List<Event> events = new ArrayList<>();
    @Getter
    @Setter
    private static List<Merch> merches = new ArrayList<>();

    private static int timestamp = 0;
    private static final int LIMIT = 5;

    private Admin() { }


    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        normalUsers = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            normalUsers.add(new NormalUser(userInput.getUsername(),
                    userInput.getAge(), userInput.getCity()));
            names.add(userInput.getUsername());
        }
    }

    /**
     *
     * @param username
     * @param age
     * @param city
     * @param type
     * @return
     */
    public static String addUser(final String username, final Integer age,
                                 final String city, final String type) {
        for (String name : names) {
            if (name.equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }

        names.add(username);

        switch (type) {
            case "user" -> {
                NormalUser user = new NormalUser(username, age, city);
                normalUsers.add(user);
            }
            case "artist" -> {
                Artist artist = new Artist(username, age, city, type);
                artists.add(artist);
            }
            case "host" -> {
                Host host = new Host(username, age, city, type);
                hosts.add(host);
            }
            default -> {
            }
        }

       return "The username " + username + " has been added successfully.";
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }


    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (NormalUser user : normalUsers) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public static NormalUser getUser(final String username) {
        for (NormalUser user : normalUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param username
     * @return
     */
    public static String getName(final String username) {
        for (String name : names) {
            if (name.equals(username)) {
                return name;
            }
        }
        return null;
    }

    /**
     * @param username
     * @return
     */
    public static Artist getArtist(final String username) {
        for (Artist artist : artists) {
            if (artist.getName().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    /**
     * @param username
     * @return
     */
    public static Host getHosts(final String username) {
        for (Host host : hosts) {
            if (host.getName().equals(username)) {
                return host;
            }
        }
        return null;
    }


    /**
     * @return
     */
    public static ArrayList<String> getOnlineUsers() {
        ArrayList<String> onlineUsers = new ArrayList<>();
        for (NormalUser user : normalUsers) {
            if (user.isOnline()) {
                onlineUsers.add(user.getName());
            }
        }
        return onlineUsers;
    }

    /**
     * @return
     */
    public static ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();

        for (NormalUser user : normalUsers) {
                users.add(user.getName());
        }

        for (Artist artist : artists) {
            users.add(artist.getName());
        }

        for (Host host : hosts) {
            users.add(host.getName());
        }

        return users;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (NormalUser user : normalUsers) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }



    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * @param username
     * @return
     */
    public static String deleteUser(final String username) {
        User userToDelete;

        for (NormalUser userAux : normalUsers) {
            PlayerSource source = userAux.getPlayer().getSource();

            if (source != null) {
                AudioCollection audioCollection = source.getAudioCollection();
                AudioFile audioFile = source.getAudioFile();

                if ((audioCollection != null && audioCollection.getOwner().equals(username))
                        || (audioFile != null && audioFile.getArtist() != null
                && audioFile.getArtist().equals(username))) {
                    return username + " can't be deleted.";
                }

            }

            if (userAux.getPageOwner() != null
                    && userAux.getPageOwner().equals(username)) {
                return username + " can't be deleted.";
            }
        }

        NormalUser userAux = null;

        for (NormalUser user : normalUsers) {
            if (user.getName().equals(username)) {
                userAux = user;
            }
        }

        for (NormalUser normalUser : normalUsers) {
            normalUser.getLikedSongs().removeIf(song -> song.getArtist().equals(username));
            normalUser.getFollowedPlaylists().
                    removeIf(playlist -> playlist.getOwner().equals(username));
            if (userAux != null) {
                for (Playlist playlist : userAux.getFollowedPlaylists()) {
                    for (Playlist followedPlaylist : normalUser.getPlaylists()) {
                        if (playlist.getName().equals(followedPlaylist.getName())) {
                            playlist.decreaseFollowers();
                        }
                    }
                }
            }
        }

        songs.removeIf(song -> song.getArtist().equals(username));

        Iterator<String> iterator = names.iterator();
        String userName = null;

        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals(username)) {
                userName = name;
                iterator.remove();
                break;
            }
        }

        if (userName == null) {
            return "The username " + username + " doesn't exist.";
        }


        for (Artist artist : artists) {
            if (artist.getName().equals(username)) {
                userToDelete = artist;
                artists.remove(userToDelete);
            }
            return username + " was successfully deleted.";
        }

        for (NormalUser user : normalUsers) {
            if (user.getName().equals(username)) {
                userToDelete = user;
                normalUsers.remove(userToDelete);
            }
            return username + " was successfully deleted.";
        }

        for (Host host : hosts) {
            if (host.getName().equals(username)) {
                userToDelete = host;
                hosts.remove(userToDelete);
            }
            return username + " was successfully deleted.";
        }
        return null;
    }

    /**
     * Reset.
     */
    public static void reset() {
        normalUsers = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        names = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albums = new ArrayList<>();
        timestamp = 0;
        events = new ArrayList<>();
    }

}
