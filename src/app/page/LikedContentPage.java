package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.List;

public class LikedContentPage extends Page {
    private final List<Song> likedSongs;
    private final List<Playlist> followedPlaylists;

    /**
     * Constructs a HomePage object with liked songs and followed playlists.
     *
     * @param likedSongs        The list of liked songs.
     * @param followedPlaylists The list of followed playlists.
     */
    public LikedContentPage(final List<Song> likedSongs,
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
        StringBuilder songsStringBuilder = new StringBuilder("Liked songs:\n\t");

        if (likedSongs.isEmpty()) {
            songsStringBuilder.append("[]\n\n");
        } else {
            songsStringBuilder.append("[");
            for (int i = 0; i < likedSongs.size(); i++) {
                Song song = likedSongs.get(i);
                songsStringBuilder.append(song.getName());
                songsStringBuilder.append(" - ");
                songsStringBuilder.append(song.getArtist());
                if (i < likedSongs.size() - 1) {
                    songsStringBuilder.append(", ");
                }
            }
            songsStringBuilder.append("]\n\n");
        }

        StringBuilder playlistsStringBuilder = new StringBuilder("Followed playlists:\n\t");
        if (followedPlaylists.isEmpty()) {
            playlistsStringBuilder.append("[]");
        } else {
            playlistsStringBuilder.append("[");
            for (int i = 0; i < followedPlaylists.size(); i++) {
                Playlist playlist = followedPlaylists.get(i);
                playlistsStringBuilder.append(playlist.getName());
                playlistsStringBuilder.append(" - ");
                playlistsStringBuilder.append(playlist.getOwner());
                if (i < followedPlaylists.size() - 1) {
                    playlistsStringBuilder.append(", ");
                }
            }
            playlistsStringBuilder.append("]");
        }

        return songsStringBuilder + playlistsStringBuilder.toString();
    }

}
