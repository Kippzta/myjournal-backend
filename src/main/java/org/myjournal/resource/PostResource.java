package org.myjournal.resource;

import java.util.List;

import org.myjournal.dto.CreatePostDTO;
import org.myjournal.dto.PostDTO;
import org.myjournal.entity.Post;
import org.myjournal.entity.User;
import org.myjournal.repository.UserRepository;
import org.myjournal.service.PostService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path ("/api/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject 
    private PostService postService;

    @Inject 
    private UserRepository userRepository;

    @GET 
    public List<PostDTO> getAllPosts() {
        User user = userRepository.findById(1L);
        List<Post> posts = postService.getPostsForUser(user);
        List<PostDTO> postDTOs = posts.stream().map(post -> new PostDTO(post)).toList();
        return postDTOs;
    }
    

    @POST 
    public Response createPost(CreatePostDTO dto) {
        User user = userRepository.findById(1L);
        Post post = postService.createPost(dto, user);
        PostDTO postDTO = new PostDTO(post);
        return Response.status(Response.Status.CREATED).entity(postDTO).build();
    }
}
