package fr.univamu.iut.plats;

import jakarta.ws.rs.*;
import fr.univamu.iut.SqlRequests;

import java.sql.SQLException;

@Path("/plat")
public class GetPlats {

    @GET
    @Produces("text/plain")
    @Path("/getIdByName/{name}")
    public String getIdByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        return SqlRequests.executeQuery("SELECT id FROM Plats WHERE nom='"+name+"'").toString();
    }
}
