import java.util.*;

class SongAnalytics_1{
    private int songIdCounter = 0;
    private final Map<Integer, String> songMap = new HashMap<>();
    private final Map<Integer, Set<Integer>> songListener = new HashMap<>();


    public int addSong(String name){
        int songId = ++songIdCounter;

        songMap.put(songId, name);
        songListener.put(songId, new HashSet<>());

        return songId;
    }

    public void playSong(int songId, int userId){
        if(!songListener.containsKey(songId)) {
            System.out.println("Error: Song ID " + songId + " does not exist.");
            return;
        }

        songListener.get(songId).add(userId);
        }

        public void printAnalytics() {
            List<int[]> analytics = new ArrayList<>();

            for (int songId : songMap.keySet()) {
                analytics.add(new int[]{songId, songListener.get(songId).size()});
            }

            analytics.sort((a, b) -> {
                if (b[1] != a[1])
                    return Integer.compare(b[1], a[1]);
                else
                    return songMap.get(a[0]).compareTo(songMap.get(b[0]));
            });

            for (int[] entry : analytics) {
                System.out.println(songMap.get(entry[0]) + " (" + entry[1] + " unique listeners)");
            }
        }
    public static void main(String[] args){
        SongAnalytics_1 sa = new SongAnalytics_1();
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

