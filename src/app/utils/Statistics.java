package app.utils;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Statistics {

    private static final int TOP_LIMIT = 5;

    private Statistics() {
    }

    /**
     * @param likedSongsInput
     * @return
     */
    public static List<Song> getTopLikedSongs(final List<Song> likedSongsInput) {
        List<Song> likedSongs = new ArrayList<>(likedSongsInput);
        likedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return likedSongs.subList(0, Math.min(TOP_LIMIT, likedSongs.size()));
    }

    /**
     * @param followedPlaylistsInput
     * @return
     */
    public static List<Playlist>
    getTopFollowedPlaylists(final List<Playlist> followedPlaylistsInput) {
        List<Playlist> followedPlaylists = new ArrayList<>(followedPlaylistsInput);
        followedPlaylists.sort(Comparator.comparingInt(Playlist::getTotalLikes).reversed());
        return followedPlaylists.subList(0, Math.min(TOP_LIMIT, followedPlaylists.size()));
    }

    /**
     * @param album
     * @return
     */
    public static Integer getAlbumTotalLikes(final Album album) {
        Integer likes = 0;
        for (Song song : album.getSongs()) {
            likes += song.getLikes();
        }
        return likes;
    }

    /**
     * @param albumsInput
     * @return
     */
    public static List<String> getTopLikedAlbums(final List<Album> albumsInput) {
        albumsInput.sort(Comparator.comparing(Album::getName));
        List<Album> albums = albumsInput.stream().sorted(Comparator.
                comparingInt(Statistics::getAlbumTotalLikes)).collect(Collectors.toList());

        albums = albums.stream().sorted(Comparator.comparingInt(album ->
                -getAlbumTotalLikes(album))).collect(Collectors.toList());

        List<String> names = new ArrayList<>();
        for (Album album : albums) {
            names.add(album.getName());
        }
        return names.subList(0, Math.min(TOP_LIMIT, names.size()));
    }

    /**
     * @param albumsInput
     * @return
     */
    public static Map<String, Integer> getArtistTotalLikes(final List<Album> albumsInput) {
        return albumsInput.stream()
                .flatMap(album -> album.getSongs().stream())
                .collect(Collectors.groupingBy(
                        Song::getArtist,
                        Collectors.summingInt(Song::getLikes)
                ));
    }

    /**
     * @param albumsInput
     * @return
     */
    public static List<String> getTop5Artists(final List<Album> albumsInput) {
        Map<String, Integer> artistLikes = getArtistTotalLikes(albumsInput);

        List<String> topArtists = artistLikes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(TOP_LIMIT)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topArtists;
    }

}
