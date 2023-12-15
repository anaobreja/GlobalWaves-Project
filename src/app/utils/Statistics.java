package app.utils;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Statistics {
    private static Statistics instance = null;

    private static final int TOP_LIMIT = 5;

    private Statistics() {
    }

    /**
     * Returns the instance of the Statistics singleton class.
     *
     * @return The instance of the Statistics class.
     */

    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
        }
        return instance;
    }


    /**
     * Gets the top liked songs from the given list of songs.
     *
     * @param likedSongsInput the list of liked songs
     * @return a list containing the top liked songs
     */
    public List<Song> getTopLikedSongs(final List<Song> likedSongsInput) {
        List<Song> likedSongs = new ArrayList<>(likedSongsInput);
        likedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return likedSongs.subList(0, Math.min(TOP_LIMIT, likedSongs.size()));
    }

    /**
     * Gets the top followed playlists from the given list of playlists.
     *
     * @param followedPlaylistsInput the list of followed playlists
     * @return a list containing the top followed playlists
     */
    public List<Playlist>
    getTopFollowedPlaylists(final List<Playlist> followedPlaylistsInput) {
        List<Playlist> followedPlaylists = new ArrayList<>(followedPlaylistsInput);
        followedPlaylists.sort(Comparator.comparingInt(Playlist::getTotalLikes).reversed());
        return followedPlaylists.subList(0, Math.min(TOP_LIMIT, followedPlaylists.size()));
    }

    /**
     * Calculates the total number of likes for an album based on its songs.
     *
     * @param album the album to calculate total likes for
     * @return the total number of likes for the album
     */
    public Integer getAlbumTotalLikes(final Album album) {
        Integer likes = 0;
        for (Song song : album.getSongs()) {
            likes += song.getLikes();
        }
        return likes;
    }

    /**
     * Gets the names of the top liked albums from the given list of albums.
     *
     * @param albumsInput the list of albums
     * @return a list containing the names of the top liked albums
     */
    public List<String> getTopLikedAlbums(final List<Album> albumsInput) {
        Statistics statistics = Statistics.getInstance();
        albumsInput.sort(Comparator.comparing(Album::getName));
        List<Album> albums = albumsInput.stream().sorted(Comparator.
                comparingInt(statistics::getAlbumTotalLikes)).collect(Collectors.toList());

        albums = albums.stream().sorted(Comparator.comparingInt(album ->
                -getAlbumTotalLikes(album))).collect(Collectors.toList());

        List<String> names = new ArrayList<>();
        for (Album album : albums) {
            names.add(album.getName());
        }
        return names.subList(0, Math.min(TOP_LIMIT, names.size()));
    }

    /**
     * Calculates the total number of likes for each artist from the given list of albums.
     *
     * @param albumsInput the list of albums
     * @return a map containing each artist and their total likes
     */
    public Map<String, Integer> getArtistTotalLikes(final List<Album> albumsInput) {
        return albumsInput.stream()
                .flatMap(album -> album.getSongs().stream())
                .collect(Collectors.groupingBy(
                        Song::getArtist,
                        Collectors.summingInt(Song::getLikes)
                ));
    }

    /**
     * Gets the top 5 artists based on their total likes from the given list of albums.
     *
     * @param albumsInput the list of albums
     * @return a list containing the names of the top 5 artists
     */
    public List<String> getTop5Artists(final List<Album> albumsInput) {
        Map<String, Integer> artistLikes = getArtistTotalLikes(albumsInput);

        List<String> topArtists = artistLikes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(TOP_LIMIT)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topArtists;
    }

}
