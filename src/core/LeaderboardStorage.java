package core;

import java.util.List;

public interface LeaderboardStorage {
    void add(String name, double timeSeconds);
    List<LeaderboardEntry> top(int n);
}
