
public class Song {
    String title;
    String artist;
    Song next;
    public static final Song END = new Song();

    public Song(String title, String artist){
        this.title = title;
        this.artist = artist;
        next = END;
    }

    // This is used to construct the END Table.
    private Song() {
        title = "";
        artist = "";
        next = this;
    }

    public boolean equals(Song other) {
        if (this.title.equals(other.title) 
         && this.artist.equals(other.artist))
            {return true;}
        else {return false;}
    }

    public String toString(){
        return "\nTitle: " + title + "\tArtist: " + artist;
    }
}
