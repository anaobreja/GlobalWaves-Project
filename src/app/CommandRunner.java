package app;

import app.audio.Collections.Album;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.Song;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.NormalUser;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
import app.audio.Files.Song;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        NormalUser user =  Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        String message;
        ArrayList<String> results = new ArrayList<>();

        if (!user.online) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            results = user.search(filters, type);
            message = "Search returned " + results.size() + " results";
        }

            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", message);

            objectNode.put("results", objectMapper.valueToTree(results));

            return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        String message = "";
        if (user != null) {
             message = user.select(commandInput.getItemNumber());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;

        String message = user.load();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        NormalUser user = (NormalUser) Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.playPause();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.repeat();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.forward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.backward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.like();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.next();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.prev();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                                             commandInput.getTimestamp());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String message = user.follow();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        PlayerStats stats = user.getPlayerStats();

        stats.isPaused();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        stats.isPaused();

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        if (user == null)
            return null;
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user != null) {
             message = user.switchConnectionStatus();
        } else {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {

        ArrayList<String> results = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));


        return objectNode;
    }

    public static ObjectNode getAllUsers(final CommandInput commandInput) {

        ArrayList<String> results = Admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));


        return objectNode;
    }


    public static ObjectNode addUser(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        String city = commandInput.getCity();
        Integer age = commandInput.getAge();
        String type = commandInput.getType();

        String message = Admin.addUser(username, age, city, type);


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        String city = commandInput.getCity();
        Integer age = commandInput.getAge();
        String type = commandInput.getType();

        String message = Admin.deleteUser(username);


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        NormalUser user = Admin.getUser(commandInput.getUsername());

        if (user == null)
            return null;
        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode addAlbum(final CommandInput commandInput) {
        String message;
        String name = Admin.getName(commandInput.getUsername());

        if (name == null)
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        else {
            Artist artist = Admin.getArtist(commandInput.getUsername());

            if (artist == null)
                message = commandInput.getUsername() + " is not an artist.";
            else {
                ArrayList<SongInput> songs = commandInput.getSongs();
                Integer releaseYear = commandInput.getReleaseYear();
                int timestamp = commandInput.getTimestamp();
                String owner = commandInput.getUsername();
                name = commandInput.getName();

                message = artist.addAlbum(name, owner, songs, releaseYear, timestamp);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode showAlbums(final CommandInput commandInput) {
        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (artist == null)
            return null;

        List<Album> albums = Admin.getAlbums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayNode resultArray = objectMapper.createArrayNode();
        for (Album album : albums) {
            if (album.getOwner().equals(commandInput.getUsername())) {
                ObjectNode albumNode = objectMapper.createObjectNode(); // Creează un nou nod pentru fiecare album

                albumNode.put("name", album.getName());

                ArrayNode songsArray = objectMapper.createArrayNode();
                for (Song song : album.getSongs()) {
                    songsArray.add(song.getName());
                }
                albumNode.putArray("songs").addAll(songsArray);

                resultArray.add(albumNode);
            }
        }

        objectNode.putArray("result").addAll(resultArray);

        return objectNode;
    }

    public static ObjectNode addEvent(final CommandInput commandInput) {
        String message;
        String name = Admin.getName(commandInput.getUsername());

        if (name == null)
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        else {
            Artist artist = Admin.getArtist(commandInput.getUsername());

            if (artist == null)
                message = commandInput.getUsername() + " is not an artist.";
            else {
                String description = commandInput.getDescription();
                String date = commandInput.getDate();
                int timestamp = commandInput.getTimestamp();
                String owner = commandInput.getUsername();
                name = commandInput.getName();

                message = artist.addEvent(name, owner, description, date, timestamp);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode removeEvent(final CommandInput commandInput) {
        String message;
        String name = Admin.getName(commandInput.getUsername());

        if (name == null)
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        else {
            Artist artist = Admin.getArtist(commandInput.getUsername());

            if (artist == null)
                message = commandInput.getUsername() + " is not an artist.";
            else {
                String description = commandInput.getDescription();
                String date = commandInput.getDate();
                int timestamp = commandInput.getTimestamp();
                String owner = commandInput.getUsername();
                name = commandInput.getName();

                message = artist.addEvent(name, owner, description, date, timestamp);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode addMerch(final CommandInput commandInput) {
        String message;
        String name = Admin.getName(commandInput.getUsername());

        if (name == null)
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        else {
            Artist artist = Admin.getArtist(commandInput.getUsername());
            Integer price = commandInput.getPrice();
            if (artist == null)
                message = commandInput.getUsername() + " is not an artist.";
            else if (price < 0) {
                message = "Price for merchandise can not be negative.";
            } else {
                String description = commandInput.getDescription();
                int timestamp = commandInput.getTimestamp();
                String owner = commandInput.getUsername();
                name = commandInput.getName();

                message = artist.addMerch(name, owner, description, price, timestamp);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode addPodcast(final CommandInput commandInput) {
        String message;
        String name = Admin.getName(commandInput.getUsername());

        if (name == null)
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        else {
            Host host = Admin.getHosts(commandInput.getUsername());

            if (host == null)
                message = commandInput.getUsername() + " is not a host.";
            else {
                ArrayList<EpisodeInput> episodes = commandInput.getEpisodes();
                int timestamp = commandInput.getTimestamp();
                String owner = commandInput.getUsername();
                name = commandInput.getName();
                message = host.addPodcast(name, owner, episodes, timestamp);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }
}
