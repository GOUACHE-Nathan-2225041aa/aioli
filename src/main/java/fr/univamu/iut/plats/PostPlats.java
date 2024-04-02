package fr.univamu.iut.plats;

import jakarta.ws.rs.*;
import java.sql.*;
import fr.univamu.iut.SqlRequests;

@Path("/plat/create")
public class PostPlats {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean createPlat(@FormParam("name") String name) throws ClassNotFoundException {

        try{
            SqlRequests.executeQuery("INSERT INTO Plats (nom) VALUES ('"+name+"')").toString();
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
}