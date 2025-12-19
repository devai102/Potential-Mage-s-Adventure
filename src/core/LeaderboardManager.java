package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardManager implements LeaderboardStorage {
    private final Path storagePath;

    public LeaderboardManager() {
        this.storagePath = Paths.get("leaderboard.csv");
    }

    public synchronized void add(String name, double timeSeconds) {
        List<LeaderboardEntry> entries = loadAll();
        entries.add(new LeaderboardEntry(sanitize(name), timeSeconds));
        Collections.sort(entries);
        if (entries.size() > 5) entries = new ArrayList<>(entries.subList(0, 5));
        saveAll(entries);
    }

    public synchronized List<LeaderboardEntry> top(int n) {
        List<LeaderboardEntry> entries = loadAll();
        if (entries.size() <= n) return entries;
        return new ArrayList<>(entries.subList(0, n));
    }

    private List<LeaderboardEntry> loadAll() {
        List<LeaderboardEntry> list = new ArrayList<>();
        if (!Files.exists(storagePath)) return list;
        try (BufferedReader br = Files.newBufferedReader(storagePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;
                String name = parts[0].trim();
                try {
                    double t = Double.parseDouble(parts[1].trim());
                    list.add(new LeaderboardEntry(name, t));
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }

    private void saveAll(List<LeaderboardEntry> entries) {
        try {
            try (BufferedWriter bw = Files.newBufferedWriter(storagePath, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (LeaderboardEntry e : entries) {
                    bw.write(sanitize(e.getName()) + "," + e.getTimeSeconds());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sanitize(String s) {
        if (s == null) return "";
        String t = s.trim();
        if (t.length() > 16) t = t.substring(0, 16);
        return t.replaceAll("[^A-Za-z0-9 _-]", "");
    }
}
