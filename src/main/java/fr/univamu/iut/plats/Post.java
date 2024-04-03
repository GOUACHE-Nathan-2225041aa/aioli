package fr.univamu.iut.plats;

import jakarta.ws.rs.*;
import java.sql.*;
import fr.univamu.iut.SqlRequests;

@Path("/plats")
public class Post {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean createPlat(@FormParam("nom") String nom, @FormParam("prix") float prix, @FormParam("description") String description){

        try{
            SqlRequests.executeQuery("INSERT INTO Plats (nom, prix, description) VALUES ('"+nom+"', "+prix+",'"+description+"')");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}