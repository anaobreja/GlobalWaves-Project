package app.user;

import app.Admin;
import app.audio.Collections.Announcement;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.page.HostPage;
import app.player.PlayerSource;
import fileio.input.EpisodeInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Host extends User {
    @Getter
    @Setter
    private HostPage hostPage;

    public Host(String username, int age, String city, String userType) {
        super(username, age, city, userType);
        this.hostPage = new HostPage(new ArrayList<>(), new ArrayList<>());
    }


    private static Episode converseEpisode(EpisodeInput episodeInput) {
        String name = episodeInput.getName();
        Integer duration = episodeInput.getDuration();
        String description = episodeInput.getDescription();

        return new Episode(name, duration, description);
    }

    public String addPodcast(String name, String owner, ArrayList<EpisodeInput> episodes,
                             int timestamp) {

        ArrayList<Episode> episodeResult = new ArrayList<>();

        for (EpisodeInput episodeInput : episodes) {
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


        return owner + " has added new podcast successfully.";
    }

    public String removePodcast(String name, String username) {
        for (NormalUser userAux : Admin.getNormalUsers()) {
            PlayerSource source = userAux.getPlayer().getSource();

            if (source != null) {
                AudioCollection audioCollection = source.getAudioCollection();

                if ((audioCollection != null && audioCollection.getName() != null
                        && audioCollection.getName().equals(name))) {
                    return username + " can't delete this podcast.";
                }
            }
        }

        boolean hasPodcast = false;
        for (Podcast podcastAux : hostPage.getPodcasts()) {
            if (podcastAux.getName().equals(name)) {
                hasPodcast = true;
                break;
            }
        }

        if (!hasPodcast) {
            return username + " doesn't have a podcast with the given name.";
        }

        Admin.getPodcasts().removeIf(podcast -> podcast.getName().equals(name));

        Host user = Admin.getHosts(username);

        assert user != null;
        user.getHostPage().getPodcasts().removeIf(podcast -> podcast.getName().equals(name));

        return username + " deleted the podcast successfully.";
    }

    public String addAnnouncement(String name, String owner, String description) {


        Announcement announcement = new Announcement(name, owner, description);

        for (Announcement announcement1 : hostPage.getAnnouncements()) {
            if (announcement1.getName().equals(announcement.getName()))
                return owner + " has another announcement with the same name.";
        }

        hostPage.getAnnouncements().add(announcement);


        return owner + " has successfully added new announcement.";
    }

    public String revomeAnnouncement(String name, String owner) {
        Announcement announcement = null;

        for (Announcement announcement1 : hostPage.getAnnouncements()) {
            if (announcement1.getName().equals(name))
                announcement = announcement1;
        }

        if (announcement == null)
            return owner + " has no announcement with the given name.";

        hostPage.getAnnouncements().remove(announcement);


        return owner + " has successfully deleted the announcement.";
    }

}
