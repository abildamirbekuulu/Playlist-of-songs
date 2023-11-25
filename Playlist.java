import java.util.*;

public class Playlist {

    String name;
    Song first;
    private Song pos, pre;
    
    public Playlist(){
        name = "library";
        first = Song.END;
    }

    public Playlist(String name){
        this.name = name;  
        first = Song.END;
    }

    public String getName(){
        return name;
    }
    
     // for to add a song, by making it last one in the list.
    public void add(Song song){
      String ttl = song.title;
      String arts = song.artist;
      Song nws = new Song(ttl, arts);
      pos = first;
      if(first != Song.END){   
         while(pos.next != Song.END){
            pos = pos.next;
         }
         nws.next = pos.next;
         pos.next = nws;
      }
      else{
         first = nws;
      }
    }
      
    // to calculate the amount of songs in the list.
    public int size() {
       int cnt = 0;
       pos = first;
       while(true){
         if(pos == Song.END){
            break;
         } 
         pos = pos.next;
         cnt++;
       }
       return cnt;
    }
    
    // to remove first song in the list, returns the removed song.
    public Song removeFirst() {
      if(Song.END != first){
         pos = first;
         first = null;
         first = pos.next;
         return pos;
      }
      else{
         return first;
      }  
    }
         
    // to find a song in the list and returns its position in the list.
    public int remove(Song song) {
       int ans = -1;
       ans = getPosition(song);
       boolean ans2 = first.equals(song);
       pos = first;
       if(ans != -1){
         while(pos.equals(song) != true){
            pre = pos;
            pos = pos.next;
         }
         pre.next = pos.next;
       }
       else
       if(ans2 == true){
         pos = first;
         first = null;
         first = pos.next;
       }
       return ans;      
    }
    
    // to get first song in the list
    public Song head() {
       return first;
    }
    
    // to find songs positioins in the list.
    public int getPosition(Song song) {
       int ind = 0, res = -1;
       boolean ans = first.equals(song);
       pos = first;
       if(ans == true){
         res = 0;
       }
       while(pos.next != Song.END){
         pos = pos.next;
         boolean check = pos.equals(song);
         ind++;
         if(check == true) {
            res = ind;
            break;
         }
       }
       return res;
    }
   
    // to get all songs in the list by using toString() method which is written in the Song class.
    public String listSongs() {
       String ans = "";
       pos = first;
       while(pos != Song.END){
         ans = ans + pos.toString();
         pos = pos.next;
       }
       return ans;
    }
}
