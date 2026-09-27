package org.myjournal.dto;

import org.myjournal.entity.Mood;

// DTO som används för att skicka statistik om moods till frontend. Innehåller mood, antal inlägg med det moodet och procentandelen av alla inlägg.
public class MoodStatisticsDTO {

    private Mood mood;

    private long count;

    private double percentage;


    public MoodStatisticsDTO(Mood mood, long count, double percentage) {

        this.mood = mood;
        
        this.count = count;

        this.percentage = percentage;
    }

    public Mood getMood() {
        return mood;
    }



    public long getCount() {
        return count;
    }



    public double getPercentage() {
        return percentage;
    }

  

    
    
}
