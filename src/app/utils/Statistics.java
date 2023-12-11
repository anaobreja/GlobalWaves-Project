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

    private Statistics() {}
    public static List<Song> getTopLikedSongs(List<Song> likedSongs) {
        likedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return likedSongs.subList(0, Math.min(5, likedSongs.size()));
    }

    public static List<Playlist> getTopFollowedPlaylists(List<Playlist> followedPlaylists) {
        followedPlaylists.sort(Comparator.comparingInt(Playlist::getTotalLikes).reversed());
        return followedPlaylists.subList(0, Math.min(5, followedPlaylists.size()));
    }

    public static Integer getAlbumTotalLikes(Album album) {
        Integer likes = 0;
        for (Song song : album.getSongs())
            likes += song.getLikes();
        return likes;
    }

    public static List<String> getTopLikedAlbums(List<Album> albumsInput) {

        List<Album> albums = albumsInput.stream().sorted(Comparator.
                comparingInt(Statistics::getAlbumTotalLikes)).collect(Collectors.toList());

        albums = albums.stream().sorted(Comparator.comparingInt(album ->
                -getAlbumTotalLikes(album))).collect(Collectors.toList());

        List<String> names = new ArrayList<>();
        for (Album album : albums)
            names.add(album.getName());
        return names.subList(0, Math.min(5, names.size()));
    }

    public static Map<String, Integer> getArtistTotalLikes(List<Album> albumsInput) {
        return albumsInput.stream()
                .flatMap(album -> album.getSongs().stream())
                .collect(Collectors.groupingBy(
                        Song::getArtist,
                        Collectors.summingInt(Song::getLikes)
                ));
    }

    public static List<String> getTop5Artists(List<Album> albumsInput) {
        Map<String, Integer> artistLikes = getArtistTotalLikes(albumsInput);

        List<String> topArtists = artistLikes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topArtists;
    }

}
