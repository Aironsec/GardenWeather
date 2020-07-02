package com.example.gardenweather;

public class MemData {
    private String todayTemper;

    private MemData() {
    }

    private static class MemDataHolder {
        private static final MemData instance = new MemData();
    }

    public static MemData getInstance() {
        return MemDataHolder.instance;
    }

    public String getTodayTemper() {
        return todayTemper;
    }

    public void setTodayTemper(String todayTemper) {
        this.todayTemper = todayTemper;
    }
}
