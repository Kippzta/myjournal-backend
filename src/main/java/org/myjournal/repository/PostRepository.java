package org.myjournal.repository;

import java.util.List;

import org.myjournal.entity.Post;
import org.myjournal.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class PostRepository implements PanacheRepository<Post>{
    
    public List<Post> findByUser(User user) {
        return list("user", user);
    }
    }

