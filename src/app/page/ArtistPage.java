package app.page;

import app.audio.Collections.Album;
import app.audio.Collections.Merch;
import app.utils.EventValidator;
import com.fasterxml.jackson.core.io.MergedStream;
import lombok.Getter;
import lombok.Setter;
import app.audio.Collections.Event;

import java.util.ArrayList;

@Getter
@Setter
public class ArtistPage extends Page {
    @Getter
    @Setter
    private ArrayList<Album> albums;
    @Getter
    @Setter
    private ArrayList<Event> events;
    @Getter
    @Setter
    private ArrayList<Merch> merches;

    public ArtistPage(ArrayList<Album> albums, ArrayList<Event> events,
                      ArrayList<Merch> merches) {
        this.albums = albums;
        this.events = events;
        this.merches = merches;
    }

    @Override
    public String showPage() {
        StringBuilder page = new StringBuilder();
        page.append("Albums:\n");
        albums.forEach(album -> page.append("\t[").append(album.getName()).append("]\n"));

        page.append("\nMerch:\n");
        if (merches.isEmpty()) {
            page.append("\t[]\n");
        } else {
            page.append("\t[");
            for (int i = 0; i < merches.size(); i++) {
                Merch merch = merches.get(i);
                page.append(String.format("%s - %d:\n\t%s", merch.getName(), merch.getPrice(), merch.getDescription()));
                if (i < merches.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]\n");
        }

        page.append("\nEvents:\n");
        if (events.isEmpty()) {
            page.append("\t[]\n");
        } else {
            page.append("\t[");
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                page.append(String.format("%s - %s:\n\t%s", event.getName(), event.getDate(), event.getDescription()));
                if (i < events.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]");
        }

        return page.toString();
    }



}
