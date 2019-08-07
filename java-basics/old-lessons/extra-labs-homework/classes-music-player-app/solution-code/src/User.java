import java.util.HashMap;
import java.util.Set;

/**
 * Created by charlie on 6/27/16.
 */
public class User {
    private String mName;
    private HashMap<String, Playlist> mPlaylists;

    /**
     * Constructor for class User
     * @param userName String
     */
    public User(String userName) {
        mName = userName;
        mPlaylists = new HashMap<String, Playlist>();
    }

    /**
     * Add a Playlist object to the User's collection of Playlists
     * @param playlist The Playlist object to be added
     */
    public void addPlaylist(Playlist playlist) {
        // Use the playlist's name as the key for the HashMap
        mPlaylists.put(playlist.getName(), playlist);
    }

    /**
     * Get a list of the names of all Playlists added to this User
     * @return a Set of the String values for the name of each Playlist
     */
    public Set<String> getAllPlaylistNames() {
        // return the keys from the HashMap
        return mPlaylists.keySet();
    }

    /**
     * Get the Playlist object corresponding to the name input
     * @param name The name String of the Playlist to be retrieved
     * @return The specified Playlist, or null if no Playlist matches the name input
     */
    public Playlist getPlaylistByName(String name) {
        // note: get() returns null if the HashMap doesn't have an entry with that key
        return mPlaylists.get(name);
    }


    // BONUS - getter and setter methods for mName and removePlaylist method

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    /**
     * Removes the Playlist specified by the key "name" from the User
     * @param name String
     */
    public void removePlaylistByName(String name) {
        mPlaylists.remove(name);
    }
}
