package app.page;

import app.audio.Collections.Album;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ArtistPage extends Page {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merchandise;

    /**
     * Constructs an ArtistPage object with albums, events, and merchandise.
     *
     * @param albums   The list of albums.
     * @param events   The list of events.
     * @param merchandise  The list of merchandise.
     */
    public ArtistPage(final ArrayList<Album> albums, final ArrayList<Event> events,
                      final ArrayList<Merch> merchandise) {
        this.albums = albums;
        this.events = events;
        this.merchandise = merchandise;
    }

    /**
     * Generates a textual representation of the artist's page.
     *
     * @return A formatted string displaying albums, merchandise, and events.
     */
    @Override
    public String showPage() {
        StringBuilder page = new StringBuilder();

        page.append("Albums:\n");

        if (albums.isEmpty()) {
            page.append("\t[]\n");
        } else {
            page.append("\t[");
            for (int i = 0; i < albums.size(); i++) {
                Album album = albums.get(i);
                page.append(album.getName());
                if (i < albums.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]\n");
        }

        page.append("\nMerch:\n");
        if (merchandise.isEmpty()) {
            page.append("\t[]\n");
        } else {
            page.append("\t[");
            for (int i = 0; i < merchandise.size(); i++) {
                Merch merch = merchandise.get(i);
                page.append(String.format("%s - %d:\n\t%s", merch.getName(),
                        merch.getPrice(), merch.getDescription()));
                if (i < merchandise.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]\n");
        }

        page.append("\nEvents:\n");
        if (events.isEmpty()) {
            page.append("\t[]");
        } else {
            page.append("\t[");
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                page.append(String.format("%s - %s:\n\t%s", event.getName(),
                        event.getDate(), event.getDescription()));
                if (i < events.size() - 1) {
                    page.append(", ");
                }
            }
            page.append("]");
        }

        return page.toString();
    }


}
