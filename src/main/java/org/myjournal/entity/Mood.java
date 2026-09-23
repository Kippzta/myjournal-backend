package org.myjournal.entity;

public enum Mood {
    HAPPY("😊"),
    SAD("☹️"),
    MOTIVATED("💪"),
    ANGRY("🤬"),
    SUSPICIOUS("🤨");

    private final String emoji;

    Mood(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

}
