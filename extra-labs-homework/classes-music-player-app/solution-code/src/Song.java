/**
 * Created by charlie on 6/27/16.
 */
public class Song {
    private String mSongName, mArtistName, mAlbumName;

    /**
     * Constructor for class Song
     * @param songName String
     * @param artistName String
     * @param albumName String
     */
    public Song(String songName, String artistName, String albumName) {
        mSongName = songName;
        mArtistName = artistName;
        mAlbumName = albumName;
    }

    /**
     * Simulate playback by printing song details to the command line
     */
    public void play() {
        System.out.println("now playing: " + mSongName + " by " + mArtistName + " from " + mAlbumName);
    }


    // BONUS - below are getters and setters for each member variable

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String songName) {
        mSongName = songName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }
}
