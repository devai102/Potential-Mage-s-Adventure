package core;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private final String name;
    private final double timeSeconds;

    public LeaderboardEntry(String name, double timeSeconds) {
        this.name = name;
        this.timeSeconds = timeSeconds;
    }

    public String getName() { return name; }
    public double getTimeSeconds() { return timeSeconds; }

    @Override
    public int compareTo(LeaderboardEntry o) {
        return Double.compare(this.timeSeconds, o.timeSeconds);
    }
}
