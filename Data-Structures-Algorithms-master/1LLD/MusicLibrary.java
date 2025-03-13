import java.util.*;

public class MusicLibrary {
    private Map<String, Set<String>> songToUsers; // Track unique user plays
    private LinkedHashSet<String> recentSongs; // Track unique recently played songs
    private int recentCapacity;

    public MusicLibrary(int recentCapacity) {
        this.songToUsers = new HashMap<>();
        this.recentSongs = new LinkedHashSet<>();
        this.recentCapacity = recentCapacity;
    }

    // Function to return most played songs by unique users
    // priority queue
    public List<String> getMostPlayedSongs(int k) {
        PriorityQueue<Map.Entry<String, Set<String>>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue().size() - a.getValue().size());

        maxHeap.addAll(songToUsers.entrySet());

        List<String> result = new ArrayList<>();
        while (!maxHeap.isEmpty() && k-- > 0) {
            result.add(maxHeap.poll().getKey());
        }
        return result;
    }

    // Function to record a song play
    // unique - if exist pop and push to move it to end
    public void playSong(String userId, String songId) {
        songToUsers.putIfAbsent(songId, new HashSet<>());
        songToUsers.get(songId).add(userId);

        // Maintain order & uniqueness for recent plays
        if (recentSongs.contains(songId)) {
            recentSongs.remove(songId); // Move it to the end
        }
        recentSongs.add(songId);

        // Ensure we only keep the last 'recentCapacity' entries
        if (recentSongs.size() > recentCapacity) {
            Iterator<String> it = recentSongs.iterator();
            it.next();
            it.remove();
        }
    }


    // Function to return the last 3 unique recently played songs
    public List<String> getRecentSongs() {
        return new ArrayList<>(recentSongs);
    }

    public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary(3);

        library.playSong("user1", "songA");
        library.playSong("user2", "songA");
        library.playSong("user3", "songB");
        library.playSong("user1", "songC");
        library.playSong("user2", "songD");
        library.playSong("user3", "songE");

        System.out.println("Most Played Songs: " + library.getMostPlayedSongs(2));
        System.out.println("Recent Songs: " + library.getRecentSongs());
    }
}
