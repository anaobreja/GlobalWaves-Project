package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.List;

public class LikedContentPage extends Page {
    private final List<Song> likedSongs;
    private final List<Playlist> followedPlaylists;

    public LikedContentPage(List<Song> likedSongs, List<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    @Override
    public String showPage() {
        StringBuilder songsStringBuilder = new StringBuilder("Liked songs:\n\t[");
        for (Song song : likedSongs) {
            songsStringBuilder.append(song.getName()).append(", ");
        }
        songsStringBuilder.append("]\n\n");

        StringBuilder playlistsStringBuilder = new StringBuilder("Followed playlists:\n\t[");
        for (Playlist playlist : followedPlaylists) {
            playlistsStringBuilder.append(playlist.getName()).append(", ");
        }
        playlistsStringBuilder.append("]");

        return songsStringBuilder.toString() + playlistsStringBuilder.toString();
    }
}