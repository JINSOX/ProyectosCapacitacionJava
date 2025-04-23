package com.example;

import Entities.Usuario;
import Exceptions.NoSuchElementException;
import Repositories.UserRepository;
import io.smallrye.faulttolerance.api.RateLimit;
import io.smallrye.faulttolerance.api.RateLimitException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;
import java.util.Objects;

@Path("/users")
@Transactional
public class UserResource {

    @Inject
    UserRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RateLimit(2)
    @Timeout(100)
    @Fallback(fallbackMethod = "FallbackAvaliable" , applyOn = RateLimitException.class )
    public Response getUsers() {
        try {
            List<Usuario> listUsers = repo.listAll();
            return Response.ok(listUsers).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RateLimit(2)
    @Timeout(100)
    public Response createUser(@Valid Usuario usuario) {
        if (usuario.getEmail() == null || Objects.equals(usuario.getEmail(), "")) {
            usuario.setStattusCode(400);
        } else {
            usuario.setStattusCode(200);
        }
        try {
            repo.persist(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity(usuario)
                    .build();
        } catch (Exception e) {
            return Response.status(500)
                    .entity("Error en el servidor al crear el usuario: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @RateLimit(2)
    @Timeout(100)
    public Response getUserById(@PathParam("id") Long id) throws NoSuchElementException {
        try {
            var user = repo.findById(id);
            if (user != null) {
                if (user.getStattusCode() == 400) {
                    return Response.status(202).entity(user).build();
                }
                return Response.status(200).entity(user).build();
            }
            throw new NoSuchElementException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{id}")
    @RateLimit(2)
    @Timeout(100)
    public Response deleteUserById(@PathParam("id") Long id) {
        try {
            repo.deleteById(id);
            return Response.status(200).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{id}")
    @RateLimit(2)
    @Timeout(100)
    public Response updateUser(@PathParam("id") Long id, @Valid Usuario usuario) throws NoSuchElementException {
        try {
            var updatedUser = repo.findById(id);

            if (updatedUser == null) {
                throw new NoSuchElementException();
            }

            updatedUser.setName(usuario.getName());
            updatedUser.setSex(usuario.getSex());
            updatedUser.setEmail(usuario.getEmail());
            updatedUser.setAvatar(usuario.getAvatar());
            updatedUser.setCardNumber(usuario.getMaskedCardNumber());
            updatedUser.setCardCvv(usuario.getMaskedCardcvv());

            if (updatedUser.getEmail() == null || updatedUser.getEmail().isEmpty()) {
                updatedUser.setStattusCode(400);
            } else {
                updatedUser.setStattusCode(200);
            }

            return Response.status(Response.Status.OK)
                    .entity(updatedUser)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el usuario: " + e.getMessage())
                    .build();
        }
    }

    @Produces(MediaType.APPLICATION_JSON)
    public Response FallbackAvaliable() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error del ratelimit")
                .build();
    }

}
