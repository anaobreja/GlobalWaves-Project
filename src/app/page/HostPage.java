package app.page;

import app.audio.Collections.Announcement;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HostPage extends Page {
    private final ArrayList<Podcast> podcasts;
    private final ArrayList<Announcement> announcements;

    /**
     * @param podcasts the podcasts
     * @param announcements the announcements
     */
    public HostPage(final ArrayList<Podcast> podcasts,
                    final ArrayList<Announcement> announcements) {
        this.podcasts = podcasts;
        this.announcements = announcements;
    }

    /**
     * @return A formatted string displaying podcasts and announcements.
     */
    @Override
    public String showPage() {
        StringBuilder page = new StringBuilder();

        page.append("Podcasts:\n");
        if (podcasts.isEmpty()) {
            page.append("\t[]\n");
        } else {
            page.append("\t[");
            for (int i = 0; i < podcasts.size(); i++) {
                Podcast podcast = podcasts.get(i);
                page.append(podcast.getName()).append(":\n\t[");
                List<Episode> episodes = podcast.getEpisodes();
                for (int j = 0; j < episodes.size(); j++) {
                    Episode episode = episodes.get(j);
                    page.append(episode.getName()).
                            append(" - ").append(episode.getDescription());
                    if (j < episodes.size() - 1) {
                        page.append(", ");
                    }
                }
                page.append("]\n");
                if (i < podcasts.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]\n");
        }

        page.append("\nAnnouncements:\n");
        if (announcements.isEmpty()) {
            page.append("\t[]");
        } else {
            page.append("\t[");
            for (int i = 0; i < announcements.size(); i++) {
                Announcement announcement = announcements.get(i);
                page.append(announcement.getName()).
                        append(":\n\t").append(announcement.getDescription());
                if (i < announcements.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("\n]");
        }

        return page.toString();
    }

}
