package app;

import app.audio.Collections.Album;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.NormalUser;
import app.utils.Statistics;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
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
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {

    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        String message;
        ArrayList<String> results = new ArrayList<>();

        if (!user.isOnline()) {
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
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
        Admin admin = Admin.getInstance();
        List<String> songs = admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 albums.
     *
     * @param commandInput the command input
     * @return the top 5 albums
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        Statistics statistics = Statistics.getInstance();
        List<String> albums = statistics.getTopLikedAlbums(admin.getAlbums());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Gets top 5 artists.
     *
     * @param commandInput the command input
     * @return the top 5 artists
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        Statistics statistics = Statistics.getInstance();
        List<String> albums = statistics.getTop5Artists(admin.getAlbums());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> playlists = admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Toggles the user's online/offline status based on the provided command input's username.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());
        String message;

        if (user != null) {
            message = user.switchConnectionStatus();
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());
            Host host = admin.getHost(commandInput.getUsername());
            if (artist != null || host != null) {
                message = commandInput.getUsername() + " is not a normal user.";
            } else {
                message = "The username " + commandInput.getUsername() + " doesn't exist.";
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Get online users object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();

        ArrayList<String> results = admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));


        return objectNode;
    }

    /**
     * Get all users object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();

        ArrayList<String> results = admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));


        return objectNode;
    }


    /**
     * Add user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String username = commandInput.getUsername();
        String city = commandInput.getCity();
        Integer age = commandInput.getAge();
        String type = commandInput.getType();

        String message = admin.addUser(username, age, city, type);


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Delete user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String username = commandInput.getUsername();

        String message = admin.deleteUser(username);


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Print current page node (4 possibilities: artist page,
     * host page, home page and liked content page).
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Change page object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        NormalUser user = admin.getUser(commandInput.getUsername());

        String message = user.changePage(commandInput.getNextPage());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());

            if (artist == null) {
                message = commandInput.getUsername() + " is not an artist.";
            } else {
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

    /**
     * Remove album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());

            if (artist == null) {
                message = commandInput.getUsername() + " is not an artist.";
            } else {
                name = commandInput.getName();
                String username = commandInput.getUsername();
                message = artist.removeAlbum(name, username);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show albums object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<Album> albums = admin.getAlbums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayNode resultArray = objectMapper.createArrayNode();
        for (Album album : albums) {
            if (album.getOwner().equals(commandInput.getUsername())) {
                ObjectNode albumNode = objectMapper.createObjectNode();

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

    /**
     * Add event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());

            if (artist == null) {
                message = commandInput.getUsername() + " is not an artist.";
            } else {
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

    /**
     * Remove event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());

            if (artist == null) {
                message = commandInput.getUsername() + " is not a host.";
            } else {
                name = commandInput.getName();
                String username = commandInput.getUsername();
                message = artist.removeEvent(name, username);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add merch object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Artist artist = admin.getArtist(commandInput.getUsername());
            Integer price = commandInput.getPrice();
            if (artist == null) {
                message = commandInput.getUsername() + " is not an artist.";
            } else if (price < 0) {
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

    /**
     * Add podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Host host = admin.getHost(commandInput.getUsername());

            if (host == null) {
                message = commandInput.getUsername() + " is not a host.";
            } else {
                ArrayList<EpisodeInput> episodes = commandInput.getEpisodes();
                String owner = commandInput.getUsername();
                name = commandInput.getName();
                message = host.addPodcast(name, owner, episodes);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Host host = admin.getHost(commandInput.getUsername());

            if (host == null) {
                message = commandInput.getUsername() + " is not a host.";
            } else {
                name = commandInput.getName();
                String username = commandInput.getUsername();
                message = host.removePodcast(name, username);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Host host = admin.getHost(commandInput.getUsername());

            if (host == null) {
                message = commandInput.getUsername() + " is not a host.";
            } else {
                String description = commandInput.getDescription();
                String owner = commandInput.getUsername();
                name = commandInput.getName();

                message = host.addAnnouncement(name, owner, description);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String message;
        String name = admin.getName(commandInput.getUsername());

        if (name == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            Host host = admin.getHost(commandInput.getUsername());

            if (host == null) {
                message = commandInput.getUsername() + " is not a host.";
            } else {
                name = commandInput.getName();
                String username = commandInput.getUsername();
                message = host.removeAnnouncement(name, username);
            }
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show podcasts object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        Host host = admin.getHost(commandInput.getUsername());
        if (host == null) {
            return null;
        }

        List<Podcast> podcasts = admin.getPodcasts();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayNode resultArray = objectMapper.createArrayNode();
        for (Podcast podcast : podcasts) {
            if (podcast.getOwner().equals(commandInput.getUsername())) {
                ObjectNode albumNode = objectMapper.createObjectNode();


                ArrayNode episodesArray = objectMapper.createArrayNode();
                for (Episode episode : podcast.getEpisodes()) {
                    episodesArray.add(episode.getName());
                }
                albumNode.putArray("episodes").addAll(episodesArray);

                albumNode.put("name", podcast.getName());

                resultArray.add(albumNode);
            }
        }

        objectNode.putArray("result").addAll(resultArray);

        return objectNode;
    }
}
