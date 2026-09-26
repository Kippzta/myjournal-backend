package org.myjournal.resource;

import org.myjournal.dto.RegisterDTO;
import org.myjournal.dto.UserDTO;
import org.myjournal.entity.User;
import org.myjournal.service.AuthService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path ("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject AuthService authService;

    
    @Path ("/register")
    @POST 
    @PermitAll 
    public Response registerUser(RegisterDTO registerDTO) {

        User user = authService.registerUser(registerDTO);


        UserDTO userDTO = new UserDTO(user);

        return Response.status(Response.Status.CREATED).entity(userDTO).build();

    }



    
}
