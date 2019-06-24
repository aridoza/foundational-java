/**
 * Created by charlie on 6/27/16.
 */
public class Main {
    public static void main(String[] args) {

        // create a new user
        User user = new User("Bob");

        // create some new songs
        Song song1 = new Song("song 1", "artist 1", "album 1");
        Song song2 = new Song("song 2", "artist 1", "album 1");
        Song song3 = new Song("song 3", "artist 2", "album 2");
        Song song4 = new Song("song 4", "artist 3", "album 3");
        Song song5 = new Song("song 5", "artist 3", "album 4");

        // create playlist 1, populate it with songs, and add it to user
        Playlist playlist1 = new Playlist("playlist 1");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        playlist1.addSong(song4);
        user.addPlaylist(playlist1);

        // create playlist 2, populate it with songs, and add it to user
        Playlist playlist2 = new Playlist("playlist 2");
        playlist2.addSong(song5);
        playlist2.addSong(song1);
        playlist2.addSong(song3);
        user.addPlaylist(playlist2);

        // print the names of all the user's playlists
        System.out.println("playlists: " + user.getAllPlaylistNames());

        // "play" all the songs in each of the user's playlists
        for (String playlistName : user.getAllPlaylistNames()) {
            System.out.println("playing: " + playlistName);

            // getPlaylistByName() returns a Playlist; call the playAll() method on that Playlist
            user.getPlaylistByName(playlistName).playAll();
        }


        // BONUS - alter some names and print to see if changes were successful
        user.setName("Jim");
        System.out.println("new user: " + user.getName());

        song1.setSongName("song 111");
        song2.setArtistName("artist 123");
        song4.setAlbumName("album 456");

        // remove playlist2 from the User, change its name, then re-add with new name
        user.removePlaylistByName(playlist2.getName());
        playlist2.setName("playlist B");
        user.addPlaylist(playlist2);

        // print everything out again
        for (String playlistName : user.getAllPlaylistNames()) {
            System.out.println("playing: " + playlistName);
            user.getPlaylistByName(playlistName).playAll();
        }
    }
}
