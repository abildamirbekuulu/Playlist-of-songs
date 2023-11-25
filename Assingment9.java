

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment9 {

    private static Song parseSong(String[] input) {
        String title = "";
        int i = 0;
        for (; i < input.length; i++) {
            if (input[i+1].equals("by")) {
                title += input[i];
                continue;
            } else if (input[i].equals("by")) {
                i++;
                break;
            }
            title += input[i] + " ";
        }
        String artist = "";
        for (; i < input.length-1; i++) {
            artist += input[i] + " ";
        }
        artist += input[input.length-1];
        Song song = new Song(title, artist);
        return song;
    }

    private static Playlist findPlaylist(String name, 
            ArrayList<Playlist> playlists) {
        // A hashmap would be handy here...
        // but for simplicity we will refrain from using it.
        for (Playlist p : playlists) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        // No playlist found.
        return null;
    }

    public static void main(String[] args) {
        
        // Probably makes more sense to use a hashmap, but let's not complica-
        // e things more than needed.
        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist());

        // Keep track of the current playlist.
        Playlist currentPlaylist = playlists.get(0);

        // Print out the menu
        System.out.println("Welcome to AStUnes!");
        System.out.println("===================");
        printMenu();

        // Create a BufferedReader object to read input from a keyboard
        Scanner scanner = new Scanner(System.in);
        String line = "";
        do {
            line = scanner.nextLine();
            String[] input = line.split(" ");
            Song song = null;
            switch (input[0]) {
                case "add":   // Add to current playlist
                    song = parseSong(
                            Arrays.copyOfRange(input, 1, input.length));
                    currentPlaylist.add(song);
                    System.out.println("Successfully added Title: " + song.title + "    Artist: " + song.artist);
                    break;
                case "new":
                    Playlist playlist = new Playlist(input[1]);
                    playlists.add(playlist);
                    System.out.printf("Creating playlist %s\n", input[1]);
                    currentPlaylist = playlist;
                    System.out.printf("Switched to %s\n", input[1]);
                    break;
                case "count":
                    System.out.printf(
                            "The current playlist %s has %d songs.\n", 
                            currentPlaylist.getName(), currentPlaylist.size()
                    );
                    break;
                case "position":
                    song = parseSong(
                            Arrays.copyOfRange(input, 1, input.length));
                    int result = currentPlaylist.getPosition(song);
                    if(result == -1) {
                        System.out.printf("%s is not in the playlist.\n", song);
                    }
                    else {
                        System.out.println("Title: " + song.title + "    Artist: " + song.artist + " song is located at position " + (result+1) + " in " + currentPlaylist.getName()); 
                      
                    }
                    break;
                case "remove":
                    song = parseSong(
                        Arrays.copyOfRange(input, 1, input.length)
                    );
                    int index = currentPlaylist.remove(song);
                    if(index == -1) {
                        System.out.println("Title: " + song.title + "    Artist: " + song.artist + " is not in the playlist.");
                    } else {
                        System.out.println("Removed Title: " + song.title + "    Artist: " + song.artist + " (at position " + (index+1) + ") from " + currentPlaylist.getName() + ".");
                    }
                    break;
                case "switch":
                    Playlist prev = currentPlaylist;
                    currentPlaylist = findPlaylist(input[1], playlists);
                    System.out.printf(
                        "Switched from playist %s to playlist %s",
                        prev.getName(), currentPlaylist.getName()
                    );
                    break;
                case "pop":
                    // Remove current song, switch to next song in playlist.
                    if (currentPlaylist.head() == Song.END) {
                        System.out.printf(
                            "No songs currently in playlist %s.\n", 
                            currentPlaylist.getName()
                        );
                        break;
                    }
                    Song current = currentPlaylist.removeFirst();
                    if (currentPlaylist.head() == Song.END) {
                      System.out.println("Finished Title: " + current.title + "    Artist: " + current.artist + ".");
                      System.out.println("No songs currently in playlist library.");
                    }
                    else {
                        System.out.println("Finished:Title: " + current.title + "    Artist: " + current.artist + "."); 
                        System.out.println("Now plaing:Title: " + currentPlaylist.first.title + "    Artist: " + currentPlaylist.first.artist);
                        
                    }
                    break;
                case "songs":
                    System.out.println(currentPlaylist.listSongs());
                    System.out.println();
                    System.out.println("Total songs: " + currentPlaylist.size() +".");
                    break;
                case "playlists":
                    if (playlists.size() == 0) {
                        System.out.println("No playlists found.");
                    } else {
                        for (Playlist p : playlists) {
                            System.out.println(p.getName());
                        }
                    }
                    break;
                case "help":
                    printMenu();
                    break;
                case "quit": // Need to prevent default form executing.
                    break;
                default:
                    System.out.print("Unknown action\n");
                    break;
            }
        } while(!line.equals("quit"));
    }
    private static void printMenu() {
        System.out.print("Choice\t\tAction\n" +
            "------\t\t------\n" +
            "add\t\tAdds a song to the current playlist\n" +
            "count\t\tCounts the number of songs in a playlist\n" +
            "position\tGets the position of a song in a playlist\n" +
            "remove\t\tRemove a song from the playlist\n" +
            "new\t\t\tCreate a new playlist\n" +
            "switch\t\tSwitch to another playlist\n" +
            "pop\t\t\tFinish and remove the first song from the playlist\n" +
            "songs\t\tList the songs in the current playlist\n" +
            "playlists\tList the playlists\n"  +
            "stack\t\tStack a playlist onto another\n" +
            "interleave\tInterleave two playlists\n" +
            "quit\t\tQuit the program\n" +
            "help\t\tDisplay Help\n\n");
    }
}
