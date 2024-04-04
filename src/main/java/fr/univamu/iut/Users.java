package fr.univamu.iut;

import jakarta.ws.rs.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/utilisateurs")
public class Users {
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean create(@FormParam("nom") String nom){

        try{
            SqlRequests.executeQuery("INSERT INTO Utilisateurs (nom) VALUES ('"+nom+"')");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }

    @GET
    @Produces("text/plain")
    public String get() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Utilisateurs ");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                row.put("id", id);
                row.put("nom", nom);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/{id}")
    public String getWithId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Utilisateurs WHERE id='" + id + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                String nom = resultSet.getString("nom");
                row.put("id", id);
                row.put("nom", nom);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString().replace("[", "").replace("]", "");
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean put(@FormParam("nom")String nom, @FormParam("id")int id){
        try{
            SqlRequests.executeQuery("UPDATE Utilisateurs SET nom='"+nom+"' WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id")int id){
        try{
            SqlRequests.executeQuery("DELETE FROM Utilisateurs WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
