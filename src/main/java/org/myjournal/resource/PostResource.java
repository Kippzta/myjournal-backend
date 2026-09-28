package org.myjournal.resource;

import java.time.LocalDate;
import java.util.List;

import org.myjournal.dto.CreatePostDTO;
import org.myjournal.dto.PostDTO;
import org.myjournal.dto.PostStatisticsDTO;
import org.myjournal.entity.Post;
import org.myjournal.entity.User;
import org.myjournal.repository.UserRepository;
import org.myjournal.service.PostService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path ("/api/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject 
    private PostService postService;

    @Inject 
    private UserRepository userRepository;

    @GET 
    @RolesAllowed ("user")
    public List<PostDTO> getAllPosts(@Context SecurityContext securityContext) {

        User user = userRepository.findByUsername(securityContext.getUserPrincipal().getName());

        List<Post> posts = postService.getPostsForUser(user);
        // Omvandlar alla posts till säkra DTO:er
        List<PostDTO> postDTOs = posts.stream().map(post -> new PostDTO(post)).toList();
        
        return postDTOs;
    }
    

    @POST 
    @RolesAllowed ("user")
    public Response createPost(@Context SecurityContext securityContext, CreatePostDTO dto) {
        // Hämtar den redan autentiserade användaren via SecurityContext.
        User user = userRepository.findByUsername(securityContext.getUserPrincipal().getName());

        Post post = postService.createPost(dto, user);

        PostDTO postDTO = new PostDTO(post);

        return Response.status(Response.Status.CREATED).entity(postDTO).build();
    }

    @GET 
    @Path ("/statistics")
    @RolesAllowed ("user")
    public PostStatisticsDTO showStatistics(@Context SecurityContext securityContext, @QueryParam("startDate") LocalDate startDate, @QueryParam("endDate") LocalDate endDate) {

        User user = userRepository.findByUsername(securityContext.getUserPrincipal().getName());

        PostStatisticsDTO postStats = postService.getStatistics(user, startDate, endDate);

        return postStats;

    }
}
