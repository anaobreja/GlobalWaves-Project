package app.page;

import app.audio.Collections.Podcast;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class HostPage extends Page {

    @Getter
    @Setter
    private ArrayList<Podcast> podcasts;

    public HostPage (ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
    @Override
    public String showPage() {
        return null;
    }
}
