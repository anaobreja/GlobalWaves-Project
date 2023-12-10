package fileio.input;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class FiltersInput {
    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear; // pentru search song/episode -> releaseYear
    private String artist;
    private String owner; // pentru search playlist si podcast
    private String followers; // pentru search playlist -> followers
    private String description;
    private String date;
    public FiltersInput() {
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAlbum(final String album) {
        this.album = album;
    }

    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public void setFollowers(final String followers) {
        this.followers = followers;
    }

    public void setDescription(final String description) { this.description = description; }

    public void setDate(final String date) { this.date = date; }

    @Override
    public String toString() {
        return "FilterInput{"
                + ", name='" + name + '\''
                + ", album='" + album + '\''
                + ", tags=" + tags
                + ", lyrics='" + lyrics + '\''
                + ", genre='" + genre + '\''
                + ", releaseYear='" + releaseYear + '\''
                + ", artist='" + artist + '\''
                + ", owner='" + owner + '\''
                + ", followers='" + followers + '\''
                + '}';
    }
}
