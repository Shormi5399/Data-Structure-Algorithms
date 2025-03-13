import java.util.*;

class MusicPlayer {
    private int songCounter;
    private Map<Integer, String> songs;
    private Map<Integer, Set<Integer>> playCount;
    private Map<Integer, LinkedList<Integer>> userHistory;

    // Constructor
    public MusicPlayer() {
        this.songCounter = 0;
        this.songs = new HashMap<>();
        this.playCount = new HashMap<>();
        this.userHistory = new HashMap<>();
    }

    // Add a song with an incremental ID
    public int addSong(String songTitle) {
        int songId = ++songCounter;
        songs.put(songId, songTitle);
        playCount.put(songId, new HashSet<>());
        return songId;
    }

    // Play a song and track unique user plays
    public void playSong(int songId, int userId) {
        if (!songs.containsKey(songId)) {
            System.out.println("Song ID " + songId + " does not exist.");
            return;
        }
        playCount.get(songId).add(userId);

        userHistory.putIfAbsent(userId, new LinkedList<>());
        LinkedList<Integer> history = userHistory.get(userId);

        // Remove previous occurrence of song to maintain uniqueness
        history.remove((Integer) songId);
        history.addFirst(songId);

        // Keep only last 3 unique songs
        if (history.size() > 3) {
            history.removeLast();
        }
    }

    // Print songs sorted by most unique users played
    public void printMostPlayedSongs() {
        List<Map.Entry<Integer, Set<Integer>>> songList = new ArrayList<>(playCount.entrySet());

        songList.sort((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()));

        for (Map.Entry<Integer, Set<Integer>> entry : songList) {
            System.out.println(songs.get(entry.getKey()) + " -> " + entry.getValue().size() + " unique plays");
        }
    }

    // Get last 3 unique songs played by a user
    public List<Integer> getLastThreeSongs(int userId) {
        return userHistory.getOrDefault(userId, new LinkedList<>());
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        int song1 = player.addSong("Shape of You");
        int song2 = player.addSong("Blinding Lights");
        int song3 = player.addSong("Bad Habits");
        int song4 = player.addSong("Uptown Funk");

        player.playSong(song1, 1);
        player.playSong(song2, 1);
        player.playSong(song1, 2);
        player.playSong(song3, 1);
        player.playSong(song3, 2);
        player.playSong(song4, 3);
        player.playSong(song1, 3);
        player.playSong(song2, 2);

        System.out.println("Most Played Songs:");
        player.printMostPlayedSongs();

        System.out.println("Last 3 Songs played by User 1: " + player.getLastThreeSongs(1));
    }
}