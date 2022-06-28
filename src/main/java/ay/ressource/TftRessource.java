package ay.ressource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ay.bean.ChampionBean;
import ay.bean.TokenBean;
import ay.entity.Champion;


@Path("tft")
public class TftRessource {

    @Inject
    private ChampionBean championBean;

    @Inject
    private TokenBean tokenBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMembers(@HeaderParam("Authorization") String bearerAuth) {
        if (bearerAuth == null)
            return Response.status(401).entity("No Bearer provided, :s").build();
        String token_val = bearerAuth.split(" ")[1];
        var verifToken = tokenBean.getTokenByValue(token_val);
        if (verifToken == null)
            return Response.status(401).entity("Bearer not recognized").build();
        
        
        return Response.ok(championBean.getChampions()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addChampion(@HeaderParam("Authorization") String bearerAuth,Champion champion) {
        
        if (bearerAuth == null)
            return Response.status(401).entity("No Bearer provided, :s").build();
        String token_val = bearerAuth.split(" ")[1];
        var verifToken = tokenBean.getTokenByValue(token_val);
        if (verifToken == null)
            return Response.status(401).entity("Bearer not recognized").build();
        
        if (champion.getName() == null) {
            return Response.status(403).build();
        }
        return Response.ok(championBean.addChampion(champion)).build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editChampion(@HeaderParam("Authorization") String bearerAuth,@PathParam("id") Long id, Champion champion ) {
        if (bearerAuth == null)
            return Response.status(401).entity("No Bearer provided, :s").build();
        String token_val = bearerAuth.split(" ")[1];
        var verifToken = tokenBean.getTokenByValue(token_val);
        if (verifToken == null)
            return Response.status(401).entity("Bearer not recognized").build();


        if (champion.getName() == null || id == null || id <= 0) {
            return Response.status(403).build();
        }

        championBean.editChampion(id , champion );
        // exo: supprimer un crewMember avec une boucle for
        return Response.ok(championBean.getChampions()).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteChampion(@HeaderParam("Authorization") String bearerAuth,@PathParam("id") Long id) {
        
        if (bearerAuth == null)
            return Response.status(401).entity("No Bearer provided, :s").build();
        String token_val = bearerAuth.split(" ")[1];
        var verifToken = tokenBean.getTokenByValue(token_val);
        if (verifToken == null)
            return Response.status(401).entity("Bearer not recognized").build();

        if (id == null || id <= 0) {
            return Response.status(403).build();
        }
        championBean.deleteChampion(id);
        // exo: supprimer un crewMember avec une boucle for
        return Response.ok(championBean.getChampions()).build();
    }
}