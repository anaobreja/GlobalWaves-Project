package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.utils.Statistics;

import java.util.List;

public class HomePage extends Page {

    private final List<Song> likedSongs;
    private final List<Playlist> followedPlaylists;

    /**
     * @param likedSongs
     * @param followedPlaylists
     */
    public HomePage(final List<Song> likedSongs,
                    final List<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * @return
     */
    @Override
    public String showPage() {
        List<Song> songs = Statistics.getTopLikedSongs(likedSongs);
        List<Playlist> playlists = Statistics.getTopFollowedPlaylists(followedPlaylists);

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
}
