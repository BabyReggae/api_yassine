package ay.ressource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ay.bean.ChampionBean;
import ay.entity.Champion;


@Path("tft")
public class TftRessource {

    @Inject
    private ChampionBean championBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMembers() {
        return Response.ok(championBean.getChampions()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addChampion(Champion champion) {
        if (champion.getName() == null) {
            return Response.status(403).build();
        }
        return Response.ok(championBean.addChampion(champion)).build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editChampion(@PathParam("id") Long id, Champion champion ) {

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
    public Response deleteChampion(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(403).build();
        }
        championBean.deleteChampion(id);
        // exo: supprimer un crewMember avec une boucle for
        return Response.ok(championBean.getChampions()).build();
    }
}