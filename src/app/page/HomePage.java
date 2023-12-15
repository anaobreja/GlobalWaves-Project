package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.utils.Statistics;

import java.util.List;

public class HomePage extends Page {

    private final List<Song> likedSongs;
    private final List<Playlist> followedPlaylists;

    /**
     * Constructs a HomePage object with liked songs and followed playlists.
     *
     * @param likedSongs        The list of liked songs.
     * @param followedPlaylists The list of followed playlists.
     */
    public HomePage(final List<Song> likedSongs,
                    final List<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * Generates a formatted representation of the user's home page.
     *
     * @return A formatted string displaying liked songs and followed playlists.
     */
    @Override
    public String showPage() {
        Statistics statistics = Statistics.getInstance();
        List<Song> songs = statistics.getTopLikedSongs(likedSongs);
        List<Playlist> playlists = statistics.getTopFollowedPlaylists(followedPlaylists);

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
