import java.util.ArrayList;

/**
 * Created by charlie on 6/27/16.
 */
public class Playlist {
    private String mName;
    private ArrayList<Song> mSongs;

    /**
     * Constructor for class Playlist
     * @param playlistName The String value to be used as the Playlist's name
     */
    public Playlist(String playlistName) {
        mName = playlistName;
        mSongs = new ArrayList<Song>();
    }

    /**
     * Retrieve the name of the playlist
     * @return a String value representing the Playlist's name
     */
    public String getName() {
        return mName;
    }

    /**
     * Add a song to the playlist
     * @param song The Song object to be added
     */
    public void addSong(Song song) {
        mSongs.add(song);
    }

    /**
     * Call the play() method on each Song object in the Playlist
     */
    public void playAll() {
        // simplest approach is to use a for-each loop
        for (Song song : mSongs) {
            song.play();
        }
    }


    // BONUS - setter method for mName
    public void setName(String name) {
        mName = name;
    }


}
