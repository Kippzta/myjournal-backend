package org.myjournal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.myjournal.dto.CreatePostDTO;
import org.myjournal.dto.MoodStatisticsDTO;
import org.myjournal.dto.PostStatisticsDTO;
import org.myjournal.entity.Mood;
import org.myjournal.entity.Post;
import org.myjournal.entity.User;
import org.myjournal.repository.PostRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PostService {

    @Inject
    PostRepository postRepository;

    @Transactional
    public Post createPost(CreatePostDTO createPostDTO, User user) {
        Post post = new Post();
        post.setNote(createPostDTO.getNote());
        post.setMood(createPostDTO.getMood());
        post.setUser(user);
        postRepository.persist(post);
        return post;
    }

    public List<Post> getPostsForUser(User user) {
        return postRepository.findByUser(user);
    }

    // Metod för att visa statistik för en användares inlägg inom ett visst
    // datumintervall
    public PostStatisticsDTO getStatistics(User user, LocalDate startDate, LocalDate endDate) {

        // createdAt lagras som LocalDateTime, och findByUserAndDateRange och queryn 
        // i PostRepository använder LocalDateTime för att kunna jämföra mot databasen med korrekt typ
        LocalDateTime startDateTime = startDate.atStartOfDay();

        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        // Hämtar alla inlägg för användaren inom det det valda intervallet och sparar dem i en lista
        List<Post> filteredList = postRepository.findByUserAndDateRange(user, startDateTime, endDateTime);

        // Räknar ut totalen av inlägg i listan
        int totalPosts = filteredList.size();

        //Lista för senare spara statistik för varje mood i.
        List<MoodStatisticsDTO> moodStatsList = new ArrayList<>();

        
        // loopar igenom alla moods från enumet och räknar ut hur många inlägg som har den mooden och beräknar procentandelen av totalen.
        //Mood.values() skapar en array av alla enum-värden i Mood som gås igenom av for loopen
        for (Mood mood : Mood.values()) {

            long moodCount = filteredList.stream()
                    .filter(post -> post.getMood() == mood)
                    .count();

            double percentage;

            // Om totalPosts är 0, sätt percentage till 0 så man inte dividerar med 0.
            if (totalPosts == 0) {
                percentage = 0;
            } else {
                // Måste omvandla totalPosts till double för att undvika heltalsdivision
                percentage = (moodCount / (double) totalPosts) * 100;
            }

            // skapar en instans av MoodStatisticsDTO som innehålelr vilket mood , hur många inlägg som har det moodet, och procentandelen av alla posts.
            MoodStatisticsDTO moodStat = new MoodStatisticsDTO(mood, moodCount, percentage);

            moodStatsList.add(moodStat);

        }

        // skapar en instans av PostStatisticsDTO som innehåller totalen av inlägg, start och slutdatum, samt listan med statistik för varje mood.
        PostStatisticsDTO postStatistics = new PostStatisticsDTO(totalPosts, startDate, endDate, moodStatsList);

        return postStatistics;

    }

}
