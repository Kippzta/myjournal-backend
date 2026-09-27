package org.myjournal.dto;

import java.time.LocalDate;
import java.util.List;

// DTO som används för att skicka statistik om inlägg till frontend. Innehåller totala inlägg, start och slutdatum och en lista med statistik för varje mood.
public class PostStatisticsDTO {

    private int totalPosts;
    
    private LocalDate startDate;

    private LocalDate endDate;

    private List<MoodStatisticsDTO> moodStats;



    public PostStatisticsDTO(int totalPosts, LocalDate startDate, LocalDate endDate, List<MoodStatisticsDTO> moodStats) {
        
        this.totalPosts = totalPosts;
        
        this.startDate = startDate;

        this.endDate = endDate;

        this.moodStats = moodStats;

    }



    public int getTotalPosts() {
        return totalPosts;
    }



    public LocalDate getStartDate() {
        return startDate;
    }



    public LocalDate getEndDate() {
        return endDate;
    }



    public List<MoodStatisticsDTO> getMoodStats() {
        return moodStats;
    }



  

  

    
}
