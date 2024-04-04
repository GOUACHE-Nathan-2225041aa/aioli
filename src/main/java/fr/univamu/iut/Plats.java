package fr.univamu.iut;

import jakarta.ws.rs.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
@Path("/plats")
public class Plats {
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean create(@FormParam("nom") String nom, @FormParam("prix") float prix, @FormParam("description") String description){

        try{
            SqlRequests.executeQuery("INSERT INTO Plats (nom, prix, description) VALUES ('"+nom+"', "+prix+",'"+description+"')");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
    @GET
    @Produces("text/plain")
    public String get() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Plats ");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                float prix = resultSet.getFloat("prix");
                String description = resultSet.getString("description");
                row.put("id", id);
                row.put("nom", nom);
                row.put("prix", prix);
                row.put("description", description);
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
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Plats WHERE id='" + id + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                String nom = resultSet.getString("nom");
                float prix = resultSet.getFloat("prix");
                String description = resultSet.getString("description");
                row.put("id", id);
                row.put("nom", nom);
                row.put("prix", prix);
                row.put("description", description);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString().replace("[", "").replace("]", "");
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean put(@FormParam("nom") String nom, @FormParam("prix") float prix, @FormParam("description") String description, @FormParam("id")int id){
        try{
            SqlRequests.executeQuery("UPDATE Plats SET nom='"+nom+"', prix='"+prix+"',description='"+description+"' WHERE id='"+id+"'");
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
            SqlRequests.executeQuery("DELETE FROM Plats WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
