package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.Comparator;
import java.util.List;

public class HomePage extends Page {
    private final List<Song> likedSongs;
    private final List<Playlist> followedPlaylists;

    public HomePage(List<Song> likedSongs, List<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    @Override
    public String showPage() {
        List<Song> songs = getTopLikedSongs(likedSongs);
        List<Playlist> playlists = getTopFollowedPlaylists(followedPlaylists);

        StringBuilder songNames = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            songNames.append(songs.get(i).getName());
            if (i < songs.size() - 1) {
                songNames.append(", ");
            }
        }

        StringBuilder playlistNames = new StringBuilder();
        for (int i = 0; i < playlists.size(); i++) {
            playlistNames.append(playlists.get(i).getName());
            if (i < playlists.size() - 1) {
                playlistNames.append(", ");
            }
        }

        return "Liked songs:\n\t[" + songNames
                + "]\n\nFollowed playlists:\n\t[" + playlistNames + "]";
    }

    private List<Song> getTopLikedSongs(List<Song> likedSongs) {
        likedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return likedSongs.subList(0, Math.min(5, likedSongs.size()));
    }

    private List<Playlist> getTopFollowedPlaylists(List<Playlist> followedPlaylists) {
        followedPlaylists.sort(Comparator.comparingInt(Playlist::getTotalLikes).reversed());
        return followedPlaylists.subList(0, Math.min(5, followedPlaylists.size()));
    }
}
