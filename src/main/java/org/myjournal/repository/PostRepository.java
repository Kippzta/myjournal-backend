package org.myjournal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.myjournal.entity.Post;
import org.myjournal.entity.User;



import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    public List<Post> findByUser(User user) {
        return list("user", user);
    }

    public List<Post> findByUserAndDateRange(User user, LocalDateTime start, LocalDateTime end) {

        return list("user = ?1 and createdAt between ?2 and ?3", user, start, end);

    }

}
