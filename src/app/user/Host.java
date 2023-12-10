package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.page.ArtistPage;
import app.page.HostPage;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Host extends User {
    @Getter
    @Setter
    private HostPage hostPage;
    public Host(String username, int age, String city, String userType) {
        super(username, age, city, userType);
        this.hostPage = new HostPage(new ArrayList<>());
    }

    private static Episode converseEpisode (EpisodeInput episodeInput) {
        String name = episodeInput.getName();
        Integer duration = episodeInput.getDuration();
        String description = episodeInput.getDescription();

        return new Episode(name, duration, description);
    }

    public String addPodcast(String name, String owner, ArrayList<EpisodeInput> episodes,
                           int timestamp) {
        ArrayList<Song> songsResult = new ArrayList<>();

        ArrayList<Episode> episodeResult = new ArrayList<>();

        for(EpisodeInput episodeInput : episodes) {
            Episode episode = converseEpisode(episodeInput);
            episodeResult.add(episode);
        }


        Podcast podcast = new Podcast(name, owner, episodeResult);

        for (Podcast podcast1 : Admin.getPodcasts()) {
            if (podcast1.getName().equals(podcast.getName()))
                return owner + " has another podcast with the same name.";
        }

//        Set<String> songNames = new HashSet<>();
//        for (Song song : album.getSongs()) {
//            if (!songNames.add(song.getName())) {
//                return owner + " has the same song at least twice in this album.";
//            }
//        }

        Admin.getPodcasts().add(podcast);
        hostPage.getPodcasts().add(podcast);
//
//        for (Song song : songsResult) {
//            Admin.getSongs().add(song);
//        }


        return owner + " has added new podcast successfully.";
    }
}
