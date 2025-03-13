import java.util.*;

class SongAnalytics {
    private int songCounter = 0;
    private Map<Integer, String> songMap = new HashMap<>(); // song_id -> song_name
    private Map<Integer, Set<Integer>> songListeners = new HashMap<>(); // song_id -> unique listeners

    public int addSong(String name) {
        int songId = ++songCounter;
        songMap.put(songId, name);
        songListeners.put(songId, new HashSet<>());
        return songId;
    }

    public void playSong(int songId, int userId) {
        if (!songMap.containsKey(songId)) {
            System.out.println("Error: Song ID " + songId + " does not exist.");
            return;
        }
        songListeners.get(songId).add(userId);
    }

    public void printAnalytics() {
        List<int[]> analytics = new ArrayList<>();
        for (int songId : songMap.keySet()) {
            analytics.add(new int[]{songId, songListeners.get(songId).size()});
        }

        analytics.sort((a, b) -> {
            if (b[1] != a[1]) {
                return Integer.compare(b[1], a[1]); // Sort by unique listeners descending
            }
            return songMap.get(a[0]).compareTo(songMap.get(b[0])); // Lexicographic order
        });

        for (int[] entry : analytics) {
            System.out.println(songMap.get(entry[0]) + " (" + entry[1] + " unique listeners)");
        }
    }

    public static void main(String[] args) {
        SongAnalytics sa = new SongAnalytics();
        sa.addSong("Song A"); // ID 1
        sa.addSong("Song B"); // ID 2
        sa.addSong("Song C"); // ID 3

        sa.playSong(1, 1);
        sa.playSong(1, 2);
        sa.playSong(2, 1);
        sa.playSong(3, 3);
        sa.playSong(3, 3);

        sa.printAnalytics();
    }
}
