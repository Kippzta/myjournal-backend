package org.myjournal.service;

import java.util.List;

import org.myjournal.dto.CreatePostDTO;
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

}
