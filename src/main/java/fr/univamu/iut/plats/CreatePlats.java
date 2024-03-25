package fr.univamu.iut.plats;

import jakarta.ws.rs.*;
import java.sql.*;
import fr.univamu.iut.SqlRequests;

@Path("/plat/create")
public class CreatePlats {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String createPlat(@FormParam("name") String name) throws SQLException, ClassNotFoundException {

        SqlRequests.executeQuery("INSERT INTO Plats (nom) VALUES ('"+name+"')").toString();

        return name;
    }
}