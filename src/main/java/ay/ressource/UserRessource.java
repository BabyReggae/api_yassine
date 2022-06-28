package ay.ressource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ay.bean.UserBean;
import ay.entity.User;

@Path("user")
public class UserRessource {

    @Inject
    private UserBean userBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMembers() {
        return Response.ok(userBean.getUsers()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if (user.getFirstName() == null) {
            return Response.status(403).build();
        }
        return Response.ok(userBean.addUser(user)).build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editUser(@PathParam("id") Long id, User user) {

        if (user.getFirstName() == null || id == null || id <= 0) {
            return Response.status(403).build();
        }

        userBean.editUser(id, user);
        // exo: supprimer un crewMember avec une boucle for
        return Response.ok(userBean.getUsers()).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(403).build();
        }
        userBean.deleteUser(id);
        // exo: supprimer un crewMember avec une boucle for
        return Response.ok(userBean.getUsers()).build();
    }
}